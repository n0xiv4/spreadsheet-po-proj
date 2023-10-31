package xxl.core.content.literal;

import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code LiteralInteger} class represents an integer literal value in a spreadsheet.
 * It extends the {@link Literal} class and provides methods to retrieve the integer value,
 * string representation, and a textual representation of the integer.
 */
public class LiteralInteger extends Literal {

	/** The integer value stored in the literal. */
	private int _value;

	/**
	 * Constructs a new {@code LiteralInteger} with the specified integer value.
	 *
	 * @param value The integer value to be stored in the literal.
	 */
	public LiteralInteger(int value) {
		_value = value;
	}

	/**
	 * Retrieves the integer value of the literal.
	 *
	 * @return The integer value of the literal.
	 */
	@Override
	public int getIntValue() {
		return _value;
	}

	/**
	 * Retrieves the string representation of the integer value.
	 *
	 * @throws InvalidValueTypeException Because this literal is an integer one,
	 * so no string value can be returned.
	 */
	@Override
	public String getStringValue() throws InvalidValueTypeException {
		throw new InvalidValueTypeException();
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
	 * Computes the hash code value for this object based on its internal integer value.
	 *
	 * @return The computed hash code for this object.
	 */
	@Override
	public int hashCode() {
		return _value;
	}

	/**
	 * Returns a string representation of the {@code LiteralInteger}.
	 *
	 * @return A string representation of the integer value.
	 */
	@Override
	public String toString() {
		return Integer.toString(_value);
	}
}
