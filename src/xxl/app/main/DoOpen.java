package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.core.Calculator;
import xxl.app.exception.FileOpenFailedException;
// FIXME import classes

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    /*
    try {
      
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }*/
  }
}
