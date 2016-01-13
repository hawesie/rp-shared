package rp.config;

import lejos.robotics.navigation.Pose;

/**
 * A class to record the description of a range scanner.
 * 
 * @author nah
 *
 */
public class RangeScannerDescription {
	private final Pose m_scannerPose;
	private final float[] m_readingAngles;
	private final float m_maxRange;
	private final float m_minRange;

	/**
	 * 
	 * @param _scannerPose
	 *            Pose of the scanner
	 * @param _readingAngles
	 *            Directions to take readings in relative to pose.
	 */
	public RangeScannerDescription(Pose _scannerPose, float[] _readingAngles,
			float _maxRange, float _minRange) {
		m_scannerPose = _scannerPose;
		m_readingAngles = _readingAngles;
		m_maxRange = _maxRange;
		m_minRange = _minRange;
	}

	public float[] getReadingAngles() {
		return m_readingAngles;
	}

	public Pose getScannerPose() {
		return m_scannerPose;
	}

	public float getMaxRange() {
		return m_maxRange;
	}

	public float getMinRange() {
		return m_minRange;
	}
}
