package xxl.core.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xxl.core.Cell;
import xxl.core.Spreadsheet;

/**
 * The FunctionSearch class implements the Search interface to search for cells in a
 * Spreadsheet based on the presence of a specified function or content type.
 */
public class FunctionSearch implements Search {

	/**
     * Searches for cells in the given Spreadsheet that contain the specified function
     * or content type.
     *
     * @param spreadsheet The Spreadsheet to search within.
     * @param toSearch The function or content type to search for within the Spreadsheet cells.
     * @return A list of Cell objects that contain the matching function or content type.
     */
	@Override
	public List<Cell> search(Spreadsheet spreadsheet, String toSearch) {
		List<Cell> foundCells = new ArrayList<Cell>();
		Iterator<Cell> cellIterator = spreadsheet.getCellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (cell.getContentType().contains(toSearch)) {
				foundCells.add(cell);
			}
		}
		return foundCells;
	}
}
