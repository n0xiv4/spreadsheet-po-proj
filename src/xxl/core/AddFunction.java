package xxl.core;

public class AddFunction extends BinaryFunction {

    AddFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    protected Literal computeValue() {
        return Literal.add(super._contents[0].getValue(), super._contents[1].getValue());
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
