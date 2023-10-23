package xxl.core;

import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code SubFunction} class represents a subtraction operation in a spreadsheet.
 * It extends the {@link BinaryFunction} class and computes the result of subtracting the second
 * content from the first content.
 */
public class SubFunction extends BinaryFunction {

	/**
	 * Constructs a new {@code SubFunction} with two input contents for subtraction.
	 *
	 * @param arg1 The content from which the subtraction is performed.
	 * @param arg2 The content to be subtracted.
	 */
	SubFunction(Content arg1, Content arg2) {
		super(arg1, arg2, "SUB");
	}

	/**
	 * Computes the result of the subtraction operation and returns it as a {@link LiteralInteger}.
	 *
	 * @return The result of the subtraction operation as a {@link LiteralInteger}.
	 */
	@Override
	protected Literal computeValue() {
		Literal arg1 = super._contents[0].getValue();
		Literal arg2 = super._contents[1].getValue();
		try {
			return new LiteralInteger(arg1.getIntValue() - arg2.getIntValue());
		}
		catch (InvalidValueTypeException e) {
			return new LiteralNullFunction();
		}
	}
}
