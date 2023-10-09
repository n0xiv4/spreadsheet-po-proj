package xxl.core;

public class AddFunction extends BinaryFunction {

    AddFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    @Override
    protected Literal computeValue() {
        Literal arg1 = super._contents[0].getValue();
        Literal arg2 = super._contents[1].getValue();
        return new LiteralInteger(arg1.getIntValue() + arg2.getIntValue());
    }

    @Override
    public String toString() {
        // FIXME
        return computeValue().getIntValue() + "=ADD(" + _contents[0].toString() + _contents[1].toString() + ")";
    }
}
