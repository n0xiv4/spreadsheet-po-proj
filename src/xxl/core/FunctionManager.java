package xxl.core;

import java.util.List;
import java.util.ArrayList;

public class FunctionManager {

	private List<IntervalFunction> _observers;

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
