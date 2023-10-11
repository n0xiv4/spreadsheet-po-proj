package xxl.core;

/**
 * The {@code SubFunction} class represents a subtraction operation in a spreadsheet.
 * It extends the {@link BinaryFunction} class and computes the result of subtracting the second
 * content from the first content.
 */
public class SubFunction extends BinaryFunction {

    /**
     * Constructs a new {@code SubFunction} with two input contents for subtraction.
     *
     * @param arg1 The content from which the subtraction is performed.
     * @param arg2 The content to be subtracted.
     */
    SubFunction(Content arg1, Content arg2) {
        super(arg1, arg2, "SUB");
    }

    /**
     * Computes the result of the subtraction operation and returns it as a {@link LiteralInteger}.
     *
     * @return The result of the subtraction operation as a {@link LiteralInteger}.
     */
    @Override
    protected Literal computeValue() {
        Literal arg0 = super._contents[0].getValue();
        Literal arg1 = super._contents[1].getValue();
        return new LiteralInteger(arg0.getIntValue() - arg1.getIntValue());
    }
}
