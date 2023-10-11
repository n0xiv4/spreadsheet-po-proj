package xxl.core;

/**
 * The {@code LiteralString} class represents a string literal value in a spreadsheet.
 * It extends the {@link Literal} class and provides methods to retrieve the string value
 * and a textual representation of the string.
 */
public class LiteralString extends Literal {
	
	/** The string value stored in the literal. */
	private String _value;

	/**
	 * Constructs a new {@code LiteralString} with the specified string value.
	 *
	 * @param value The string value to be stored in the literal.
	 */
	LiteralString(String value) {
		_value = value;
	}

	/**
	 * Retrieves the integer value of the literal, which is always zero for string literals.
	 *
	 * @return The integer value of zero.
	 */
	@Override
	public int getIntValue() {
		return 0;
	}

	/**
	 * Retrieves the string value of the literal.
	 *
	 * @return The string value of the literal.
	 */
	@Override
	public String getStringValue() {
		return _value;
	}

	/**
	 * Returns a string representation of the {@code LiteralString}.
	 *
	 * @return A string representation of the string value.
	 */
	@Override
	public String toString() {
		return _value;
	}
}
