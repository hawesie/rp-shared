package rp.systems;

/**
 * An interface for a runnable that also includes a public stop method. Calling
 * the stop method should cause the runnable to stop running and the run()
 * method to exit.
 * 
 * 
 * @author Nick Hawes
 * 
 */
public interface StoppableRunnable extends Runnable {

	/**
	 * Stop the runnable, causing the run() method to exit if it is active.
	 */
	void stop();
}
