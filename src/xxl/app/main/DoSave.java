package xxl.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
  }
  
  @Override
  protected final void execute() throws FileOpenFailedException {
    try {
      if (_receiver.hasFilename()) {
        _receiver.saveFile();
      }
      else {
        _receiver.saveFileAs(Form.requestString(Message.saveAs()));
      }
    }
    catch (MissingFileAssociationException | IOException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
