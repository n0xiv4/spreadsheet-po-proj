package xxl.core;

public class Cell {
    private Content _content = null;
    private int _row;
    private int _column;

    /**
     * Creates a cell knowing its row and column.
     * 
     * @param row the row of the new Cell
     * @param column the column of the new Cell
     */
    public Cell(int row, int column) {
        _row = row;
        _column = column;
    }
    
    /**
     * Handles Cell's toString (common) method.
     *
     * @returns a String representation of the current Cell 
     * (and its contents)
     */
    public String toString() {
        // FIXME
    }

    /**
     * Sets the content for the desired cell.
     * 
     * @param content the type of content of the cell
     */
    void setContent(Content content) {
        _content = content;
    }
}