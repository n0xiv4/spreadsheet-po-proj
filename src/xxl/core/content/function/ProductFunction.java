package xxl.core.content.function;

import java.util.List;

import xxl.core.Interval;
import xxl.core.content.Content;
import xxl.core.content.literal.Literal;
import xxl.core.content.literal.LiteralInteger;
import xxl.core.content.literal.LiteralNullFunction;
import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code ProductFunction} class represents a function that calculates the product of integer values
 * within an interval. If the values cannot be converted to integers, it returns a null value.
 */
public class ProductFunction extends IntervalFunction {

	/**
	 * Constructs a {@code ProductFunction} with the given interval argument.
	 *
	 * @param arg The interval to operate on.
	 */
	public ProductFunction(Interval arg) {
		super(arg, "PRODUCT");
	}

	/**
	 * Computes the result of the PRODUCT function by calculating the product of integer values within the interval.
	 * If any value cannot be converted to an integer, it returns a null value.
	 *
	 * @return A {@code LiteralInteger} containing the product of the integer values, or a {@code LiteralNullFunction} if an error occurs.
	 */
	@Override
	protected Literal computeValue() {
		List<Content> intervalContents = _interval.getContent();
		try {
			int product = 1;
			for (Content content: intervalContents) {
				product = product * content.getValue().getIntValue();
			}
			return new LiteralInteger(product);
		}
		catch (InvalidValueTypeException e) {
			return new LiteralNullFunction();
		}
	}
	
}