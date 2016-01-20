package rp.config;

import lejos.robotics.navigation.Pose;

/**
 * A class to record the description of a range scanner.
 * 
 * @author nah
 *
 */
public class RangeScannerDescription extends RangeFinderDescription {

	private final float[] m_readingAngles;

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
	 *
	 * @param _readingAngles
	 *            Directions to take readings in relative to pose.
	 */
	public RangeScannerDescription(Pose _scannerPose, float _maxRange,
			float _minRange, float _noise, float _rate, float[] _readingAngles) {
		super(_scannerPose, _maxRange, _minRange, _noise, _rate);
		m_readingAngles = _readingAngles;
	}

	public float[] getReadingAngles() {
		return m_readingAngles;
	}

}
