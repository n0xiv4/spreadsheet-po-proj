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
}