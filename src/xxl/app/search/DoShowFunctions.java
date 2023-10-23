package xxl.app.search;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

	DoShowFunctions(Spreadsheet receiver) {
		super(Label.SEARCH_FUNCTIONS, receiver);
	}

	@Override
	protected final void execute() {
		_display.popup(_receiver.visualizeFunction(Form.requestString(Message.searchFunction())));
	}
}
