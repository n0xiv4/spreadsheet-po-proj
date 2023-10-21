package xxl.app.search;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

	DoShowValues(Spreadsheet receiver) {
		super(Label.SEARCH_VALUES, receiver);
	}
	
	@Override
	protected final void execute() {
		_display.popup(_receiver.visualizeValue(Form.requestString(Message.searchValue())));
	}
}
