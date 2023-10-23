package xxl.core;

import java.io.Serial;
import java.io.Serializable;

/**
 * The {@code Cell} class represents a cell in a spreadsheet.
 * It contains information about its position (row and column coordinates) and the content it holds.
 * Cells can store various types of content, such as literals, functions, or references.
 * 
 * @Serial 202310072353L
 */
public class Cell implements Serializable {

	/** The content stored in the cell. */
	private Content _content;

	/** The position (row and column coordinates) of the cell. */
	private Position _position;

	/** The function manager (used for notificating changes to the content). */
	private FunctionManager _functionManager;

	/** The serial version UID for object serialization. */
	@Serial
	private static final long serialVersionUID = 202310072353L;
	
	/**
	 * Creates a new {@link Cell} object with the specified row and column coordinates.
	 * The cell is initially empty and contains a {@link LiteralNull} as its content.
	 *
	 * @param row    The row of the new cell.
	 * @param column The column of the new cell.
	 */
	Cell(int row, int column) {
		_position = new Position(row, column);
		_content = new LiteralNullValue();
		_functionManager = new FunctionManager();
	}

	/**
	 * Constructs a new {@link Cell} object with the specified position.
	 * The cell is initially empty and contains a {@link LiteralNull} as its content.
	 *
	 * @param position The {@link Position} object representing the row and column coordinates.
	 */
	Cell(Position position) {
		_position = position;
		_content = new LiteralNullValue();
		_functionManager = new FunctionManager();
	}

	/**
	 * Returns a string representation of the cell, including its position and content.
	 *
	 * @return A string in the format "row;column|content".
	 */
	public String toString() {
		return _position.toString() + "|" + _content.toString();
	}

	/**
	 * Retrieves the position (row and column coordinates) of this {@link Cell}.
	 *
	 * @return A new {@link Position} object representing the row and column coordinates of this cell.
	 */
	Position getPosition() {
		return _position;
	}

	/**
	 * Retrieves the content stored in this cell.
	 *
	 * @return The {@link Content} stored in this cell.
	 */
	Content getContent() {
		return _content;
	}

	/**
	 * Sets the content for the cell.
	 *
	 * @param content The type of content to be set in the cell.
	 */
	void setContent(Content content) {
		_content = content;
		_functionManager.notifyFunctions();
	}

	/**
	 * Retrieves the content type of this cell, which indicates the type of data or formula stored in the cell.
	 *
	 * @return A string representing the content type of the cell.
	 */
	String getContentType() {
		return _content.getType();
	}

	/**
	 * Retrieves the function manager associated with this cell, which manages any interval functions applied to the cell.
	 *
	 * @return The {@link FunctionManager} responsible for handling interval functions in this cell.
	 */
	FunctionManager getFunctionManager() {
		return _functionManager;
	}

	/**
	 * Retrieves the value of the cell's content.
	 *
	 * @return The value of the cell's content as a {@link Literal}.
	 */
	Literal getValue() {
		return _content.getValue();
	}
}
