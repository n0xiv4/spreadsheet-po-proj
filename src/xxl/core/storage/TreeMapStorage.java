package xxl.core.storage;

import java.util.TreeMap;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import xxl.core.Position;

/**
 * A class that implements the {@link Storage} interface to manage storage of elements using a TreeMap-based data structure.
 *
 * @param <T> The type of elements to be stored.
 */
public class TreeMapStorage<T> implements Storage<T>, Serializable {
	
	/** The storage variable - in this case, an TreeMap. */
	private Map<Position, T> _stored;

	/**
	 * Initializes a new instance of the {@code TreeMapStorage} class.
	 */
	public TreeMapStorage() {
		_stored = new TreeMap<>();
	}

	/**
	 * Sets the element at the specified position in the storage.
	 *
	 * @param position The position at which to store the element.
	 * @param element  The element to store.
	 */
	@Override
	public void set(Position position, T element) {
		_stored.put(position, element);
	}

	/**
	 * Retrieves the element at the specified position from the storage.
	 *
	 * @param position The position of the element to retrieve.
	 * @return The element at the specified position, or null if it does not exist.
	 */
	@Override
	public T get(Position position) {
		return _stored.get(position);
	}

	/**
	 * Gets an iterator for iterating over elements in the storage.
	 *
	 * @return An iterator for the elements in the storage.
	 */
	@Override
	public Iterator<T> iterator() {
		return _stored.values().iterator();
	}

}
