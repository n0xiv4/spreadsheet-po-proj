package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Calculator;
// FIXME import classes

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
    addStringField("filename", Message.saveAs());
  }
  
  @Override
  protected final void execute() {
    if (_receiver.getCurrentFile() != null) {
      try {
        _receiver.saveFile();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    else {
      String filename = stringField("filename");
      try {
        _receiver.saveFileAs(filename);
      }
      catch (Exception e) {
        e.printStackTrace();
      } 
    }
    // FIXME implement command and create a local Form
  }
}
