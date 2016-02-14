package rp.config;

import lejos.robotics.RegulatedMotor;

/**
 * A class to store configuration information for a wheeled robot. You could
 * subclass this to also contain information about sensor ports.
 * 
 * @author Nick Hawes
 * 
 */
public class WheeledRobotConfiguration extends MobileRobotConfiguration
		implements WheeledRobotDescription {

	private final float m_wheelDiameter;
	private final RegulatedMotor m_leftWheel;
	final RegulatedMotor m_rightWheel;

	public float getWheelDiameter() {
		return m_wheelDiameter;
	}

	public float getTrackWidth() {
		return m_trackWidth;
	}

	public RegulatedMotor getLeftWheel() {
		return m_leftWheel;
	}

	public WheeledRobotConfiguration(float _wheelDiameter, float _trackWidth,
			float _robotLength, RegulatedMotor _leftWheel,
			RegulatedMotor _rightWheel) {
		super(_trackWidth, _robotLength);
		m_wheelDiameter = _wheelDiameter;
		m_leftWheel = _leftWheel;
		m_rightWheel = _rightWheel;

	}

	@Override
	public RegulatedMotor getRightWheel() {
		return m_rightWheel;
	}

}
