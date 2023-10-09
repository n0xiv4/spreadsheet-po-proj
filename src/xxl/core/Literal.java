package xxl.core;

/**
 * The {@code Literal} abstract class represents a literal value in a spreadsheet.
 * It serves as the base class for specific literal types such as integers, strings, or null values.
 * Subclasses must implement methods to retrieve integer and string values.
 */
public abstract class Literal extends Content {

    /**
     * Retrieves the value of the literal as an integer.
     *
     * @return The integer value of the literal.
     */
    abstract public int getIntValue();

    /**
     * Retrieves the value of the literal as a string.
     *
     * @return The string representation of the literal.
     */
    abstract public String getStringValue();

    /**
     * Returns the literal itself as its value.
     *
     * @return The literal object itself.
     */
    @Override
    Literal getValue() {
        return this;
    }
}
