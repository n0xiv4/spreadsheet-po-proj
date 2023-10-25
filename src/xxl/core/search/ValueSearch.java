package xxl.core.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xxl.core.Cell;
import xxl.core.Spreadsheet;
import xxl.core.exception.InvalidValueTypeException;

/**
 * The ValueSearch class implements the Search interface and provides methods to search
 * for cells in a Spreadsheet based on either string or integer values.
 */
public class ValueSearch implements Search {

    /**
     * Searches for cells in the given Spreadsheet that contain the specified search query,
     * which can be a string or an integer value.
     *
     * @param spreadsheet The Spreadsheet to search within.
     * @param toSearch The search query, which can be a string or an integer value.
     * @return A list of Cell objects that match the search query.
     */
	@Override
	public List<Cell> search(Spreadsheet spreadsheet, String toSearch) {
		if (toSearch.charAt(0) == '\'') {
			String strValue = toSearch.substring(1);
			return findCellsByStringValue(spreadsheet, strValue);
		}
		else {
			int intValue = Integer.parseInt(toSearch);
			return findCellsByIntValue(spreadsheet, intValue);
		}
    }

    /**
     * Searches for cells in the given Spreadsheet that contain the specified string value.
     *
     * @param spreadsheet The Spreadsheet to search within.
     * @param strValue The string value to search for within the Spreadsheet cells.
     * @return A list of Cell objects that contain the specified string value.
     */
    private List<Cell> findCellsByStringValue(Spreadsheet spreadsheet, String strValue) {
        List<Cell> foundCells = new ArrayList<Cell>();
		Iterator<Cell> cellIterator = spreadsheet.getCellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			try {
				if (cell.getValue().getStringValue().equals(strValue)) {
					foundCells.add(cell);
				}
			} 
			catch (InvalidValueTypeException e) {
			}
		}
		return foundCells;
	}

    /**
     * Searches for cells in the given Spreadsheet that contain the specified integer value.
     *
     * @param spreadsheet The Spreadsheet to search within.
     * @param intValue The integer value to search for within the Spreadsheet cells.
     * @return A list of Cell objects that contain the specified integer value.
     */
    private List<Cell> findCellsByIntValue(Spreadsheet spreadsheet, int intValue) {
        List<Cell> foundCells = new ArrayList<Cell>();
		Iterator<Cell> cellIterator = spreadsheet.getCellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			try {
				if (cell.getValue().getIntValue() == intValue) {
					foundCells.add(cell);
				}
			} 
			catch (InvalidValueTypeException e) {
			}
		}
		return foundCells;
	}
}
