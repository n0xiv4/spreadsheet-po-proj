package xxl.core.search;

import java.util.List;

import xxl.core.Cell;
import xxl.core.Spreadsheet;

/**
 * The Search interface defines a contract for searching a Spreadsheet
 * for cells containing a specified search query.
 */
public interface Search {

	/**
     * Searches for cells in the given Spreadsheet that contain the specified
     * search query.
     *
     * @param spreadsheet The Spreadsheet to search within.
     * @param toSearch The string to search for within the Spreadsheet cells.
     * @return A list of Cell objects that match the search query.
     */
	public List<Cell> search(Spreadsheet spreadsheet, String toSearch);
	
}
