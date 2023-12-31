package xxl.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Map;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

import java.util.HashMap;

/**
 * The {@code Calculator} class represents a spreadsheet application that allows users to work with spreadsheets.
 * It manages the current spreadsheet, the active user, and the user collection.
 */
public class Calculator {

	/** The singleton instance of the calculator. */
	private static Calculator _instance;

	/** The current spreadsheet being edited. */
	private Spreadsheet _spreadsheet;
	
	/** The filename of the currently loaded or saved spreadsheet. */
	private String _currentFile;

	/** The active user who is currently using the calculator. */
	private User _activeUser;

	/** A collection of users registered in the calculator application. */
	private Map<Integer, User> _users;

	/**
	 * Constructs a new {@code Calculator} object, initializing it with a default "root" user.
	 * The "root" user is set as the active user, and it is added to the list of users.
	 * This constructor is responsible for creating the initial state of the calculator.
	 */
	private Calculator() {
		User rootUser = new User("root");
		_activeUser = rootUser;
		_users = new HashMap<Integer, User>();
		_users.put(rootUser.hashCode(), rootUser);
	}

	/**
	 * Gets the single instance of the `Calculator` class.
	 *
	 * @return The instance of the Calculator.
	 */
	public static Calculator getInstance() {
		if (_instance == null) {
			_instance = new Calculator();
		}
		return _instance;
	}

	/**
	 * Checks if a filename is associated with the current spreadsheet.
	 *
	 * @return {@code true} if a filename is associated with the current spreadsheet, {@code false} otherwise.
	 */
	public boolean hasFilename() {
		return _currentFile != null;
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
	 * @throws MissingFileAssociationException if the current network does not have a file.
	 * @throws IOException if there is some error while serializing the state of the network to disk.
	 */
	public void saveFile() throws MissingFileAssociationException, IOException {
		if (_currentFile == null) {
			throw new MissingFileAssociationException();
		}
		else {
			try (FileOutputStream fileOut = new FileOutputStream(_currentFile);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				// Spreadsheet is now saved, not changed anymore
				_spreadsheet.flagAsUnchanged();
				out.writeObject(_spreadsheet);
			}
			// Exceptions aren't handled here, so no catch needed
		}
	}
	
	/**
	 * Saves the serialized application's state into the specified file. The current network is
	 * associated to this file.
	 *
	 * @param filename the name of the file.
	 * @throws IOException if there is some error while serializing the state of the network to disk.
	 */
	public void saveFileAs(String filename) throws IOException {
		setCurrentFile(filename);
		try {
			saveFile();
		}
		catch (MissingFileAssociationException e) {
			// Not the case here, association added with setCurrentFile
		}
	}

	/**
	 * @param filename name of the file containing the serialized application's state
	 *        to load.
	 * @throws UnavailableFileException if the specified file does not exist or there is
	 *         an error while processing this file.
	 */
	public void loadFile(String filename) throws UnavailableFileException {
		try (FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn)) {
			_spreadsheet = (Spreadsheet) in.readObject();
			setCurrentFile(filename);
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
			_spreadsheet.linkUser(_activeUser);
			_spreadsheet.flagAsChanged();
		} 
		catch (IOException | UnrecognizedEntryException e) {
			throw new ImportFileException(filename, e);
		}
	} 

	/**
	 * Creates a new spreadsheet with the specified number of rows and columns and associates it with the active user.
	 *
	 * @param rows    The number of rows in the new spreadsheet.
	 * @param columns The number of columns in the new spreadsheet.
	 */
	public void createSpreadsheet(int rows, int columns) {
		_spreadsheet = new Spreadsheet(rows, columns);
		_spreadsheet.linkUser(_activeUser);
		_spreadsheet.flagAsChanged();
	}

	/**
	 * Creates a new user with the specified username and adds it to the user collection if the username does not already exist.
	 *
	 * @param username The username of the new user to be created.
	 * @return {@code true} if the user was successfully created and added, {@code false} if the username already exists.
	 */
	public boolean createUser(String username) {
		User newUser = new User(username);
		if (!userExists(newUser)) {
			_users.put(newUser.hashCode(), newUser);
			return true;
		}
		return false;
	}

	/**
	 * Checks whether the current spreadsheet has been changed or modified.
	 *
	 * @return {@code true} if the current spreadsheet has been changed; {@code false} otherwise.
	 */
	public boolean isCurrentSpreadsheetChanged() {
		return _spreadsheet.isChanged();
	}
	
	/**
	 * Checks if a user with the same properties as the given user already exists in the user collection.
	 *
	 * @param userToTest The user to be tested for existence in the user collection.
	 * @return {@code true} if a user with the same properties (e.g., username) exists, {@code false} otherwise.
	 */
	private boolean userExists(User userToTest) {
		for (User user: _users.values()) {
			if (user.equals(userToTest)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the current file for the calculator to the specified filename.
	 * This method updates the current file associated with the calculator instance.
	 *
	 * @param filename The filename to set as the current file.
	 */
	private void setCurrentFile(String filename) {
		_currentFile = filename;
	}
	
}
