package xxl.core;

import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code DivFunction} class represents a division operation in a spreadsheet.
 * It extends the {@link BinaryFunction} class and computes the result of dividing the first
 * content by the second content.
 */
public class DivFunction extends BinaryFunction {

	/**
	 * Constructs a new {@code DivFunction} with two input contents for division.
	 *
	 * @param arg1 The content from which the division is performed.
	 * @param arg2 The content by which the division is performed.
	 */
	DivFunction(Content arg1, Content arg2) {
		super(arg1, arg2, "DIV");
	}

	/**
	 * Computes the result of the division operation and returns it as a {@link LiteralInteger}.
	 *
	 * @return The result of the division operation as a {@link LiteralInteger}.
	 */
	@Override
	protected Literal computeValue() {
		Literal arg1 = super._contents[0].getValue();
		Literal arg2 = super._contents[1].getValue();
		try {
			return new LiteralInteger(arg1.getIntValue() / arg2.getIntValue());
		}
		catch (InvalidValueTypeException | ArithmeticException e) {
			return new LiteralNullFunction();
		}
	}
}

