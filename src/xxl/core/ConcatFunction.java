package xxl.core;

import java.util.List;

import xxl.core.exception.InvalidValueTypeException;

public class ConcatFunction extends IntervalFunction{
    ConcatFunction(Interval arg) {
		super(arg, "CONCAT");
	}

    @Override
	protected Literal computeValue() {
		List<Content> intervalContents = _interval.getContent();
        String concatStrings = "";
        for (Content content: intervalContents) {
            try {
                //FIXME
                concatStrings += content.getValue().getStringValue().split("'")[1];
            }
            catch (InvalidValueTypeException ivte) {
            }
        }
        return new LiteralString(concatStrings);
	}
}
