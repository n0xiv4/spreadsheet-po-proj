package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xxl.core.content.Content;
import xxl.core.content.Observer;
import xxl.core.content.literal.Literal;
import xxl.core.content.literal.LiteralNull;
import xxl.core.content.literal.LiteralNullValue;

/**
 * The {@code Cell} class represents a cell in a spreadsheet.
 * It contains information about its position (row and column coordinates) and the content it holds.
 * Cells can store various types of content, such as literals, functions, or references.
 * 
 * @Serial 202310072353L
 */
public class Cell implements Serializable {

	/** The content stored in the cell. */
	private Content _content;

	/** The position (row and column coordinates) of the cell. */
	private Position _position;

	/** The list of observers of the cell. Naturally they are updated after changes. */
	private List<Observer> _observers;

	/** The serial version UID for object serialization. */
	@Serial
	private static final long serialVersionUID = 202310072353L;
	
	/**
	 * Creates a new {@link Cell} object with the specified row and column coordinates.
	 * The cell is initially empty and contains a {@link LiteralNull} as its content.
	 *
	 * @param row    The row of the new cell.
	 * @param column The column of the new cell.
	 */
	public Cell(int row, int column) {
		_position = new Position(row, column);
		_content = new LiteralNullValue();
		_observers = new ArrayList<Observer>();
	}

	/**
	 * Constructs a new {@link Cell} object with the specified position.
	 * The cell is initially empty and contains a {@link LiteralNull} as its content.
	 *
	 * @param position The {@link Position} object representing the row and column coordinates.
	 */
	public Cell(Position position) {
		_position = position;
		_content = new LiteralNullValue();
		_observers = new ArrayList<Observer>();
	}

	/**
	 * Retrieves the position (row and column coordinates) of this {@link Cell}.
	 *
	 * @return A new {@link Position} object representing the row and column coordinates of this cell.
	 */
	public Position getPosition() {
		return _position;
	}

	/**
	 * Retrieves the content stored in this cell.
	 *
	 * @return The {@link Content} stored in this cell.
	 */
	public Content getContent() {
		return _content;
	}

	/**
	 * Returns a string representation of the cell, including its position and content.
	 *
	 * @return A string in the format "row;column|content".
	 */
	@Override
	public String toString() {
		return _position.toString() + "|" + _content.toString();
	}

	/**
	 * Retrieves the value of the cell's content.
	 *
	 * @return The value of the cell's content as a {@link Literal}.
	 */
	public Literal getValue() {
		return _content.getValue();
	}

	/**
	 * Adds an observer to the cell's list of observers.
	 *
	 * @param observer The observer to be added to the list of observers.
	 */
	public void addObserver(Observer observer) {
		_observers.add(observer);
	}

	/**
	 * Removes an observer from the cell's list of observers.
	 *
	 * @param observer The observer to be removed from the list of observers.
	 */
	public void removeObserver(Observer observer) {
		_observers.remove(observer);
	}

	/**
	 * Notifies all registered observers about changes in the cell's state.
	 */
	public void notifyObservers() {
		for (Observer observer: _observers) {
			observer.update();
		}
	}

	/**
	 * Sets the content for the cell.
	 *
	 * @param content The type of content to be set in the cell.
	 */
	void setContent(Content content) {
		_content = content;
		notifyObservers();
	}

}
