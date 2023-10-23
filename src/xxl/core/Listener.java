package xxl.core;

/**
 * The {@code Listener} interface represents an object that can listen for updates.
 * Implementing classes should provide an {@code update} method that is called when an update event occurs.
 */
public interface Listener {
	/**
     * Called to notify the listener of an update event.
     */
	public void update();
}
