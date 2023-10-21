package xxl.core;

import java.util.List;

import xxl.core.exception.InvalidValueTypeException;

public class ProductFunction extends IntervalFunction{
    ProductFunction(Interval arg) {
		super(arg, "PRODUCT");
	}

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