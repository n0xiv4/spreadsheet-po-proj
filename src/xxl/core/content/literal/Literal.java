package xxl.core.content.literal;

import xxl.core.ReferenceUpdateVisitor;
import xxl.core.content.Content;
import xxl.core.content.Observer;
import xxl.core.exception.InvalidValueTypeException;
import xxl.core.search.SearchVisitor;

/**
 * The {@code Literal} abstract class represents a literal value in a spreadsheet.
 * It serves as the base class for specific literal types such as integers, strings, or null values.
 * Subclasses must implement methods to retrieve integer and string values.
 */
abstract public class Literal extends Content {

	/**
	 * Retrieves the value of the literal as an integer.
	 *
	 * @return The integer value of the literal.
	 * @throws InvalidValueTypeException If the value cannot be converted to an integer.
	 */
	abstract public int getIntValue() throws InvalidValueTypeException;

	/**
	 * Retrieves the value of the literal as a string.
	 *
	 * @return The string representation of the literal.
	 * @throws InvalidValueTypeException If the value cannot be converted to a string.
	 */
	abstract public String getStringValue() throws InvalidValueTypeException;

	/**
	 * Returns the literal itself as its value.
	 *
	 * @return The literal object itself.
	 */
	@Override
	public Literal getValue() {
		return this;
	}

	/**
     * Accepts a search visitor to visit and process this literal.
     *
     * @param visitor The search visitor to accept.
     */
	@Override
	public void accept(SearchVisitor visitor) {
		visitor.visit(this);
	}

	/**
     * Accepts a reference update visitor along with an observer to visit and update this literal.
     *
     * @param visitor The reference update visitor to accept.
     * @param observer The observer to be used for updates.
     */
	@Override
	public void accept(ReferenceUpdateVisitor visitor, Observer observer) {
		visitor.visit(this, observer);
	}

}
