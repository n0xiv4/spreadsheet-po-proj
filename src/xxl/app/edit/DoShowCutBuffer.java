package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.core.Spreadsheet;

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

	DoShowCutBuffer(Spreadsheet receiver) {
		super(Label.SHOW_CUT_BUFFER, receiver);
	}
	
	@Override
	protected final void execute() throws CommandException {
		_display.popup(_receiver.visualizeCutBuffer());
	}
}
