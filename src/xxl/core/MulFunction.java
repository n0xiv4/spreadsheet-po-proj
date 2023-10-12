package xxl.core;

import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code MulFunction} class represents a multiplication function in a spreadsheet.
 * It extends the {@link BinaryFunction} class and provides methods to calculate the result
 * of multiplying two content values and generate a string representation of the multiplication operation.
 */
public class MulFunction extends BinaryFunction {

	/**
	 * Constructs a new {@code MulFunction} with the specified arguments.
	 *
	 * @param arg1 The first content argument to be multiplied.
	 * @param arg2 The second content argument to be multiplied.
	 */
	MulFunction(Content arg1, Content arg2) {
		super(arg1, arg2, "MUL");
	}

	/**
	 * Calculates the result of multiplying the two content values represented by this function.
	 *
	 * @return A {@link LiteralInteger} representing the result of the multiplication.
	 */
	@Override
	protected Literal computeValue() {
		Literal arg1 = super._contents[0].getValue();
		Literal arg2 = super._contents[1].getValue();
		try {
			return new LiteralInteger(arg1.getIntValue() * arg2.getIntValue());
		}
		catch (InvalidValueTypeException e) {
			return new LiteralString("#VALUE");
		}
	}
}

