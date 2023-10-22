package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class FunctionManager implements Serializable {

	private List<IntervalFunction> _observers;

	/** The serial version UID for object serialization. */
	@Serial
	private static final long serialVersionUID = 2023102300451L;

	FunctionManager() {
		_observers = new ArrayList<IntervalFunction>();
	}

	void addFunction(IntervalFunction observer) {
		_observers.add(observer);
	}

	void notifyFunctions() {
		for (IntervalFunction function: _observers) {
			function.update();
		}
	}
	
}
