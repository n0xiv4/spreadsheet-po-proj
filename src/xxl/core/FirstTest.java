package xxl.core;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;

public class FirstTest {
	public static void main (String[] args) throws ImportFileException, FileNotFoundException, IOException, MissingFileAssociationException, UnavailableFileException {
		Calculator calculator = new Calculator();
		// loadSerializer(calculator);
		importFile(calculator);
	}

	static private void importFile(Calculator calculator) throws ImportFileException {
		calculator.importFile("xxl\\core\\test.txt");
		printSpreadsheet(calculator.getSpreadsheet());
	}

	/* Testar folha no Serializer atual (folha1.ser) */
	static private void loadSerializer(Calculator calculator) throws UnavailableFileException {
		// dar load ao ficheiro atual
		calculator.load("xxl\\core\\folha1.ser");

		Spreadsheet folha = calculator.getSpreadsheet();
		printSpreadsheet(folha);
	}

	static private void printSpreadsheet(Spreadsheet spreadsheet) {
		Position end = spreadsheet.getEndPosition();

		for (int row = 1; row <= end.getRow(); row++) {
			for (int column = 1; column <= end.getColumn(); column++) {
				Position current = new Position(row, column);
				spreadsheet.printCellInPosition(current);
			}
		}
	}
}
