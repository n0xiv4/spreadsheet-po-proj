package xxl.core;

import java.util.List;

import xxl.core.exception.InvalidValueTypeException;

public class CoalesceFunction extends IntervalFunction{
    CoalesceFunction(Interval arg) {
		super(arg, "COALESCE");
	}
	
	@Override
	protected Literal computeValue() {
		List<Content> intervalContents = _interval.getContent();
			for (Content content: intervalContents) {
				try {
					return new LiteralString(content.getValue().getStringValue());
				}
				catch (InvalidValueTypeException ivte) {
				}
			}
			return new LiteralString("");
	}
}
