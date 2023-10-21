package xxl.core;

import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code LiteralNull} class represents a null or empty literal value in a spreadsheet.
 * It extends the {@link Literal} class and provides methods to retrieve default values for numeric
 * and string representations of a null literal.
 */
public abstract class LiteralNull extends Literal {

	/**
	 * Returns the default integer value for a null literal, which is 0.
	 *
	 * @throws InvalidValueTypeException Because this literal is a null one,
	 * so no integer value can be returned.
	 */
	@Override
	public int getIntValue() throws InvalidValueTypeException {
		throw new InvalidValueTypeException();
	}

	/**
	 * Returns the default string value for a null literal, which is an empty string ("").
	 *
	 * @throws InvalidValueTypeException Because this literal is a null one,
	 * so no string value can be returned.
	 */
	@Override
	public String getStringValue() throws InvalidValueTypeException {
		throw new InvalidValueTypeException();
	}
}
