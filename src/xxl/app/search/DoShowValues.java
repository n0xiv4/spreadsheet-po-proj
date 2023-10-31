package xxl.app.search;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.search.ValueSearchStrategy;

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

	DoShowValues(Spreadsheet receiver) {
		super(Label.SEARCH_VALUES, receiver);
	}
	
	@Override
	protected final void execute() {
		_display.popup(_receiver.search(new ValueSearchStrategy(), Form.requestString(Message.searchValue())));
	}
}
