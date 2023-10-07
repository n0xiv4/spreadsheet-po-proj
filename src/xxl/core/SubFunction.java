package xxl.core;

public class SubFunction extends BinaryFunction {

    SubFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    @Override
    protected Literal computeValue() {
        return Literal.sub(super._contents[0].getValue(), super._contents[1].getValue());
    }

    // FIXME... what's the diff between getValue and computeValue ?
    @Override
    Literal getValue() {
        return computeValue();
    }

    @Override
    public String toString() {
        // FIXME
        return "";
    }
}
