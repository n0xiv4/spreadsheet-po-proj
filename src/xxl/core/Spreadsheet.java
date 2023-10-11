package xxl.core;

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

	/** A boolean atribute that specifies if the spreadsheet has been changed already. */
	private boolean _changed;
	
	/** The serial version UID for ensuring version compatibility during serialization. */
	@Serial
	private static final long serialVersionUID = 202308312359L;
	
	/**
	 * Constructs a new {@link Spreadsheet} with the specified number of rows and columns.
	 *
	 * @param rows    The number of rows in the spreadsheet.
	 * @param columns The number of columns in the spreadsheet.
	 */
	Spreadsheet(int rows, int columns) {
		_users = new ArrayList<User>();
		_cells = new ArrayList<Cell>();
		_spreadsheetRange = new Interval(new Position(rows, columns), this);

		populateSpreadsheet();
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
	 * Visualizes the content of a spreadsheet range specified by a given gamma string.
	 *
	 * @param gamma The gamma string representing the spreadsheet range to visualize.
	 * @return A string representation of the contents within the specified spreadsheet range.
	 * @throws InvalidCellRangeException if the gamma string format is invalid.
	 */
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

	/**
	 * Returns the last position within the spreadsheet range.
	 *
	 * @return The {@link Position} of the last cell in the spreadsheet range.
	 */
	public Position getLastPosition() {
		return _spreadsheetRange.getLastPosition();
	}
	
	/**
	 * Checks if the spreadsheet range has been changed.
	 *
	 * @return {@code true} if the spreadsheet range has been changed; {@code false} otherwise.
	 */
	public boolean isChanged() {
		return _changed;
	}

	/**
	 * Retrieves the value of a {@link Cell} at the specified {@link Position} within the spreadsheet.
	 *
	 * @param cellPosition The {@link Position} object representing the row and column coordinates
	 *                     of the cell to retrieve the value from.
	 * @return The value of the cell at the specified position, represented as a {@link Literal}.
	 * @throws CellNotFoundException If no cell exists at the provided position in the spreadsheet.
	 */
	Literal getValueInPosition(Position cellPosition) {
		return findCellByPosition(cellPosition).getValue();
	}
	
	/**
	 * Visualizes the {@link Content} of the {@link Cell} at the specified {@link Position}.
	 *
	 * @param cellPosition The position of the cell to visualize.
	 * @return A string representation of the content in the cell at the given {@link Position}.
	 */
	String visualizeCellInPosition(Position cellPosition) {
		return findCellByPosition(cellPosition).toString();
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
	
}
