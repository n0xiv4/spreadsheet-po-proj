package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.app.exception.FileOpenFailedException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.Calculator;

import java.io.IOException;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

	DoNew(Calculator receiver) {
		super(Label.NEW, receiver);
		addBooleanField("saveBeforeExit?", Message.saveBeforeExit());
		addIntegerField("rows", Message.lines());
		addIntegerField("columns", Message.columns());
	}
	
	@Override
	protected final void execute() throws CommandException {
		if (_receiver.getSpreadsheet() != null && booleanField("saveBeforeExit?")) {
			try {
				_receiver.saveFile();
			}
			catch (MissingFileAssociationException | IOException e) {
				throw new FileOpenFailedException(e);
			}
		}
		Integer rows = integerField("rows");
		Integer columns = integerField("columns");
		
		_receiver.createSpreadsheet(rows, columns);
	}
}
