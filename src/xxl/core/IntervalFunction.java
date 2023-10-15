package xxl.core;

public abstract class IntervalFunction extends Function {

	protected Interval _interval;

	/** The name of the binary function. */
	private final String _functionName;

	IntervalFunction(Interval arg, String functionName) {
		_interval = arg;
		_functionName = functionName;
	}

	@Override
	public String toString() {
		return getValue() + "=" + _functionName + "(" + _interval + ")";
	}
}
