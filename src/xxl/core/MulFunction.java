package xxl.core;

public class MulFunction extends BinaryFunction {

    MulFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    @Override
    protected Literal computeValue() {
        return Literal.mul(super._contents[0].getValue(), super._contents[1].getValue());
    }

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
