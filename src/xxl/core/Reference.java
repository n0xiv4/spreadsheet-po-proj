package xxl.core;

import xxl.core.exception.InvalidValueTypeException;

/**
 * The {@code Reference} class represents a reference to a cell in a spreadsheet.
 * It extends the {@link Content} class and provides a way to access the value of a referenced cell.
 * A reference is associated with a specific position in the spreadsheet.
 */
public class Reference extends Content {

	/** The position of the referenced cell. */
	private Position _referencePosition;

	/** The spreadsheet to which the reference belongs. */
	private Spreadsheet _spreadsheet;

	/**
	 * Constructs a new {@code Reference} object with the specified position and associated spreadsheet.
	 *
	 * @param position    The position of the referenced cell.
	 * @param spreadsheet The spreadsheet to which the reference belongs.
	 */
	Reference(Position position, Spreadsheet spreadsheet) {
		_referencePosition = position;
		_spreadsheet = spreadsheet;
	}

	/**
	 * Returns a string representation of the reference in the format "value=position".
	 *
	 * @return A string in the format "value=position" where value is the referenced cell's value,
	 *         and position is the position of the referenced cell.
	 */
	@Override
	public String toString() {
		String referenceValue;
		try {
			referenceValue = Integer.toString(getValue().getIntValue());
		} 
		catch (InvalidValueTypeException e) {
			// If we can't get an int value, we try to get a string one
			try {
				referenceValue = getValue().getStringValue();
			}
			// If we still can't get a string value, the value is "#VALUE"
			catch (InvalidValueTypeException ivte) {
				referenceValue = "#VALUE";
			}
		}
		return referenceValue + "=" + _referencePosition.toString();
	}

	/**
	 * Gets the type of this object.
	 *
	 * @return The type, which is "REFERENCE."
	 */
	@Override
	public String getType() {
		return "REFERENCE";
	}

	/**
	 * Retrieves the value of the referenced cell from the associated spreadsheet.
	 *
	 * @return The value of the referenced cell as a {@link Literal}.
	 */
	Literal getValue() {
		return _spreadsheet.getCell(_referencePosition).getValue();
	}
}
