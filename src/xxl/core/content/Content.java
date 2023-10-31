package xxl.core.content;

import java.io.Serial;
import java.io.Serializable;

import xxl.core.ReferenceUpdateVisitor;
import xxl.core.content.literal.Literal;
import xxl.core.search.SearchVisitor;

/**
 * The {@code Content} class represents the content of a cell in a spreadsheet.
 * It serves as a base class for various types of content, such as literals, functions, and references.
 * Subclasses of this class must implement the {@link #getValue()} and {@link #toString()} methods.
 * 
 * @Serial 202310081841L
 */
public abstract class Content implements Serializable {
	
	/** The serial version UID for object serialization. */
	@Serial
	private static final long serialVersionUID = 202310081841L;

	/**
	 * Returns a string representation of the content.
	 *
	 * @return A string representation of the content.
	 */
	abstract public String toString();
	
	/**
	 * Retrieves the value of the content, which is specific to the content type.
	 *
	 * @return The value of the content as a {@link Literal}.
	 */
	abstract public Literal getValue();

	/**
     * Accepts a search visitor to visit and process this content.
     *
     * @param visitor The search visitor to accept.
     */
	abstract public void accept(SearchVisitor visitor);

	/**
     * Accepts a reference update visitor along with an observer to visit and update this content.
     *
     * @param visitor The reference update visitor to accept.
     * @param observer The observer to be used for updates.
     */
	abstract public void accept(ReferenceUpdateVisitor visitor, Observer observer);

}

