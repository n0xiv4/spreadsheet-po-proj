package xxl.core;

import java.io.Serial;
import java.io.Serializable;

/**
 * The {@code Position} class represents a two-dimensional position or coordinate
 * consisting of a row and a column, that belongs to the {@link Cell}.
 * 
 * @Serial 202310112120L
 */
public class Position implements Serializable, Comparable<Position> {
	
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
	 * Compares this {@link Position} with another {@link Position} to determine if they are equal.
	 *
	 * @param position The {@link Position} to compare with this one.
	 * @return {@code true} if the provided {@link Position} has the same row and column
	 *         coordinates as this one; {@code false} otherwise.
	 */
	public boolean equals(Position position) {
		return _row == position.getRow() && _column == position.getColumn();
	}

	/**
	 * Compares this {@link Position} with another {@link Position} for ordering. Returns a negative integer if this
	 * position is before the specified position (in terms of rows), a positive integer if it's after the specified
	 * position, and 0 if the positions are the same (both in rows and columns).
	 *
	 * @param position The {@link Position} to compare with.
	 * @return A negative integer if this position is before the specified position, a positive integer if it's after,
	 *         or 0 if the positions are the same.
	 */
	public int compareTo(Position position) {
		if (_row < position._row) {
			// A is before B in terms of rows
			return -1;
		} 
		else if (_row > position._row) {
			// A is after B in terms of rows
			return 1;
		} 
		else {
			// Rows are the same; compare columns
			if (_column < position._column) {
				// A is before B in terms of columns
				return -1;
			} 
			else if (_column > position._column) {
				// A is after B in terms of columns
				return 1;
			} 
			else {
				// Rows and columns are the same
				return 0;
			}
		}
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
	 * Checks if the current {@link Position} is valid.
	 * A valid position must have both row and column indices greater than zero.
	 *
	 * @return {@code true} if the position is valid, {@code false} otherwise.
	 */
	private boolean isValid() {
		return _row > 0 && _column > 0;
	}

	/**
	 * Returns a string representation of this {@link Position} in the format "row;column".
	 *
	 * @return A string representation of the position.
	 */
	public String toString() {
		return _row + ";" + _column;
	}
}
