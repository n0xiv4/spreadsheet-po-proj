package xxl.core;

// FIXME import classes

import java.util.List;
import java.util.ArrayList;

import java.io.Serial;
import java.io.Serializable;

import xxl.core.exception.UnrecognizedEntryException;

/**
 * The {@link Spreadsheet} class represents a grid-based spreadsheet with cells.
 * It allows users to insert content into specific cells and maintains a collection
 * of users associated with the spreadsheet.
 *
 * @serial 202308312359L
 */
public class Spreadsheet implements Serializable {
	private List<User> _users;
	private List<Cell> _cells;
	private Interval _spreadsheetRange;
	
	@Serial
	private static final long serialVersionUID = 202308312359L;
	
	// FIXME define attributes
	// FIXME define contructor(s)
	// FIXME define methods
	
	/**
	 * Constructs a new {@link Spreadsheet} with the specified number of rows and columns.
	 *
	 * @param rows    The number of rows in the spreadsheet.
	 * @param columns The number of columns in the spreadsheet.
	 */
	public Spreadsheet(int rows, int columns) {
		_users = new ArrayList<User>();
		_cells = new ArrayList<Cell>();
		_spreadsheetRange = new Interval(new Position(rows, columns), this);

		populateSpreadsheet();
		// FIXME aditional things to implement
	}

	/**
	 * Populates the {@link Spreadsheet} with cells based on the specified number of rows and columns.
	 * Each cell is associated with a unique {@link Position}.
	 */
	private void populateSpreadsheet() {
		int lastRow = _spreadsheetRange.getLastPosition().getRow();
		int lastColumn = _spreadsheetRange.getLastPosition().getColumn();
		for(int row = 1; row <= lastRow; row++) {
			for(int column = 1; column <= lastColumn; column++) {
				_cells.add(new Cell(row, column));
			}
		}
	}

	/**
	 * Insert specified content in specified {@link Position}.
	 *
	 * @param row the row of the cell to change 
	 * @param column the column of the cell to change
	 * @param contentSpecification the specification in a string format of the content to put
	 *        in the specified cell.
	 */
	public void insertContent(int row, int column, Content contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
		findCellByPosition(new Position(row, column)).setContent(contentSpecification);
		// FIXME... exceptions ?
	}

	/**
	 * Finds and returns a {@link Cell} within a collection of cells based on its position.
	 *
	 * @param cellPos The {@link Position} object representing the row and column coordinates
	 *                to search for within the collection of cells.
	 * @return The {@link Cell} with the specified position if found; {@code null} if no cell
	 *         with the given position exists in the collection.
	 */
	private Cell findCellByPosition(Position cellPos) {
		for (Cell cell: _cells) {
				if (cell.getPosition().equals(cellPos)) {
						return cell;
				}
		}
		return null;
	}

	/**
	 * Retrieves the value of a cell at the specified {@link Position} within the spreadsheet.
	 *
	 * @param cellPosition The {@link Position} object representing the row and column coordinates
	 *                     of the cell to retrieve the value from.
	 * @return The value of the cell at the specified position, represented as a {@link Literal}.
	 * @throws CellNotFoundException If no cell exists at the provided position in the spreadsheet.
	 */
	Literal getValueInPosition(Position cellPosition) {
		// FIXME maybe add exception ?
		return findCellByPosition(cellPosition).getValue();
	}

	/**
	 * Links a {@link User} to the spreadsheet.
	 *
	 * @param user The {@link User} to link with the spreadsheet.
	 */
	void linkUser(User user) {
		// FIXME caution with bidirectional link!! where does it start?
		_users.add(user);
	}
}
