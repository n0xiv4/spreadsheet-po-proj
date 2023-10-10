package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
    // addStringField("gamma", Message.address());
    // addStringField("content", Message.contents());
  }
  
  @Override
  protected final void execute() throws CommandException {
    // String gamma = stringField("gamma");
    // String content = stringField("content");
    // NEXT
  }
}
