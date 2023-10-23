package xxl.core;

import java.util.List;

import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code AverageFunction} class represents a function that calculates the average (mean) of integer values
 * within an interval. If the values cannot be converted to integers, it returns a null value.
 */
public class AverageFunction extends IntervalFunction {

	/**
     * Constructs an {@code AverageFunction} with the given interval argument.
     *
     * @param arg The interval to operate on.
     */
	AverageFunction(Interval arg) {
		super(arg, "AVERAGE");
	}

	/**
     * Computes the result of the AVERAGE function by calculating the average of integer values within the interval.
     * If any value cannot be converted to an integer, it returns a null value.
     *
     * @return A {@code LiteralInteger} containing the average of the integer values, or a {@code LiteralNullFunction} if an error occurs.
     */
	@Override
	protected Literal computeValue() {
		List<Content> intervalContents = _interval.getContent();
		try {
			int sum = 0;
			for (Content content: intervalContents) {
				sum += content.getValue().getIntValue();
			}
			return new LiteralInteger(sum / intervalContents.size());
		}
		catch (InvalidValueTypeException ivte) {
			return new LiteralNullFunction();
		}
	}
}
