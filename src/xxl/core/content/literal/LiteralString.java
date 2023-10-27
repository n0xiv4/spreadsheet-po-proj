package xxl.core.content.literal;

import xxl.core.exception.InvalidValueTypeException;

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
	public LiteralString(String value) {
		_value = value;
	}

	/**
	 * Retrieves the integer value of the literal, which is always zero for string literals.
	 *
	 * @throws InvalidValueTypeException Because this literal is a string one,
	 * so no integer value can be returned.
	 */
	@Override
	public int getIntValue() throws InvalidValueTypeException {
		throw new InvalidValueTypeException();
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
		return "'" + _value;
	}
	
	/**
	 * Compares this object with another object to determine if they are equal.
	 *
	 * @param object The object to compare with this object.
	 * @return {@code true} if this object is equal to the provided object, {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object object) {
		return hashCode() == object.hashCode();
	}

	/**
	 * Computes the hash code value for this object based on its internal value.
	 *
	 * @return The computed hash code for this object.
	 */
	@Override
	public int hashCode() {
		return _value.hashCode();
	}
	
}
