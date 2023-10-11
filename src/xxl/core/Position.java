package xxl.core;

import java.io.Serializable;

/**
 * The {@code Position} class represents a two-dimensional position or coordinate
 * consisting of a row and a column, that belongs to the {@link Cell}.
 */
public class Position implements Serializable, Comparable<Position> {
	
	private int _row;
	private int _column;

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
	 * @param firstPosition  The first {@link Position} to be compared.
	 * @param secondPosition The second {@link Position} to be compared.
	 * @return {@code true} if the positions are compatible for defining an interval, {@code false} otherwise.
	 */
	public static boolean isCompatibleForInterval(Position firstPosition, Position secondPosition) {
		// Check if either position is not valid
		if (!firstPosition.isValid() || !secondPosition.isValid()) {
			return false;
		}

		// Check if rows are equal and columns are in ascending order
		if (firstPosition.getRow() == secondPosition.getRow() && firstPosition.getColumn() <= secondPosition.getColumn()) {
			return true;
		}

		// Check if columns are equal and rows are in ascending order
		if (firstPosition.getColumn() == secondPosition.getColumn() && firstPosition.getRow() <= secondPosition.getRow()) {
			return true;
		}

		return false;
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
     * Returns a string representation of this {@link Position} in the format "row;column".
     *
     * @return A string representation of the position.
     */
	public String toString() {
		return _row + ";" + _column;
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

	// FIXME
	public int compareTo(Position position) {
		if (getRow() < position.getRow()) {
			// A is before B in terms of rows
			return -1;
		} 
		else if (getRow() > position.getRow()) {
			// A is after B in terms of rows
			return 1;
		} 
		else {
			// Rows are the same; compare columns
			if (getColumn() < position.getColumn()) {
				// A is before B in terms of columns
				return -1;
			} 
			else if (getColumn() > position.getColumn()) {
				// A is after B in terms of columns
				return 1;
			} 
			else {
				// Rows and columns are the same
				return 0;
			}
		}
	}
}
