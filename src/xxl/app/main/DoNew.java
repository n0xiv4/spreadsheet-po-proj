package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("rows", Message.lines());
    addIntegerField("columns", Message.columns());
  }
  
  @Override
  protected final void execute() throws CommandException {
    Integer rows = integerField("rows");
    Integer columns = integerField("columns");
    
    _receiver.createSpreadsheet(rows, columns);
  }
}
