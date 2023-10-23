package xxl.core;

import java.util.List;

import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code CoalesceFunction} class represents a function that returns the first non-null string value
 * found within an interval, or an empty string if all values are null.
 */
public class CoalesceFunction extends IntervalFunction {

	/**
     * Constructs a {@code CoalesceFunction} with the given interval argument.
     *
     * @param arg The interval to operate on.
     */
    CoalesceFunction(Interval arg) {
		super(arg, "COALESCE");
	}
	
	/**
     * Computes the result of the COALESCE function by returning the first non-null string value found
     * within the interval, or an empty string if all values are null.
     *
     * @return A {@code LiteralString} containing the first non-null string value or an empty string.
     */
	@Override
	protected Literal computeValue() {
		List<Content> intervalContents = _interval.getContent();
		for (Content content: intervalContents) {
			try {
				return new LiteralString(content.getValue().getStringValue());
			}
			catch (InvalidValueTypeException e) {
			}
		}
		return new LiteralString("");
	}
}
