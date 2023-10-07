package xxl.core;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import xxl.core.exception.ImportFileException;

public class FirstTest {
	public static void main (String[] args) throws ImportFileException, FileNotFoundException, IOException {
		Calculator calculator = new Calculator();
		calculator.importFile("D:\\Utilizadores\\Rodrigo\\Documentos\\OneDrive - IST\\OneDrive - Universidade de Lisboa\\Documentos\\2º Ano\\1º Semestre\\PO\\ProjetoGIT\\spreadsheet-po-proj\\bin\\xxl\\core\\test.txt");
		
		Spreadsheet folha = calculator.getSpreadsheet();

		System.out.println(folha.getValueInPosition(new Position(1, 1)));
		System.out.println(folha.getValueInPosition(new Position(1, 2)));
		System.out.println(folha.getValueInPosition(new Position(1, 3)));
	}
}
