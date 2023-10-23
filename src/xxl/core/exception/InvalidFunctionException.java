package xxl.core.exception;

public class InvalidFunctionException extends UnrecognizedEntryException {

	/**
	 * @param functionName
	 */
	public InvalidFunctionException(String functionName) {
		super(functionName);
	}
}
