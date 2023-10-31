package xxl.core.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xxl.core.Parser;
import xxl.core.Cell;
import xxl.core.Spreadsheet;
import xxl.core.content.literal.Literal;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * A search strategy for finding cells in a spreadsheet that contain a specific value.
 */
public class ValueSearchStrategy implements SearchStrategy {

	/**
	 * Searches for cells in a spreadsheet containing a specific value.
	 *
	 * @param spreadsheet The spreadsheet to search within.
	 * @param value The value to search for.
	 * @return A list of cells containing the specified value.
	 */
	@Override
	public List<Cell> search(Spreadsheet spreadsheet, String value) {
		List<Cell> foundCells = new ArrayList<>();
		try {
			Literal toSearch = new Parser().parseLiteralInput(value);
			SearchVisitor functionSearch = new ValueSearchVisitor(toSearch);
			Iterator<Cell> cellIterator = spreadsheet.getCellIterator();

			while (cellIterator.hasNext()) {
				Cell current = cellIterator.next();
				// Check if the current cell's content matches the function name
				current.getContent().accept(functionSearch);
				if (functionSearch.isFound()) {
					foundCells.add(current);
				}
			}
		}
		catch (UnrecognizedEntryException e) {
			// If the user entry isn't valid, nothing happens
		}
		return foundCells;
	}
	
}


