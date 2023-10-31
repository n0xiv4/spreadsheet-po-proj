package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.core.Calculator;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

	DoNew(Calculator receiver) {
		super(Label.NEW, receiver);
	}
	
	@Override
	protected final void execute() throws CommandException {
		if (_receiver.getSpreadsheet() != null && _receiver.isCurrentSpreadsheetChanged() && Form.confirm(Message.saveBeforeExit())) {
			new DoSave(_receiver).execute();
		}
		Integer rows = Form.requestInteger(Message.lines());
		Integer columns = Form.requestInteger(Message.columns());
		
		_receiver.createSpreadsheet(rows, columns);
	}
}
