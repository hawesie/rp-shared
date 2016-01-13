package rp.robotics;

/**
 * This describes an object which can listen for events from a touch sensor.
 * 
 * @author nah
 *
 */
public interface TouchSensorListener {

	/**
	 * Called when the sensor is pressed.
	 * 
	 * @param _e
	 */
	void sensorPressed(TouchSensorEvent _e);

	/**
	 * Called when the sensor is released.
	 * 
	 * @param _e
	 */
	void sensorReleased(TouchSensorEvent _e);

	/**
	 * Called when the sensor is pressed then released.
	 * 
	 * @param _e
	 */
	void sensorBumoed(TouchSensorEvent _e);

}
