package rp.robotics;

/**
 * 
 * An interface for an event-based touch sensor.
 * 
 * @author Nick Hawes
 *
 */
public interface TouchSensorEventSource {

	/**
	 * Add a new listener the event source.
	 * 
	 * @param _listener
	 */
	public void addTouchSensorListener(TouchSensorListener _listener);

}
