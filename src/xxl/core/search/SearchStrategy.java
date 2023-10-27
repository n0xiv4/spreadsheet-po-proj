package xxl.core.search;

import java.util.List;

import xxl.core.Cell;
import xxl.core.Spreadsheet;

/**
 * An interface for defining search strategies in a spreadsheet.
 */
public interface SearchStrategy {

	/**
	 * Searches for cells in a spreadsheet based on a specific criteria defined by the implementing class.
	 *
	 * @param spreadsheet The spreadsheet to search within.
	 * @param gamma The criteria, represented as a string, to be used for searching.
	 * @return A list of cells that match the search criteria.
	 */
	List<Cell> search(Spreadsheet spreadsheet, String gamma);
	
}
