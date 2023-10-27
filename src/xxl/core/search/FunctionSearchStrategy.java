package xxl.core.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import xxl.core.Cell;
import xxl.core.Spreadsheet;

/**
 * Implementation of a search strategy that uses a FunctionSearchVisitor to search for cells
 * in a spreadsheet containing a specific function name.
 */
public class FunctionSearchStrategy implements SearchStrategy {

	/**
	 * Searches for cells in the given spreadsheet that contain a specific function name.
	 *
	 * @param spreadsheet The spreadsheet to search.
	 * @param functionName The function name to search for.
	 * @return A list of cells containing the specified function name.
	 */
	@Override
	public List<Cell> search(Spreadsheet spreadsheet, String functionName) {
		/** A map to store which cells belong to each function. */
		Map<String, List<Cell>> cellMap = new TreeMap<String, List<Cell>>();

		Iterator<Cell> cellIterator = spreadsheet.getCellIterator();
		SearchVisitor functionSearch = new FunctionSearchVisitor(functionName);

		while (cellIterator.hasNext()) {
			Cell current = cellIterator.next();
			// Check if the current cell's content matches the function name
			current.getContent().accept(functionSearch);
			if (functionSearch.isFound()) {
				String matchingName = functionSearch.getVisitValue();
				if (cellMap.get(matchingName) == null) {
					cellMap.put(matchingName, new ArrayList<Cell>());
				}
				cellMap.get(matchingName).add(current);
			}
		}

		// Grabs all cells (values) in the cellMap and then flattens the lists
		List<Cell> sortedCells = cellMap.values().stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toList());

		return sortedCells;
	}
	
}
