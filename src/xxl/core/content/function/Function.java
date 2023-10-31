package xxl.core.content.function;

import xxl.core.ReferenceUpdateVisitor;
import xxl.core.content.Content;
import xxl.core.content.Observer;
import xxl.core.content.literal.Literal;
import xxl.core.search.SearchVisitor;

/**
 * The {@code Function} class represents an abstract function in a spreadsheet.
 * It extends the {@link Content} class and defines the structure for spreadsheet functions.
 * Subclasses of this class must implement the {@link #computeValue()} method to calculate
 * the result of the function.
 */
public abstract class Function extends Content {

	/** The name of the function. */
	private final String _functionName;

	/**
	 * Constructs a new instance of the {@code Function} class with the specified function name.
	 *
	 * @param functionName The name of the function.
	 */
	protected Function(String functionName) {
		_functionName = functionName;
	}

	/**
	 * Gets the function name of this function.
	 *
	 * @return The function name.
	 */
	public String getFunctionName() {
		return _functionName;
	}

	/**
	 * Returns a string representation of the function.
	 *
	 * @return A string representation of the function.
	 */
	public abstract String toString();

	/**
	 * Computes the result of the function and returns it as a {@link Literal}.
	 *
	 * @return The result of the function as a {@link Literal}.
	 */
	protected abstract Literal computeValue();

	/**
	 * Accepts a search visitor to visit and process this object.
	 *
	 * @param visitor The search visitor to accept.
	 */
	@Override
	public void accept(SearchVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Accepts a reference update visitor along with an observer to visit and update this object.
	 *
	 * @param visitor The reference update visitor to accept.
	 * @param observer The observer to be used for updates.
	 */
	@Override
	public void accept(ReferenceUpdateVisitor visitor, Observer observer) {
		visitor.visit(this, observer);
	}

}

