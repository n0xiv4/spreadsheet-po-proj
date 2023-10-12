package xxl.core;

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
		return parseLiteralValue() + "=" + _referencePosition.toString();
	}

	/**
	 * Retrieves the value of the referenced cell from the associated spreadsheet.
	 *
	 * @return The value of the referenced cell as a {@link Literal}.
	 */
	Literal getValue() {
		return _spreadsheet.getValueInPosition(_referencePosition);
	}

	/**
	 * Parses the value of a literal to a string representation, considering the special case
	 * where the value is a {@link LiteralNull}.
	 *
	 * @return A string representation of the literal's value, or {@code #VALUE} if the value is an empty string.
	 */
	private String parseLiteralValue() {
		if ("".equals(getValue().toString())) {
			return "#VALUE";
		}
		return getValue().toString();
	}
}
