package xxl.core;

// FIXME import classes

import java.util.List;
import java.util.ArrayList;

import java.io.Serial;
import java.io.Serializable;

import xxl.app.exception.InvalidCellRangeException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * The {@code Spreadsheet} class represents a grid-based spreadsheet with cells.
 * It allows users to insert content into specific cells and maintains a collection
 * of users associated with the spreadsheet.
 * 
 * @Serial 202308312359L
 */
public class Spreadsheet implements Serializable {

	/** The list where all the users of the Spreadsheet are stored. */
	private List<User> _users;

	/** The list that holds all the Cells that are a part of the Spreadsheet. */
	private List<Cell> _cells;

	/** The range of the Spreadsheet, represented as interval. */
	private Interval _spreadsheetRange;
	
	/** The serial version UID for ensuring version compatibility during serialization. */
	@Serial
	private static final long serialVersionUID = 202308312359L;
	
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
	public void insertContent(int row, int column, Content contentSpecification) throws UnrecognizedEntryException {
		findCellByPosition(new Position(row, column)).setContent(contentSpecification);
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

	// FIXME temporary !!!!!!!!!!!!
	String visualizeCellInPosition(Position cellPosition) {
		return findCellByPosition(cellPosition).toString();
	}

	// FIXME javadoc
	public String visualizeGamma(String gamma) throws InvalidCellRangeException {
		Interval intervalToVisualize = new Interval(gamma, this);
		return intervalToVisualize.readInterval();
	}

	/**
	 * Links a {@link User} to the spreadsheet.
	 *
	 * @param user The {@link User} to link with the spreadsheet.
	 */
	public void linkUser(User user) {
		user.linkSpreadsheet(this);
		_users.add(user);
	}

	public Position getEndPosition() {
		return _spreadsheetRange.getLastPosition();
	}

}
