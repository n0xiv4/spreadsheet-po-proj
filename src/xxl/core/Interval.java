package xxl.core;

import java.io.Serial;
import java.io.Serializable;

import xxl.app.exception.InvalidCellRangeException;

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
	Interval(Position firstPosition, Position lastPosition, Spreadsheet spreadsheet) {
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
	Interval(Position lastPosition, Spreadsheet spreadsheet) {
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
	 * @throws InvalidCellRangeException if the provided gamma range is not a valid cell range.
	 */
	Interval(String gamma, Spreadsheet spreadsheet) throws InvalidCellRangeException {
		Position[] intervalPositions = parsePositions(gamma);
		_firstPosition = intervalPositions[0];
		_lastPosition = intervalPositions[1];
		_linkedSpreadsheet = spreadsheet;
		// FIXME
		if (!isInsideSpreadsheet() || !Position.isCompatibleForInterval(_firstPosition, _lastPosition)) {
			throw new InvalidCellRangeException(gamma);
		}
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
		// If compareTo = 1, lastPosition is OUT of the spreadsheet range.
		return _lastPosition.compareTo(_linkedSpreadsheet.getLastPosition()) < 1;
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
	 * Parses the gamma range coordinates and returns an array of two {@link Position} objects representing
	 * the first and last positions of an interval.
	 *
	 * @param gamma The gamma range coordinates in string format.
	 * @return An array containing two {@link Position} objects representing the first and last positions.
	 * @throws InvalidCellRangeException if the provided gamma range is not a valid cell range.
	 */
	private Position[] parsePositions(String gamma) throws InvalidCellRangeException {
		String[] rangeCoordinates;
		Position[] positions = new Position[2];
		// In case it's an Interval
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
}