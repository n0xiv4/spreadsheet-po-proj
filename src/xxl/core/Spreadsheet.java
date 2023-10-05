package xxl.core;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;

import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;
  
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods
  
  public Spreadsheet(int rows, int columns) {
    // FIXME construct spreadsheet
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

  public static void main(String[] args) {
    System.out.println("Hey!");
  }
}
