package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * The {@code FunctionManager} class manages a collection of {@link IntervalFunction} objects and provides methods
 * to add functions and notify them.
 *
 * @Serial 2023102300451L
 */
public class FunctionManager implements Serializable {

	/**
     * The list of observers ({@code IntervalFunction}) managed by this {@code FunctionManager}.
     */
	private List<IntervalFunction> _observers;

	/** The serial version UID for object serialization. */
	@Serial
	private static final long serialVersionUID = 2023102300451L;

	/**
     * Constructs a new {@code FunctionManager} and initializes the list of observers.
     */
	FunctionManager() {
		_observers = new ArrayList<IntervalFunction>();
	}

	/**
     * Adds an {@code IntervalFunction} to the list of observers.
     *
     * @param observer The {@code IntervalFunction} to be added.
     */
	void addFunction(IntervalFunction observer) {
		_observers.add(observer);
	}

	/**
     * Notifies all registered {@code IntervalFunction} objects by calling their {@code update} method.
     */
	void notifyFunctions() {
		for (IntervalFunction function: _observers) {
			function.update();
		}
	}
}
