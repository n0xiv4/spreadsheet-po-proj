package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.app.exception.InvalidCellRangeException;

import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidCellIntervalException;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

	DoCopy(Spreadsheet receiver) {
		super(Label.COPY, receiver);
	}
	
	@Override
	protected final void execute() throws CommandException {
		String gamma = Form.requestString(Message.address());
		try {
			_receiver.copyGamma(gamma);
		}
		catch (InvalidCellIntervalException e) {
			throw new InvalidCellRangeException(gamma);
		}
	}
}
