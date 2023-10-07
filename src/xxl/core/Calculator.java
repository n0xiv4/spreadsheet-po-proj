package xxl.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

// FIXME import classes

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {
	/** The current spreadsheet. */
	private Spreadsheet _spreadsheet;
	private String _currentFile;
	
	public void setCurrentFile(String filename) {
		_currentFile = filename;
	}

	/**
	 * Return the current spreadsheet.
	 *
	 * @returns the current spreadsheet of this application. This reference can be null.
	 */
	public final Spreadsheet getSpreadsheet() {
		return _spreadsheet;
	}

	/**
	 * Saves the serialized application's state into the file associated to the current network.
	 *
	 * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
	 * @throws MissingFileAssociationException if the current network does not have a file.
	 * @throws IOException if there is some error while serializing the state of the network to disk.
	 */
	public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
		FileOutputStream fileOut = new FileOutputStream(_currentFile);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(_spreadsheet);
		out.close();
		fileOut.close();
	}
	
	/**
	 * Saves the serialized application's state into the specified file. The current network is
	 * associated to this file.
	 *
	 * @param filename the name of the file.
	 * @throws FileNotFoundException if for some reason the file cannot be created or opened.
	 * @throws MissingFileAssociationException if the current network does not have a file.
	 * @throws IOException if there is some error while serializing the state of the network to disk.
	 */
	public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
		FileOutputStream fileOut = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(_spreadsheet);
		out.close();
		fileOut.close();
	}

	/**
	 * @param filename name of the file containing the serialized application's state
	 *        to load.
	 * @throws UnavailableFileException if the specified file does not exist or there is
	 *         an error while processing this file.
	 */
	public void load(String filename) throws UnavailableFileException {
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			_spreadsheet = (Spreadsheet) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException | ClassNotFoundException e) {
			throw new UnavailableFileException(filename);
		}
	}
	
	/**
	 * Read text input file and create domain entities.
	 *
	 * @param filename name of the text input file
	 * @throws ImportFileException
	 */
	public void importFile(String filename) throws ImportFileException {
		try {
			Parser parser = new Parser(_spreadsheet);
			_spreadsheet = parser.parseFile(filename);
		} 
		catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
			throw new ImportFileException(filename, e);
		}
	} 
}
