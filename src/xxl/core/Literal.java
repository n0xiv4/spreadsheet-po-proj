package xxl.core;

public abstract class Literal extends Content {
	/**
	 * Abstract getValue for child classes
	 *
	 * @returns a Literal version of the current
	 * literal type (Integer or String)
	 */
	@Override
	Literal getValue() {
		return this;
	}

	abstract public int getIntValue();
	abstract public String getStringValue();

	// FIXME maybe toString here too ??

	// OPERATIONS WITH LITERALS (MAKING LITERAL A USABLE TYPE FOR
	// ARITHMETIC OPERATIONS)

	/**
     * Adds two {@link Literal} objects and returns the result as a new {@link LiteralInteger}.
     *
     * @param a The first {@link Literal} to be added.
     * @param b The second {@link Literal} to be added.
     * @return A new {@link LiteralInteger} representing the result of the addition.
     */
	static Literal add(Literal a, Literal b) {
		return new LiteralInteger(a.getIntValue() + b.getIntValue());
	}

	/**
     * Subtracts one {@link Literal} object from another and returns the result as a new {@link LiteralInteger}.
     *
     * @param a The {@link Literal} to be subtracted from.
     * @param b The {@link Literal} to subtract.
     * @return A new {@link LiteralInteger} representing the result of the subtraction.
     */
    static Literal sub(Literal a, Literal b) {
        return new LiteralInteger(a.getIntValue() - b.getIntValue());
    }

    /**
     * Multiplies two {@link Literal} objects and returns the result as a new {@link LiteralInteger}.
     *
     * @param a The first {@link Literal} to be multiplied.
     * @param b The second {@link Literal} to be multiplied.
     * @return A new {@link LiteralInteger} representing the result of the multiplication.
     */
    static Literal mul(Literal a, Literal b) {
        return new LiteralInteger(a.getIntValue() * b.getIntValue());
    }

    /**
     * Divides one {@link Literal} object by another and returns the result as a new {@link LiteralInteger}.
     *
     * @param a The {@link Literal} to be divided.
     * @param b The {@link Literal} to divide by.
     * @return A new {@link LiteralInteger} representing the result of the division.
     */
    static Literal div(Literal a, Literal b) {
        return new LiteralInteger(a.getIntValue() / b.getIntValue());
    }
}