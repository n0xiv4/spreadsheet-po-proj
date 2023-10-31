package xxl.core.search;

import xxl.core.content.Reference;
import xxl.core.content.function.Function;
import xxl.core.content.literal.Literal;

/**
 * A visitor for searching values within a spreadsheet.
 */
public class ValueSearchVisitor extends SearchVisitor {

	/** The user input (of which value to search). */
	private Literal _valueToSearch;

	/**
	 * Creates a new ValueSearchVisitor with the specified user input value to search for.
	 *
	 * @param userInput The value to search for within the spreadsheet.
	 */
	public ValueSearchVisitor(Literal userInput) {
		_valueToSearch = userInput;
	}

	/**
	 * Visits a literal content within the spreadsheet and checks if it matches the search value.
	 *
	 * @param literal The literal content to visit.
	 */
	@Override
	public void visit(Literal literal) {
		_found = literal.equals(_valueToSearch);
	}

	/**
	 * Visits a reference content within the spreadsheet and checks if its value matches the search value.
	 *
	 * @param reference The reference content to visit.
	 */
	@Override
	public void visit(Reference reference) {
		_found = reference.getValue().equals(_valueToSearch);
	}

	/**
	 * Visits a function content within the spreadsheet and checks if its value matches the search value.
	 *
	 * @param function The function content to visit.
	 */
	@Override
	public void visit(Function function) {
		_found = function.getValue().equals(_valueToSearch);
	}

	/**
	 * Retrieves the value obtained during the last visit.
	 *
	 * @return The value obtained during the last visit (currently not implemented).
	 */
	@Override
	public String getVisitValue() {
		return null;
	}
}
