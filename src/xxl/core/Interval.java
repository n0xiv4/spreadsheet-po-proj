package xxl.core;

import java.io.Serializable;

/**
 * The {@code Interval} class represents a rectangular interval or range of positions within a
 * {@link Spreadsheet}. It defines the first and last positions of the interval and the associated
 * spreadsheet.
 */
public class Interval implements Serializable {
	private Position _firstPosition;
	private Position _lastPosition;
	/** _linkedSpreadsheet will only be used for Interval operations, hence currently unused. **/
	private Spreadsheet _linkedSpreadsheet;

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
}

/* Na classe Spreadsheet preciso de algo com a seguinte funcionalidade
Range createRange(String range) throws ? {
	String[] rangeCoordinates;
	int firstRow, firstColumn, lastRow, lastColumn;
	
	if (range.indexOf(':') != -1) {
		rangeCoordinates = range.split("[:;]");
		firstRow = Integer.parseInt(rangeCoordinates[0]);
		firstColumn = Integer.parseInt(rangeCoordinates[1]);
		lastRow = Integer.parseInt(rangeCoordinates[2]);
		lastColumn = Integer.parseInt(rangeCoordinates[3]);
	} else {
		rangeCoordinates = range.split(";");
		firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
		firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
	}

	// check if coordinates are valid
	// if yes
	return new Range with firstRow, firstColumn, lastRow, lastColumn and spreadsheet;
}
*/