package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
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
	}
	
	@Override
	protected final void execute() throws CommandException {
		if (_receiver.isCurrentSpreadsheetChanged() && Form.confirm(Message.saveBeforeExit())) {
			try {
				_receiver.saveFile();
			}
			catch (MissingFileAssociationException | IOException e) {
				throw new FileOpenFailedException(e);
			}
		}
		Integer rows = Form.requestInteger(Message.lines());
		Integer columns = Form.requestInteger(Message.columns());
		
		_receiver.createSpreadsheet(rows, columns);
	}
}
