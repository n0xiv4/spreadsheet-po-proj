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
    public Cell(int row, int column) {
        _position = new Position(row, column);
        _content = new LiteralNull();
    }

    /**
     * Constructs a new {@link Cell} object with the specified position.
     * The cell is initially empty and contains a {@link LiteralNull} as its content.
     *
     * @param position The {@link Position} object representing the row and column coordinates.
     */
    public Cell(Position position) {
        _position = position;
        _content = new LiteralNull();
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
     * Returns a string representation of the cell, including its position and content.
     *
     * @return A string in the format "row;column|content".
     */
    public String toString() {
        return _position.toString() + "|" + _content.toString();
    }

    /**
     * Sets the content for the cell.
     *
     * @param content The type of content to be set in the cell.
     */
    void setContent(Content content) {
        _content = content;
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
