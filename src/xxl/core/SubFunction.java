package xxl.core;

public class SubFunction extends BinaryFunction {

    SubFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    @Override
    protected Literal computeValue() {
        Literal arg0 = super._contents[0].getValue();
        Literal arg1 = super._contents[1].getValue();
        return new LiteralInteger(arg0.getIntValue() - arg1.getIntValue());
    }

    @Override
    public String toString() {
        // FIXME
        return "";
    }
}
