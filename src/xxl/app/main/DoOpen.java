package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import xxl.core.Calculator;
import xxl.core.exception.UnavailableFileException;
import xxl.app.exception.FileOpenFailedException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

	DoOpen(Calculator receiver) {
		super(Label.OPEN, receiver);
	}
	
	@Override
	protected final void execute() throws FileOpenFailedException {
		if (_receiver.getSpreadsheet() != null && _receiver.isCurrentSpreadsheetChanged() && Form.confirm(Message.saveBeforeExit())) {
			new DoSave(_receiver).execute();
		}
		try {
			_receiver.loadFile(Form.requestString(Message.openFile()));
		}
		catch (UnavailableFileException e) {
			throw new FileOpenFailedException(e);
		}
	}
}
