package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.app.exception.InvalidCellRangeException;
import xxl.app.exception.UnknownFunctionException;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidCellIntervalException;
import xxl.core.exception.InvalidFunctionException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

	DoInsert(Spreadsheet receiver) {
		super(Label.INSERT, receiver);
	}
	
	@Override
	protected final void execute() throws CommandException {
		String gamma = Form.requestString(Message.address());
		String content = Form.requestString(Message.contents());
		try {
			_receiver.insertGamma(gamma, content);
		}
		catch (InvalidCellIntervalException ivie) {
			throw new InvalidCellRangeException(gamma);
		}
		catch (InvalidFunctionException ife) {
			throw new UnknownFunctionException(ife.getEntrySpecification());
		}
		catch (UnrecognizedEntryException uee) {
			// This entry is invalid. Nothing will be done, then
		}
	}
}
