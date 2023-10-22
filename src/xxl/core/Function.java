package xxl.core;

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
	Function(String functionName) {
		_functionName = functionName;
	}

	// FIXME javadoc
	@Override
	public String getType() {
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
}

