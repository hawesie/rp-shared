package rp.config;

import lejos.robotics.navigation.Pose;

/**
 * A class to record the description of a range finding sensor positioned at a
 * relative position to a robot's centre point.
 * 
 * @author Nick Hawes
 *
 */
public class RangeFinderDescription {

	private final Pose m_scannerPose;
	private final float m_maxRange;
	private final float m_minRange;
	private final float m_noise;

	public static final float OUT_OF_RANGE_VALUE = Float.NaN;

	/**
	 * 
	 * @param _scannerPose
	 *            Pose of the scanner relative to the robot's centre point.
	 * 
	 * @param _maxRange
	 *            The maximum range of the sensor.
	 * 
	 * @param _minRange
	 *            The minimum range of the sensor.
	 * 
	 * @param _noise
	 *            The absolution value of the noise present in this sensor. For
	 *            example a value of 0.03 means noise of +/-3cm.
	 * 
	 */
	public RangeFinderDescription(Pose _scannerPose, float _maxRange,
			float _minRange, float _noise) {
		m_scannerPose = _scannerPose;
		m_maxRange = _maxRange;
		m_minRange = _minRange;
		m_noise = _noise;
	}

	/**
	 * The relative pose of the sensor with respect to the origin of the robot.
	 * 
	 * @return
	 */
	public Pose getScannerPose() {
		return m_scannerPose;
	}

	public float getMaxRange() {
		return m_maxRange;
	}

	public float getMinRange() {
		return m_minRange;
	}

	/**
	 * Returns the absolution value of the noise present in this sensor. For
	 * example a value of 0.03 means noise of +/- 3cm.
	 * 
	 * @return
	 */
	public float getNoise() {
		return m_noise;
	}
}
