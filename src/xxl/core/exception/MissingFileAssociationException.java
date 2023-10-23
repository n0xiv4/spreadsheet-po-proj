package xxl.core.exception;

/**
 * Thrown when an application is not associated with a file.
 */
public class MissingFileAssociationException extends Exception {
	
	/**
	 * Constructs a new MissingFileAssociationException.
	 */
	public MissingFileAssociationException() {
		super("A aplicação não está associada com um ficheiro.");
	}
}
