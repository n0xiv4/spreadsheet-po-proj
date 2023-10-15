package xxl.app;

import pt.tecnico.uilib.Dialog;
import xxl.core.exception.ImportFileException;

/**
 * Class that represents the spreadsheet's textual interface.
 */
public class App {
	public static void main(String[] args) {
		try (var ui = Dialog.UI) {
			var receiver = new xxl.core.Calculator();
			// FIXME
			System.setProperty("import", System.getProperty("user.dir") + "/src/xxl/core/teste.import");
			//
			String datafile = System.getProperty("import");
			if (datafile != null) {
				try {
					receiver.importFile(datafile);
				} catch (ImportFileException e) {
					// no behavior described: just present the problem
					e.printStackTrace();
				}
			}
			
			(new xxl.app.main.Menu(receiver)).open();
		}
	}
}
