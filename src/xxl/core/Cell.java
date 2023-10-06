package xxl.core;

public class Cell {
    private Content _content = null;
    private Position _position;

    /**
     * Creates a {@link Cell} knowing its row and column.
     * 
     * @param row the row of the new Cell
     * @param column the column of the new Cell
     */
    public Cell(int row, int column) {
        _position = new Position(row, column);
    }

    /**
     * Constructs a new {@link Cell} with the specified Position.
     *
     * @param position the {@link Position} object representing the row 
     * and column coordinates.
     */
    public Cell(Position position) {
        _position = position;
    }

    /**
     * Retrieves the position (row and column coordinates) of this {@link Cell}.
     *
     * @return A new {@link Position} object representing the row and column coordinates
     * of this cell. For safety reasons, it returns a copy of the position and not the
     * actual position object.
     */
    Position getPosition() {
        return new Position(_position.getRow(),_position.getColumn());
    }
    
    /**
     * Handles {@link Cell}'s toString (common) method.
     *
     * @return a String representation of the current {@link Cell} 
     * (and its contents)
     */
    public String toString() {
        // More maybe? FIXME later
        return _position.toString();
    }

    /**
     * Sets the content for the desired cell.
     * 
     * @param content the type of content of the cell
     */
    void setContent(Content content) {
        _content = content;
    }

    // FIXME javadoc
    Literal getValue() {
        return _content.getValue();
    }
}