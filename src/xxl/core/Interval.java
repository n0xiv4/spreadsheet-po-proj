package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import xxl.core.content.Content;
import xxl.core.content.Observer;
import xxl.core.exception.InvalidCellIntervalException;

/**
 * The {@code Interval} class represents a rectangular interval or range of positions within a
 * {@link Spreadsheet}. It defines the first and last positions of the interval and the associated
 * spreadsheet.
 * 
 * @Serial 202310102223L
 */
public class Interval implements Serializable {
	
	/** The first position of the interval. */
	private Position _firstPosition;

	/** The last position of the interval. */
	private Position _lastPosition;

	/** The associated spreadsheet. */
	private Spreadsheet _linkedSpreadsheet;

	/** The serial version UID for ensuring version compatibility during serialization. */
	@Serial
	private static final long serialVersionUID = 202310102223L;

	/**
	 * Constructs a new interval with the specified first and last positions and associates it
	 * with a given spreadsheet.
	 *
	 * @param firstPosition The {@link Position} of the first cell in the interval.
	 * @param lastPosition  The {@link Position} of the last cell in the interval.
	 * @param spreadsheet   The {@link Spreadsheet} to which this interval is associated.
	 */
	public Interval(Position firstPosition, Position lastPosition, Spreadsheet spreadsheet) {
		_firstPosition = firstPosition;
		_lastPosition = lastPosition;
		_linkedSpreadsheet = spreadsheet;
	}

	/**
	 * Constructs a new interval with the specified last position and associates it with a given
	 * spreadsheet. The first position is assumed to be the top-left cell (row 1, column 1) of
	 * the spreadsheet.
	 *
	 * @param lastPosition  The {@link Position} of the last cell in the interval.
	 * @param spreadsheet   The {@link Spreadsheet} to which this interval is associated.
	 */
	public Interval(Position lastPosition, Spreadsheet spreadsheet) {
		_firstPosition = new Position(1, 1);
		_lastPosition = lastPosition;
		_linkedSpreadsheet = spreadsheet;
	}

	/**
	 * Constructs a new interval with specified gamma range coordinates and associates it with a given
	 * spreadsheet. The gamma range can represent either a single cell or a cell interval.
	 * If it's a single cell, both the first and last positions of the interval will be the same.
	 *
	 * @param gamma       The gamma range coordinates in string format (e.g., "1;1" for a single cell or "1;1:2;2" for an interval).
	 * @param spreadsheet The {@link Spreadsheet} to which this interval is associated.
	 * @throws InvalidCellIntervalException if the provided gamma range does not make a valid Interval.
	 */
	public Interval(String gamma, Spreadsheet spreadsheet) throws InvalidCellIntervalException {
		Position[] intervalPositions = parsePositions(gamma);
		_firstPosition = intervalPositions[0];
		_lastPosition = intervalPositions[1];
		_linkedSpreadsheet = spreadsheet;
		if (!isInsideSpreadsheet() || !_firstPosition.isCompatibleForInterval(_lastPosition)) {
			throw new InvalidCellIntervalException();
		}
	}

	/**
	 * Creates an interval for the cut buffer by copying content from another interval.
	 * This constructor is used to duplicate an existing interval and its contents.
	 *
	 * @param toCopy The interval to copy content from.
	 */
	public Interval(Interval toCopy) {
		int newSheetRows = calculateNewSheetRows(toCopy);
		int newSheetColumns = calculateNewSheetColumns(toCopy);
	
		_linkedSpreadsheet = new Spreadsheet(newSheetRows, newSheetColumns);
		_firstPosition = new Position(1, 1);
		_lastPosition = new Position(newSheetRows, newSheetColumns);
	
		copyContent(toCopy);
	}

	/**
	 * Reads the content within the interval and returns it as a string.
	 *
	 * @return A string representation of the content within the interval.
	 */
	public String readInterval() {
		String interval = new String("");
		// If the Interval happens in a certain row, we'll iterate thru the columns of that row.
		if (onSameRow()) {
			for (int col = _firstPosition.getColumn(); col <= _lastPosition.getColumn(); col++) {
				if (col != _firstPosition.getColumn()) {
					interval += "\n";
				}
				interval += _linkedSpreadsheet.visualizeCellInPosition(new Position(_firstPosition.getRow(), col));
			}
		}
		// Else, the Interval will happen in a certain column, so we'll instead iterate thru the rows.
		else {
			for (int row = _firstPosition.getRow(); row <= _lastPosition.getRow(); row++) {
				if (row != _firstPosition.getRow()) {
					interval += "\n";
				}
				interval += _linkedSpreadsheet.visualizeCellInPosition(new Position(row, _firstPosition.getColumn()));
			}
		}
		return interval;
	}

	/**
	 * Checks if the interval is inside the spreadsheet's range.
	 *
	 * @return {@code true} if the last position is inside the spreadsheet's range; {@code false} otherwise.
	 */
	public boolean isInsideSpreadsheet() {
		return _lastPosition.isInsideSpreadsheet(_linkedSpreadsheet);
	}

	/**
	 * Adds an observer to all cells within the interval and updates references within the cells.
	 *
	 * @param observer The observer to be added to the cells.
	 */
	public void addObserverToCells(Observer observer) {
		ReferenceUpdateVisitor referenceUpdateVisitor = new ReferenceUpdateVisitor();
		for (Cell cell: getCells()) {
			cell.addObserver(observer);
			cell.getContent().accept(referenceUpdateVisitor, observer);
		}
	}

	/**
	 * Retrieves a list of cells based on the positions obtained from this object.
	 *
	 * @return A list of cells corresponding to the positions.
	 */
	public List<Cell> getCells() {
		List<Cell> cells = new ArrayList<Cell>();
		for (Position position: getPositions()) {
			cells.add(_linkedSpreadsheet.getCell(position));
		}
		return cells;
	}

	/**
	 * Retrieves a list of content elements from the cells obtained from this object.
	 *
	 * @return A list of content elements derived from the cells.
	 */
	public List<Content> getContent() {
		return getCells().stream().map(cell -> cell.getContent()).collect(Collectors.toList());
	}

	/**
	 * Returns a string representation of the range, including its first and last positions.
	 *
	 * @return A string representation in the format "firstPosition:lastPosition."
	 */
	@Override
	public String toString() {
		return _firstPosition.toString() + ":" + _lastPosition.toString();
	}

	/**
	 * Retrieves the first {@link Position} of this interval.
	 *
	 * @return The {@link Position} of the first cell in the interval.
	 */
	Position getFirstPosition() {
		return _firstPosition;
	}

	/**
	 * Retrieves the last {@link Position} of this interval.
	 *
	 * @return The {@link Position} of the last cell in the interval.
	 */
	Position getLastPosition() {
		return _lastPosition;
	}

	/**
	 * Returns a list of positions within the interval. The positions are generated based on the first
	 * and last positions of the interval. If the interval spans multiple rows or columns, it provides
	 * a list of all positions in that range.
	 *
	 * @return A list of {@link Position} objects representing the positions within the interval.
	 */
	List<Position> getPositions() {
		List<Position> positions = new ArrayList<Position>();
		if (onSameRow()) {
			int row = _firstPosition.getRow();
			for (int col = _firstPosition.getColumn(); col <= _lastPosition.getColumn(); col++) {
				positions.add(new Position(row, col));
			}
		}
		else {
			int col = _firstPosition.getColumn();
			for (int row = _firstPosition.getRow(); row <= _lastPosition.getRow(); row++) {
				positions.add(new Position(row, col));
			}
		}
		return positions;
	}

	/**
	 * Retrieves the associated spreadsheet linked to this interval.
	 *
	 * @return The {@link Spreadsheet} to which this interval is associated.
	 */
	Spreadsheet getLinkedSpreadsheet() {
		return _linkedSpreadsheet;
	}

	/**
	 * Pastes the specified content into all positions within the interval. This method inserts the provided
	 * content into each position within the interval, effectively populating the interval with the same content.
	 *
	 * @param content The {@link Content} to be pasted into all positions within the interval.
	 */
	void pasteContent(Content content) {
		for (Position position: getPositions()) {
			_linkedSpreadsheet.insertContent(position, content);
		}
	}

	/**
	 * Pastes the contents of a list of cells into the corresponding positions within the interval. This method inserts
	 * the content of each cell in the list into the positions within the interval, effectively populating the interval
	 * with the contents of the provided cells. If a position is out of bounds, the operation is ignored.
	 *
	 * @param cells The list of {@link Cell} objects containing the content to be pasted into the interval.
	 */
	void pasteContent(List<Cell> cells) {
		List<Position> positions = getPositions();
		int index = 0;		
		for (Cell cell: cells) {
			try {
				Position currPosition = positions.get(index);
				_linkedSpreadsheet.insertContent(currPosition, cell.getContent());
			}
			catch (NullPointerException | IndexOutOfBoundsException e) {
				// Will happen if our position is out of bounds. That's no issue - we'll just ignore that insertContent
			}
			index++;
		}
	}

	/**
	 * Checks if the interval represents a single cell or a range of cells. A single cell has the same first
	 * and last positions within the interval, while a range of cells has different first and last positions.
	 *
	 * @return {@code true} if the interval represents a single cell; {@code false} if it represents a range of cells.
	 */
	boolean isSingle() {
		return (_firstPosition.getRow() == _lastPosition.getRow()) 
				&& (_firstPosition.getColumn() == _lastPosition.getColumn());
	}

	/**
	 * Parses the gamma range coordinates and returns an array of two {@link Position} objects representing
	 * the first and last positions of an interval.
	 *
	 * @param gamma The gamma range coordinates in string format.
	 * @return An array containing two {@link Position} objects representing the first and last positions.
	 */
	private Position[] parsePositions(String gamma) throws InvalidCellIntervalException {
		String[] rangeCoordinates;
		Position[] positions = new Position[2];
		// In case it's an Interval
		try {
			if (gamma.indexOf(':') != -1) {
				rangeCoordinates = gamma.split("[:;]");
				positions[0] = new Position(Integer.parseInt(rangeCoordinates[0]), Integer.parseInt(rangeCoordinates[1]));
				positions[1] = new Position(Integer.parseInt(rangeCoordinates[2]), Integer.parseInt(rangeCoordinates[3]));
			} 
			// In case it's a sole Cell
			else {
				rangeCoordinates = gamma.split(";");
				positions[0] = new Position(Integer.parseInt(rangeCoordinates[0]), Integer.parseInt(rangeCoordinates[1]));
				positions[1] = positions[0];
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidCellIntervalException();
		}
		return positions;
	}

	/**
	 * Checks if the interval positions are on the same row.
	 *
	 * @return {@code true} if the interval positions are on the same row; {@code false} otherwise.
	 */
	private boolean onSameRow() {
		return _firstPosition.getRow() == _lastPosition.getRow();
	}

	/**
	 * Calculates the number of rows required for a new spreadsheet when copying an interval. The calculation depends
	 * on whether the interval spans multiple rows or not.
	 *
	 * @param toCopy The interval to be copied, used for determining the required rows.
	 * @return The number of rows required for a new spreadsheet to accommodate the copied interval.
	 */
	private int calculateNewSheetRows(Interval toCopy) {
		if (toCopy.getFirstPosition().getColumn() == toCopy.getLastPosition().getColumn()) {
			return toCopy.getLastPosition().getRow() - toCopy.getFirstPosition().getRow() + 1;
		}
		return 1;
	}
	
	/**
	 * Calculates the number of columns required for a new spreadsheet when copying an interval. The calculation depends
	 * on whether the interval spans multiple columns or not.
	 *
	 * @param toCopy The interval to be copied, used for determining the required columns.
	 * @return The number of columns required for a new spreadsheet to accommodate the copied interval.
	 */
	private int calculateNewSheetColumns(Interval toCopy) {
		if (toCopy.getFirstPosition().getRow() == toCopy.getLastPosition().getRow()) {
			return toCopy.getLastPosition().getColumn() - toCopy.getFirstPosition().getColumn() + 1;
		}
		return 1;
	}

	/**
	 * Copies the content from one interval to another by iterating over positions within the source interval
	 * and inserting content into the corresponding positions in the destination interval.
	 *
	 * @param toCopy The source interval from which content will be copied.
	 */
	private void copyContent(Interval toCopy) {
		List<Position> positions = getPositions();
		int index = 0;
		for (Content content: toCopy.getContent()) {
			Position currPosition = positions.get(index);
			_linkedSpreadsheet.insertContent(currPosition, content);
			index++;
		}
	}

}