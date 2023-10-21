package xxl.core;

/**
 * The {@code IntervalFunction} class represents an abstract function in a spreadsheet that operates on an interval.
 * It extends the {@link Function} class and adds the concept of an interval to the function.
 */
public abstract class IntervalFunction extends Function {

	/** The interval that belongs to the function. */
	protected Interval _interval;

	/**
     * Constructs a new instance of the IntervalFunction class with the specified interval and function name.
     *
     * @param arg The interval that the function operates on.
     * @param functionName The name of the function.
     */
	IntervalFunction(Interval arg, String functionName) {
		super(functionName);
		_interval = arg;
	}

	/**
     * Returns a string representation of the IntervalFunction, including its value, function name, and interval.
     *
     * @return A string in the format "value=functionName(interval)".
     */
	@Override
	public String toString() {
		return getValue() + "=" + getFunctionName() + "(" + _interval + ")";
	}
}
