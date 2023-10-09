package xxl.core;

/**
 * The {@code Function} class represents an abstract function in a spreadsheet.
 * It extends the {@link Content} class and defines the structure for spreadsheet functions.
 * Subclasses of this class must implement the {@link #computeValue()} method to calculate
 * the result of the function.
 */
public abstract class Function extends Content {

    /**
     * Computes the result of the function and returns it as a {@link Literal}.
     *
     * @return The result of the function as a {@link Literal}.
     */
    protected abstract Literal computeValue();

    /**
     * Returns a string representation of the function.
     *
     * @return A string representation of the function.
     */
    public abstract String toString();

    /**
     * Retrieves the value of the function, which is calculated by invoking {@link #computeValue()}.
     *
     * @return The computed value of the function as a {@link Literal}.
     */
    @Override
    public Literal getValue() {
        return computeValue();
    }
}

