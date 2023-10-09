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
        String arg1 = parseArgument(_contents[0].toString());
        String arg2 = parseArgument(_contents[1].toString());
        return computeValue().getIntValue() + "=ADD(" + arg1 + "," + arg2 + ")";
    }

    private String parseArgument(String argument) {
        if (argument.contains("=")) {
            String[] args = argument.split("=");
            return args[1];
        }
        return argument;
    }
}
