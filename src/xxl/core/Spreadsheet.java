package xxl.core;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.io.Serial;
import java.io.Serializable;

import xxl.core.exception.InvalidCellIntervalException;
import xxl.core.exception.InvalidFunctionException;
import xxl.core.exception.InvalidValueTypeException;
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

	/** A cutbuffer atribute that holds the current clipboard of cells. */
	private CutBuffer _cutBuffer;
	
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
		_cutBuffer = new CutBuffer();

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
		getCellInPosition(new Position(row, column)).setContent(contentSpecification);
	}

	// FIXME
	public void cutGamma(String gamma) throws InvalidCellIntervalException {
		copyGamma(gamma);
		deleteGamma(gamma);
	}

	// FIXME
	public void copyGamma(String gamma) throws InvalidCellIntervalException {
		Interval intervalToCopy = new Interval(gamma, this);
		_cutBuffer.setCutBuffer(getCellsFromInterval(new Interval(intervalToCopy)));
	}

	// FIXME
	public void pasteGamma(String gamma) throws InvalidCellIntervalException {
		Interval intervalToPaste = new Interval(gamma, this);
		List<Content> contentToPaste = convertCellListToContentList(_cutBuffer.getCells());
		intervalToPaste.pasteContent(contentToPaste);
	}

	// FIXME
	public void deleteGamma(String gamma) throws InvalidCellIntervalException {
		Interval intervalToDelete = new Interval(gamma, this);
		for (Position position: intervalToDelete.getPositions()) {
			getCellInPosition(position).setContent(new LiteralNullValue());
		}
	}

	// FIXME
	public void insertGamma(String gamma, String contentSpecification) throws InvalidCellIntervalException, InvalidFunctionException, UnrecognizedEntryException {
		// convert contentSpecification to a Content instance
		Parser lineParser = new Parser(this);
		Content contentToInsert = lineParser.parseUserInput(contentSpecification);

		// get Interval where to insert and do it
		Interval intervalToInsert = new Interval(gamma, this);
		intervalToInsert.pasteContent(contentToInsert);
	}

	/**
	 * Visualizes the content of a spreadsheet range specified by a given gamma string.
	 *
	 * @param gamma The gamma string representing the spreadsheet range to visualize.
	 * @return A string representation of the contents within the specified spreadsheet range.
	 * @throws InvalidCellIntervalException if the gamma string format is invalid.
	 */
	public String visualizeGamma(String gamma) throws InvalidCellIntervalException {
		Interval intervalToVisualize = new Interval(gamma, this);
		return intervalToVisualize.readInterval();
	}

	/**
	 * Visualizes cells that match the given value and returns them as a string.
	 *
	 * @param value The value to search for. If it starts with a single quote, it's treated as a String; otherwise, it's treated as an Integer.
	 * @return A string containing the visual representation of matching cells, with the last newline character removed.
	 */
	public String visualizeValue(String value) {
		/** A mutable version of a "String", so we can remove the last newline char. */
		StringBuilder display = new StringBuilder();

		if (value.charAt(0) == '\'') {
			String strValue = value.substring(1);
			display.append(findCellsByStringValue(strValue));
		} 
		else {
			int intValue = Integer.parseInt(value);
			display.append(findCellsByIntValue(intValue));
		}
		if (display.length() > 0) {
			display.deleteCharAt(display.length() - 1); 
		}

		return display.toString();
	}

	/**
	 * Visualizes cells containing a specified function by filtering and sorting them.
	 *
	 * @param function The function to search for.
	 * @return A string representing the sorted cells.
	 */
	public String visualizeFunction(String function) {
		List<Cell> foundCells = filterCellsByFunction(function);
		sortCellsByContentType(foundCells);
		return displayCells(foundCells);
	}

	// FIXME
	public String visualizeCutBuffer() {
		try {
			return displayCells(_cutBuffer.getCells());
		}
		catch (NullPointerException e) {
			// No cutBuffer exists.
			return "";
		}
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

	// FIXME
	public List<Cell> getCutBuffer() {
		return _cutBuffer.getCells();
	}
	
	/**
	 * Checks if the spreadsheet range has been changed.
	 *
	 * @return {@code true} if the spreadsheet range has been changed; {@code false} otherwise.
	 */
	boolean isChanged() {
		return _changed;
	}

	// FIXME
	void flagAsChanged() {
		_changed = true;
	}

	// FIXME
	void flagAsUnchanged() {
		_changed = false;
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
		return getCellInPosition(cellPosition).getValue();
	}

	// FIXME
	Content getContentInPosition(Position cellPosition) {
		return getCellInPosition(cellPosition).getContent();
	}
	
	/**
	 * Visualizes the {@link Content} of the {@link Cell} at the specified {@link Position}.
	 *
	 * @param cellPosition The position of the cell to visualize.
	 * @return A string representation of the content in the cell at the given {@link Position}.
	 */
	String visualizeCellInPosition(Position cellPosition) {
		return getCellInPosition(cellPosition).toString();
	}

	/**
	 * Finds and returns a {@link Cell} within a collection of cells based on its position.
	 * It's a private method so no Cells are returned (could result in a privacy leak, because
	 * Cells aren't 100% immutable).
	 *
	 * @param cellPos The {@link Position} object representing the row and column coordinates
	 *                to search for within the collection of cells.
	 * @return The {@link Cell} with the specified position if found; {@code null} if no cell
	 *         with the given position exists in the collection.
	 */
	private Cell getCellInPosition(Position cellPos) {
		for (Cell cell: _cells) {
			if (cell.getPosition().equals(cellPos)) {
				return cell;
			}
		}
		return null;
	}

	/**
	 * Populates the {@code Spreadsheet} with cells based on the specified number of rows and columns.
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
	 * Finds cells with String values that match the provided value and returns them as a string.
	 *
	 * @param strValue The String value to search for in the cells.
	 * @return A string containing the visual representation of cells with matching String values.
	 */
	private String findCellsByStringValue(String strValue) {
		StringBuilder display = new StringBuilder();
		for (Cell cell : _cells) {
			try {
				if (cell.getValue().getStringValue().equals(strValue)) {
					display.append(cell.toString()).append("\n");
				}
			} 
			catch (InvalidValueTypeException e) {
			}
		}
		return display.toString();
	}
	
	
	/**
	 * Finds cells with Integer values that match the provided value and returns them as a string.
	 *
	 * @param intValue The Integer value to search for in the cells.
	 * @return A string containing the visual representation of cells with matching Integer values.
	 */
	private String findCellsByIntValue(int intValue) {
		StringBuilder display = new StringBuilder();
		for (Cell cell : _cells) {
			try {
				if (cell.getValue().getIntValue() == intValue) {
					display.append(cell.toString()).append("\n");
				}
			} 
			catch (InvalidValueTypeException e) {
			}
		}
		return display.toString();
	}

	/**
	 * Filters cells that contain the specified function.
	 *
	 * @param function The function to search for.
	 * @return A list of filtered cells.
	 */
	private List<Cell> filterCellsByFunction(String function) {
		List<Cell> foundCells = new ArrayList<>();
		for (Cell cell : _cells) {
			if (cell.getContentType().contains(function)) {
				foundCells.add(cell);
			}
		}
		return foundCells;
	}

	/**
	 * Sorts a list of cells by content type using a custom comparator.
	 *
	 * @param cells The list of cells to sort.
	 */
	private void sortCellsByContentType(List<Cell> cells) {
		Collections.sort(cells, new ContentSort());
	}

	/**
	 * Converts a list of cells into a string and removes the last newline character if the list is not empty.
	 *
	 * @param cells The list of cells to display.
	 * @return A string representing of the cells.
	 */
	private String displayCells(List<Cell> cells) {
		StringBuilder display = new StringBuilder();
		for (Cell sCell : cells) {
			display.append(sCell).append("\n");
		}
		if (display.length() > 0) {
			display.deleteCharAt(display.length() - 1);
		}
		return display.toString();
	}

	// FIXME
	private List<Cell> getCellsFromInterval(Interval toRead) {
		List<Cell> cells = new ArrayList<Cell>();
		Spreadsheet intervalSpreadsheet = toRead.getLinkedSpreadsheet();
		for (Position cellPosition: toRead.getPositions()) {
			cells.add(intervalSpreadsheet.getCellInPosition(cellPosition));
		}
		return cells;
	}

	// FIXME
	private List<Content> convertCellListToContentList(List<Cell> cells) {
		// for each Cell in the list, we get its content
		return cells.stream().map(cell -> cell.getContent()).collect(Collectors.toList());
	}
	
}
