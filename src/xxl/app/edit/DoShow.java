package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    addStringField("gamma", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String gamma = stringField("gamma");
    _receiver.visualizeGamma(gamma);
  }
}
