package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.app.exception.InvalidCellRangeException;

import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidCellIntervalException;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

	DoShow(Spreadsheet receiver) {
		super(Label.SHOW, receiver);
	}
	
	@Override
	protected final void execute() throws CommandException {
		String gamma = Form.requestString(Message.address());
		try {
			_display.popup(_receiver.visualizeGamma(gamma));
		}
		catch(InvalidCellIntervalException e) {
			throw new InvalidCellRangeException(gamma);
		}
	}
}
