package xxl.core;

/**
 * The {@code LiteralNull} class represents a null or empty literal value in a spreadsheet.
 * It extends the {@link Literal} class and provides methods to retrieve default values for numeric
 * and string representations of a null literal.
 */
public class LiteralNull extends Literal {
    
    /**
     * Returns the default integer value for a null literal, which is 0.
     *
     * @return The integer value of the null literal.
     */
    @Override
    public int getIntValue() {
        return 0;
    }

    /**
     * Returns the default string value for a null literal, which is an empty string ("").
     *
     * @return The string representation of the null literal.
     */
    @Override
    public String getStringValue() {
        return "";
    }

    /**
     * Overrides the default {@link Object#toString()} method to return a marker symbol "#"
     * representing a null or empty literal.
     *
     * @return A string representation of the null literal ("#VALUE").
     */
    public String toString() {
        return "#VALUE";
    }
}
