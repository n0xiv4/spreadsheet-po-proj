package xxl.core;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import java.io.Serial;
import java.io.Serializable;

import xxl.core.content.Content;
import xxl.core.content.literal.LiteralNullValue;
import xxl.core.exception.InvalidCellIntervalException;
import xxl.core.exception.InvalidFunctionException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.search.SearchStrategy;
import xxl.core.storage.TreeMapStorage;
import xxl.core.storage.Storage;

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

	/** Stores the cells and positions of the spreadsheet. */
	private Storage<Cell> _storage;

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
		_storage = new TreeMapStorage<Cell>();
		_spreadsheetRange = new Interval(new Position(rows, columns), this);
		_cutBuffer = new CutBuffer();

		populateSpreadsheet();
	}

	/**
	 * Insert specified content in specified {@link Position}.
	 *
	 * @param row the row of the cell to change 
	 * @param column the column of the cell to change
	 */
	public void insertContent(Position position, Content contentSpecification) {
		getCell(position).setContent(contentSpecification);
	}

	/**
	 * Cuts the specified gamma range from the spreadsheet. This operation involves copying the content within
	 * the given gamma range and then deleting the content from the spreadsheet.
	 *
	 * @param gamma The gamma range coordinates in string format (e.g., "1;1:2;2" for an interval).
	 * @throws InvalidCellIntervalException if the provided gamma range is invalid or cannot be cut.
	 */
	public void cutGamma(String gamma) throws InvalidCellIntervalException {
		copyGamma(gamma);
		deleteGamma(gamma);
	}

	/**
	 * Copies the content within the specified gamma range and stores it in the cut buffer for later use. The copied
	 * content is determined by creating an interval from the provided gamma range and extracting the cells within it.
	 *
	 * @param gamma The gamma range coordinates in string format (e.g., "1;1:2;1" for an interval).
	 * @throws InvalidCellIntervalException if the provided gamma range is invalid or cannot be copied.
	 */
	public void copyGamma(String gamma) throws InvalidCellIntervalException {
		Interval intervalToCopy = new Interval(gamma, this);
		_cutBuffer.setCutBuffer(new Interval(intervalToCopy).getCells());
	}

	/**
	 * Pastes the content from the cut buffer into the specified gamma range. The operation depends on the nature of
	 * the gamma range and the contents in the cut buffer. If the gamma range is a single cell and the cut buffer
	 * contains multiple cells, the content is inserted at the specified position. Otherwise, the content is pasted
	 * within the gamma range, adjusting to the shape of the range.
	 *
	 * @param gamma The gamma range coordinates in string format (e.g., "1;1:2;2" for an interval).
	 * @throws InvalidCellIntervalException if the provided gamma range is invalid or cannot be pasted.
	 */
	public void pasteGamma(String gamma) throws InvalidCellIntervalException {
		Interval intervalToPaste = new Interval(gamma, this);
		// When the gamma where to paste has only a single Cell
		if (_cutBuffer.getCells().size() > 1 && intervalToPaste.isSingle()) {
			pasteContentInPosition(intervalToPaste.getFirstPosition(), _cutBuffer.getCells());
		}
		// When the gamma has more than one Cell
		else {
			intervalToPaste.pasteContent(_cutBuffer.getCells());
		}
	}

	/**
	 * Deletes the content within the specified gamma range by setting it to a null value. The deletion operation
	 * is performed by creating an interval from the provided gamma range and then iterating through the positions
	 * within the interval to set their content to a null value.
	 *
	 * @param gamma The gamma range coordinates in string format (e.g., "1;1:2;2" for an interval).
	 * @throws InvalidCellIntervalException if the provided gamma range is invalid or cannot be deleted.
	 */
	public void deleteGamma(String gamma) throws InvalidCellIntervalException {
		Interval intervalToDelete = new Interval(gamma, this);
		for (Position position: intervalToDelete.getPositions()) {
			getCell(position).setContent(new LiteralNullValue());
		}
	}

	/**
	 * Inserts the specified content into the cells within the specified gamma range. The insertion operation
	 * involves converting the `contentSpecification` into a `Content` instance, creating an interval from the
	 * provided gamma range, and pasting the content into the interval.
	 *
	 * @param gamma              The gamma range coordinates in string format (e.g., "1;1:2;2" for an interval).
	 * @param contentSpecification The string specifying the content to be inserted into the cells.
	 * @throws InvalidCellIntervalException if the provided gamma range is invalid or cannot be inserted.
	 * @throws InvalidFunctionException   if the `contentSpecification` contains an invalid function.
	 * @throws UnrecognizedEntryException  if the `contentSpecification` contains unrecognized input.
	 */
	public void insertGamma(String gamma, String contentSpecification) throws InvalidCellIntervalException, InvalidFunctionException, UnrecognizedEntryException {
		// convert contentSpecification to a Content instance
		Parser lineParser = new Parser(this);
		Content contentToInsert = lineParser.parseContentInput(contentSpecification);

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
	 * Performs a search operation using the specified search strategy.
	 *
	 * @param searchType The search strategy to use for searching.
	 * @param toSearch The string to search for.
	 * @return A list of cells that match the search criteria.
	 */
	public String search(SearchStrategy searchType, String toSearch) {
		List<Cell> foundCells = searchType.search(this, toSearch);
		return displayCells(foundCells);
	}

	/**
	 * Visualizes the content of the cut buffer, if it exists. The method retrieves the cells from the cut buffer,
	 * and if the cut buffer exists, it returns a string representation of the cells using the `displayCells` method.
	 *
	 * @return A string representing the content of the cut buffer or an empty string if the cut buffer does not exist.
	 */
	public String visualizeCutBuffer() {
		return displayCells(_cutBuffer.getCells());
	}

	/**
	 * Retrieves the cell at the specified position.
	 *
	 * @param position The position of the cell to retrieve.
	 * @return The cell at the given position.
	 */
	public Cell getCell(Position position) {
		return _storage.get(position);
	}
	
	/**
	 * Retrieves an iterator for the cells stored in this data structure.
	 *
	 * @return An iterator over the cells stored in this data structure.
	 */
	public Iterator<Cell> getCellIterator() {
		return _storage.iterator();
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
	 * Retrieves the cells stored in the cut buffer. This method returns a list of cells that have been previously
	 * copied or cut and stored in the cut buffer.
	 *
	 * @return A list of cells representing the content in the cut buffer.
	 */
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

	/**
	 * Flags the current spreadsheet as changed. This method sets a flag to indicate that the spreadsheet has been modified.
	 * Call this method to mark the spreadsheet as changed after making modifications to its content.
	 */
	void flagAsChanged() {
		_changed = true;
	}

	/**
	 * Flags the current spreadsheet as unchanged. This method sets a flag to indicate that the spreadsheet has not been
	 * modified. Call this method to mark the spreadsheet as unchanged when no modifications have occurred.
	 */
	void flagAsUnchanged() {
		_changed = false;
	}
	
	/**
	 * Visualizes the {@link Content} of the {@link Cell} at the specified {@link Position}.
	 *
	 * @param cellPosition The position of the cell to visualize.
	 * @return A string representation of the content in the cell at the given {@link Position}.
	 */
	String visualizeCellInPosition(Position cellPosition) {
		return getCell(cellPosition).toString();
	}

	/**
	 * Populates the {@code Spreadsheet} with cells based on the specified number of rows and columns.
	 * Each cell is associated with a unique {@link Position}.
	 */
	private void populateSpreadsheet() {
		for (Position position: getLastPosition().getAllPositionsUpTo()) {
			_storage.set(position, new Cell(position));
		}
	}

	/**
	 * Converts a list of cells into a string and removes the last newline character if the list is not empty.
	 *
	 * @param cells The list of cells to display.
	 * @return A string representing of the cells.
	 */
	private String displayCells(List<Cell> cells) {
		StringBuilder display = new StringBuilder();
		for (Cell cell: cells) {
			display.append(cell).append("\n");
		}
		if (display.length() > 0) {
			display.deleteCharAt(display.length() - 1);
		}
		return display.toString();
	}

	/**
	 * Pastes the content of the given list of cells into the specified initial position.
	 * This happens when the user input Interval has a single position only.
	 *
	 * @param initialPosition The initial position where the content will be inserted.
	 * @param cells The list of cells to insert.
	 */
	private void pasteContentInPosition(Position initialPosition, List<Cell> cells) {
		Position firstCellPosition = cells.get(0).getPosition();
		Position lastCellPosition = cells.get(cells.size() - 1).getPosition();
		
		int rowDifference = lastCellPosition.getRow() - firstCellPosition.getRow();
		int colDifference = lastCellPosition.getColumn() - firstCellPosition.getColumn();
		
		Position finalPosition;
		
		// If the cutBuffer is on the same row
		if (firstCellPosition.getRow() == lastCellPosition.getRow()) {
			finalPosition = new Position(
				initialPosition.getRow() + rowDifference,
				initialPosition.getColumn() + colDifference
			);
		}
		// If the cutBuffer is on the same column
		else {
			finalPosition = new Position(
				initialPosition.getRow() + rowDifference,
				initialPosition.getColumn() + rowDifference
			);
		}
		
		Interval toPaste = new Interval(initialPosition, finalPosition, this);
		toPaste.pasteContent(cells);
	}
}
