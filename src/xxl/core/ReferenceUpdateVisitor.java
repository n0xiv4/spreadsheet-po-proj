package xxl.core;

import xxl.core.content.Observer;
import xxl.core.content.Reference;
import xxl.core.content.function.Function;
import xxl.core.content.literal.Literal;

/**
 * A visitor for updating references.
 */
public class ReferenceUpdateVisitor {

	/**
	 * Visits a literal content.
	 *
	 * @param literal The literal content to visit.
	 * @param observer For future implementation if needed.
	 */
	public void visit(Literal literal, Observer observer) {
		// Nothing happens, reference only
	}

	/**
	 * Visits a reference node in the syntax tree and updates it with an observer.
	 *
	 * @param reference The reference node to visit and update.
	 * @param observer The observer to be added to the reference.
	 */
	public void visit(Reference reference, Observer observer) {
		reference.addObserver(observer);
		reference.getContent().accept(this, observer);
	}

	/**
	 * Visits a function content.
	 *
	 * @param function The function content to visit.
	 * @param observer For future implementation if needed.
	 */
	public void visit(Function function, Observer observer) {
		// Nothing happens, reference only
	}

}
