package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Position} class represents a two-dimensional position or coordinate
 * consisting of a row and a column, that belongs to the {@link Cell}.
 * 
 * @Serial 202310112120L
 */
public class Position implements Comparable<Position>, Serializable {
	
	/** The row coordinate. */
	private int _row;

	/** The column coordinate. */
	private int _column;

	/** The serial version UID for ensuring version compatibility during serialization. */
	@Serial
	private static final long serialVersionUID = 202310112120L;

	/**
	 * Constructs a new {@link Position} object with the specified row and column values.
	 *
	 * @param row    The row value for the position.
	 * @param column The column value for the position.
	 */
	Position(int row, int column) {
		_row = row;
		_column = column;
	}

	/**
	 * Checks if two positions are compatible for defining an interval.
	 * Two positions are considered compatible if they meet one of the following conditions:
	 * - They have the same row, and their columns are in ascending order.
	 * - They have the same column, and their rows are in ascending order.
	 *
	 * @param position The {@link Position} to be checked compatibility with.
	 * @return {@code true} if the positions are compatible for defining an interval, {@code false} otherwise.
	 */
	public boolean isCompatibleForInterval(Position position) {
		// Check if either position is not valid
		if (!isValid() || !position.isValid()) {
			return false;
		}

		// Check if rows are equal and columns are in ascending order
		if (getRow() == position.getRow() && getColumn() <= position.getColumn()) {
			return true;
		}

		// Check if columns are equal and rows are in ascending order
		if (getColumn() == position.getColumn() && getRow() <= position.getRow()) {
			return true;
		}

		return false;
	}

	/**
	 * Indicates whether some other object is "equal to" this one. This method compares the hash code
	 * of the current Position object with the hash code of the given object.
	 *
	 * @param obj The object to compare to this Position.
	 * @return {@code true} if the hash codes are equal, indicating potential equality; {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		return hashCode() == obj.hashCode();
	}

	/**
	 * Returns a hash code value for the Position. The hash code is computed based on the row and
	 * column coordinates, which are used to uniquely represent this Position object.
	 *
	 * @return A hash code value for this Position.
	 */
	@Override
	public int hashCode() {
		int hash = 367;
		hash = 401 * hash + _row;
		hash = 401 * hash + _column;
		return hash;
	}

	/**
	 * Generates and returns a list of Position objects representing all positions within the
	 * rectangular region up to and including the current Position.
	 *
	 * @return A list of Position objects, where each Position represents a unique coordinate within
	 *         the region from (1,1) to the current Position.
	 */
	public List<Position> getAllPositionsUpTo() {
		List<Position> positions = new ArrayList<Position>();
		for (int row = 1; row <= getRow(); row++) {
			for (int col = 1; col <= getColumn(); col++) {
				positions.add(new Position(row, col));
			}
		}
		return positions;
	}

	/**
	 * Checks if the current Position is located inside the specified spreadsheet.
	 *
	 * @param spreadsheet The spreadsheet to check.
	 * @return {@code true} if the cell is inside the spreadsheet, {@code false} otherwise.
	 */
	public boolean isInsideSpreadsheet(Spreadsheet spreadsheet) {
		return getRow() <= spreadsheet.getLastPosition().getRow() 
		&& getColumn() <= spreadsheet.getLastPosition().getColumn();
	}

	/**
	 * Returns a string representation of this {@link Position} in the format "row;column".
	 *
	 * @return A string representation of the position.
	 */
	@Override
	public String toString() {
		return _row + ";" + _column;
	}

	/**
	 * Compares this Position to another Position for ordering. The comparison is first based on the row
	 * coordinate, and if the rows are the same, it is further based on the column coordinate.
	 *
	 * @param other The Position to compare to.
	 * @return a negative integer, zero, or a positive integer if this Position is less than, equal to,
	 *         or greater than the specified Position, based on their row and column coordinates.
	 */
	@Override
	public int compareTo(Position other) {
		// Compare first by row
		int rowComparison = Integer.compare(this._row, other._row);
		// If rows are the same, compare by column
		if (rowComparison == 0) {
			return Integer.compare(this._column, other._column);
		}
		return rowComparison;
	}

	/**
	 * Gets the row value of this {@link Position}.
	 *
	 * @return The row value.
	 */
	int getRow() {
		return _row;
	}

	/**
	 * Gets the column value of this {@link Position}.
	 *
	 * @return The column value.
	 */
	int getColumn() {
		return _column;
	}

	/**
	 * Checks if this position is on the same row as the specified position.
	 *
	 * @param toCompare The position to compare with.
	 * @return {@code true} if this position and the specified position are on the same row, {@code false} otherwise.
	 */
	boolean onSameRow(Position toCompare) {
		return _row == toCompare._row;
	}

	/**
	 * Checks if the current {@link Position} is valid.
	 * A valid position must have both row and column indices greater than zero.
	 *
	 * @return {@code true} if the position is valid, {@code false} otherwise.
	 */
	private boolean isValid() {
		return _row > 0 && _column > 0;
	}

}
