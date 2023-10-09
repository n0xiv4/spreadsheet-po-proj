package xxl.core;

/**
 * The {@code LiteralInteger} class represents an integer literal value in a spreadsheet.
 * It extends the {@link Literal} class and provides methods to retrieve the integer value,
 * string representation, and a textual representation of the integer.
 */
public class LiteralInteger extends Literal {

    /** The integer value stored in the literal. */
    private int _value;

    /**
     * Constructs a new {@code LiteralInteger} with the specified integer value.
     *
     * @param value The integer value to be stored in the literal.
     */
    LiteralInteger(int value) {
        _value = value;
    }

    /**
     * Retrieves the integer value of the literal.
     *
     * @return The integer value of the literal.
     */
    @Override
    public int getIntValue() {
        return _value;
    }

    /**
     * Retrieves the string representation of the integer value.
     *
     * @return The string representation of the integer.
     */
    @Override
    public String getStringValue() {
        return "";
    }

    /**
     * Returns a string representation of the {@code LiteralInteger}.
     *
     * @return A string representation of the integer value.
     */
    @Override
    public String toString() {
        return Integer.toString(_value);
    }
}
