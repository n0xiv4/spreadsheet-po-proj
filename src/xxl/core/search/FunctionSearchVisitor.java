package xxl.core.search;

import xxl.core.content.Reference;
import xxl.core.content.function.Function;
import xxl.core.content.literal.Literal;

/**
 * The {@code FunctionSearch} class is an implementation of the {@link Visitor} interface
 * that is used to search for a specific function by name within the content hierarchy.
 */
public class FunctionSearchVisitor extends SearchVisitor {

	/** The user input (of which function to search). */
	private String _functionToSearch;

	/** The gathered functionName, if it matches. */
	private String _functionName;

	/**
	 * Creates a new {@code FunctionSearch} instance with the function name to search for.
	 *
	 * @param userInput The function name to search for.
	 */
	public FunctionSearchVisitor(String userInput) {
		_functionToSearch = userInput;
	}

	/**
	 * Visits a {@link Literal} content element and returns false.
	 *
	 * @param literal The {@link Literal} to visit.
	 * @return false.
	 */
	@Override
	public void visit(Literal literal) {
		_found = false;
	}

	/**
	 * Visits a {@link Reference} content element and returns false.
	 *
	 * @param reference The {@link Reference} to visit.
	 * @return false.
	 */
	@Override
	public void visit(Reference reference) {
		_found = false;
	}

	/**
	 * Visits a {@link Function} content element and checks if the function name contains the specified search criteria.
	 *
	 * @param function The {@link Function} to visit.
	 * @return true if the function name contains the search criteria, false otherwise.
	 */
	@Override
	public void visit(Function function) {
		if (function.getFunctionName().contains(_functionToSearch)) {
			_functionName = function.getFunctionName();
			_found = true;
		}
		else {
			_found = false;
		}
	}

	/**
	 * Retrieves the value obtained during the last visit.
	 *
	 * @return The value obtained during the last visit.
	 */
	@Override
	public String getVisitValue() {
		return _functionName;
	}
}
