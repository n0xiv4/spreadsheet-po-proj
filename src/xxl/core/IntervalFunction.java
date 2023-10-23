package xxl.core;

/**
 * The {@code IntervalFunction} class represents an abstract function in a spreadsheet that operates on an interval.
 * It extends the {@link Function} class and adds the concept of an interval to the function.
 */
public abstract class IntervalFunction extends Function implements Listener {

	/** The interval that belongs to the function. */
	protected Interval _interval;

	/** The current value of the function. Will be recomputed when updated. */
	private Literal _value;

	/**
     * Constructs a new instance of the IntervalFunction class with the specified interval and function name.
     *
     * @param arg The interval that the function operates on.
     * @param functionName The name of the function.
     */
	IntervalFunction(Interval arg, String functionName) {
		super(functionName);
		_interval = arg;
		_interval.addFunctionToCells(this);
		_value = computeValue();
	}

	/**
     * Gets the current value of the interval function.
     *
     * @return The current computed value of the function.
     */
	@Override
	public Literal getValue() {
		return _value;
	}

	/**
     * Called when the function needs to update its value, typically triggered by changes in the interval's cells.
     * It recomputes the function's value based on the current state of the interval.
     */
	@Override 
	public void update() {
		_value = computeValue();
	}

	/**
     * Returns a string representation of the IntervalFunction, including its value, function name, and interval.
     *
     * @return A string in the format "value=functionName(interval)".
     */
	@Override
	public String toString() {
		return getValue() + "=" + getType() + "(" + _interval + ")";
	}
}
