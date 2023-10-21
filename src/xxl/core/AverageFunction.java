package xxl.core;

import java.util.List;

import xxl.core.exception.InvalidValueTypeException;

public class AverageFunction extends IntervalFunction {

	AverageFunction(Interval arg) {
		super(arg, "AVERAGE");
	}

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
