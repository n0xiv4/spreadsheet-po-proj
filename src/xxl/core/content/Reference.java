package xxl.core.content;

import xxl.core.Position;
import xxl.core.ReferenceUpdateVisitor;
import xxl.core.Spreadsheet;
import xxl.core.content.literal.Literal;
import xxl.core.exception.InvalidValueTypeException;
import xxl.core.search.SearchVisitor;

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
	public Reference(Position position, Spreadsheet spreadsheet) {
		_referencePosition = position;
		_spreadsheet = spreadsheet;
	}

	/**
	 * Retrieves the content of the cell referenced by this object.
	 *
	 * @return The content of the referenced cell.
	 */
	public Content getContent() {
		return _spreadsheet.getCell(_referencePosition).getContent();
	}

	/**
	 * Adds an observer to the cell referenced by this object.
	 *
	 * @param observer The observer to be added to the referenced cell.
	 */
	public void addObserver(Observer observer) {
		_spreadsheet.getCell(_referencePosition).addObserver(observer);
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
				referenceValue = "'" + getValue().getStringValue();
			}
			// If we still can't get a string value, the value is "#VALUE"
			catch (InvalidValueTypeException ivte) {
				referenceValue = "#VALUE";
			}
		}
		return referenceValue + "=" + _referencePosition.toString();
	}

	/**
	 * Retrieves the value of the referenced cell from the associated spreadsheet.
	 *
	 * @return The value of the referenced cell as a {@link Literal}.
	 */
	@Override
	public Literal getValue() {
		return _spreadsheet.getCell(_referencePosition).getValue();
	}

	/**
	 * Accepts a search visitor to visit and process this object.
	 *
	 * @param visitor The search visitor to accept.
	 */
	@Override
	public void accept(SearchVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Accepts a reference update visitor along with an observer to visit and update this object.
	 *
	 * @param visitor The reference update visitor to accept.
	 * @param observer The observer to be used for updates.
	 */
	@Override
	public void accept(ReferenceUpdateVisitor visitor, Observer observer) {
		visitor.visit(this, observer);
	}

}
