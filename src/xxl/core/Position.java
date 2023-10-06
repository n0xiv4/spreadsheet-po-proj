package xxl.core;

/**
 * The {@link Position} class represents a two-dimensional position or coordinate
 * consisting of a row and a column, that belongs to the {@link Cell}.
 */
public class Position {
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
}
