package xxl.core;

/**
 * The {@code BinaryFunction} class represents an abstract binary function in a spreadsheet.
 * It extends the {@link Function} class and provides a common structure for binary operations.
 * Binary functions take two content values as input and produce a result based on a specific function.
 */
public abstract class BinaryFunction extends Function {

    /** The array to store the two input contents for the binary operation. */
    protected Content[] _contents;

    /** The name of the binary function. */
    private final String _functionName;

    /**
     * Constructs a new {@code BinaryFunction} with two input contents and a specified function name.
     *
     * @param arg1         The first content input for the binary operation.
     * @param arg2         The second content input for the binary operation.
     * @param functionName The name of the binary function.
     */
    BinaryFunction(Content arg1, Content arg2, String functionName) {
        _contents = new Content[2];
        _contents[0] = arg1;
        _contents[1] = arg2;
        _functionName = functionName;
    }

    /**
     * Returns a string representation of the binary function and its computed value.
     *
     * @return A string in the format "value=functionName(arg1,arg2)".
     */
    @Override
    public String toString() {
        String arg1 = parseArgument(_contents[0].toString());
        String arg2 = parseArgument(_contents[1].toString());
        return computeValue() + "=" + _functionName + "(" + arg1 + "," + arg2 + ")";
    }

    /**
     * Parses an argument string to extract the wanted portion.
     * If the argument is a {@link Reference}, we'll be looking for the "row;column" portion.
     * Otherwise, the value will be returned.
     *
     * @param argument The argument string to parse.
     * @return The value portion of the argument string.
     */
    private String parseArgument(String argument) {
        if (argument.contains("=")) {
            String[] args = argument.split("=");
            return args[1];
        }
        return argument;
    }
}
