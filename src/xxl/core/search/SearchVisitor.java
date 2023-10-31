package xxl.core.search;

import xxl.core.content.Reference;
import xxl.core.content.function.Function;
import xxl.core.content.literal.Literal;

/**
 * An abstract class for visiting and searching for values in a spreadsheet.
 */
abstract public class SearchVisitor {

	/** If a search is succeeded correctly and the value is found. */
	protected boolean _found;

	/**
	 * Visits a literal content for search purposes.
	 *
	 * @param literal The literal node to visit.
	 */
	abstract public void visit(Literal literal);

	/**
	 * Visits a reference content for search purposes.
	 *
	 * @param reference The reference node to visit.
	 */
	abstract public void visit(Reference reference);

	/**
	 * Visits a function content for search purposes.
	 *
	 * @param function The function node to visit.
	 */
	abstract public void visit(Function function);

	/**
	 * Retrieves the value obtained during the last visit.
	 *
	 * @return The value obtained during the last visit.
	 */
	abstract public String getVisitValue();

	/**
	 * Checks if a value has been found during the search.
	 *
	 * @return {@code true} if a value has been found, {@code false} otherwise.
	 */
	public boolean isFound() {
		return _found;
	}
	
}
