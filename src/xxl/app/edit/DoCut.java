package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidCellIntervalException;

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {

	DoCut(Spreadsheet receiver) {
		super(Label.CUT, receiver);
	}
	
	@Override
	protected final void execute() throws CommandException {
		String gamma = Form.requestString(Message.address());
		try {
			_receiver.cutGamma(gamma);
		}
		catch (InvalidCellIntervalException e) {
			throw new InvalidCellRangeException(gamma);
		}
	}
}
