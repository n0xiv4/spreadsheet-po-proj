package xxl.core;

// FIXME import classes

import java.util.Collection;
import java.util.ArrayList;

import java.io.Serial;
import java.io.Serializable;

import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
	private Collection<User> _users;
	private Collection<Cell> _cells;
  
  @Serial
  private static final long serialVersionUID = 202308312359L;
  
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods
  
  public Spreadsheet(int rows, int columns) {
		_users = new ArrayList<User>();
		// FIXME rest
		// FIXME function (separate) to populate sheet with cells
  }

  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, Content contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
	// FIXME implement method
  }

	public Cell findCellByPosition(Position cellPos) {
		for (Cell cell : _cells) {
        if (cell.getPosition().equals(cellPos)) {
            return cell;
        }
    }
    return null;
	}

	void linkUser(User user) {
		// FIXME caution with bidirectional link!! where does it start?
		_users.add(user);
	}
}
