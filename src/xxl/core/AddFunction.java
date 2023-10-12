package xxl.core;

import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code AddFunction} class represents an addition operation in a spreadsheet.
 * It extends the {@link BinaryFunction} class and computes the sum of two content values.
 */
public class AddFunction extends BinaryFunction {

	/**
	 * Constructs a new {@code AddFunction} with two input contents for addition.
	 *
	 * @param arg1 The first content to be added.
	 * @param arg2 The second content to be added.
	 */
	AddFunction(Content arg1, Content arg2) {
		super(arg1, arg2, "ADD");
	}

	/**
	 * Computes the result of the addition operation and returns it as a {@link LiteralInteger}.
	 *
	 * @return The result of the addition operation as a {@link LiteralInteger}.
	 */
	@Override
	protected Literal computeValue() {
		Literal arg1 = super._contents[0].getValue();
		Literal arg2 = super._contents[1].getValue();
		try {
			return new LiteralInteger(arg1.getIntValue() + arg2.getIntValue());
		}
		catch (InvalidValueTypeException e) {
			return new LiteralString("#VALUE");
		}
	}
}
