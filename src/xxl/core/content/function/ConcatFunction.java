package xxl.core.content.function;

import java.util.List;

import xxl.core.Interval;
import xxl.core.content.Content;
import xxl.core.content.literal.Literal;
import xxl.core.content.literal.LiteralString;
import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code ConcatFunction} class represents a function that concatenates the string values of
 * contents within an interval.
 */
public class ConcatFunction extends IntervalFunction {

    /**
     * Constructs a {@code ConcatFunction} with the given interval argument.
     *
     * @param arg The interval to operate on.
     */
    public ConcatFunction(Interval arg) {
		super(arg, "CONCAT");
	}

    /**
     * Computes the result of the CONCAT function by concatenating the string values of contents
     * within the interval.
     *
     * @return A {@code LiteralString} containing the concatenated string values.
     */
    @Override
	protected Literal computeValue() {
		List<Content> intervalContents = _interval.getContent();
        String concatStrings = "";
        for (Content content: intervalContents) {
            try {
                // Concatenates each String
                concatStrings += content.getValue().getStringValue();
            }
            catch (InvalidValueTypeException e) {
            }
        }
        return new LiteralString(concatStrings);
	}
    
}
