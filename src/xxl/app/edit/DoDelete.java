package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
// FIXME import classes
import xxl.core.exception.InvalidCellIntervalException;

/**
 * Delete command.
 */
class DoDelete extends Command<Spreadsheet> {

	DoDelete(Spreadsheet receiver) {
		super(Label.DELETE, receiver);
	}
	
	@Override
	protected final void execute() throws CommandException {
		String gamma = Form.requestString(Message.address());
		try {
			_receiver.deleteGamma(gamma);
		}
		catch (InvalidCellIntervalException e) {
			throw new InvalidCellRangeException(gamma);
		}
	}
}
