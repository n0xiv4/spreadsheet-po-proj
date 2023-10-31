package xxl.core.storage;

import java.util.Iterator;

import xxl.core.Position;

/**
 * An interface for managing storage of elements of type T.
 *
 * @param <T> The type of elements to be stored.
 */
public interface Storage<T> {

	/**
	* Set the element at the specified position in the storage.
	*
	* @param position The position at which to store the element.
	* @param element  The element to store.
	*/
	public void set(Position position, T element);

	/**
	* Get the element at the specified position from the storage.
	*
	* @param position The position of the element to retrieve.
	* @return The element at the specified position, or null if it does not exist.
	*/
	public T get(Position position);

	/**
	* Get an iterator for iterating over elements in the storage.
	*
	* @return An iterator for the elements in the storage.
	*/
	public Iterator<T> iterator();

}
