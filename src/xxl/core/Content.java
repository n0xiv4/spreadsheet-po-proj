package xxl.core;

import java.io.Serial;
import java.io.Serializable;

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
	abstract Literal getValue();
}

