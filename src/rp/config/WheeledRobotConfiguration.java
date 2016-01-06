package rp.config;

import lejos.geom.Line;
import lejos.geom.Rectangle;
import lejos.robotics.RegulatedMotor;

/**
 * A class to store configuration information for a wheeled robot. You could
 * subclass this to also contain information about sensor ports.
 * 
 * @author Nick
 * 
 */
public class WheeledRobotConfiguration implements WheeledRobotDescription {

	private final float m_wheelDiameter;
	private final float m_trackWidth;
	private final float m_robotLength;
	private final RegulatedMotor m_leftWheel;
	private final RegulatedMotor m_rightWheel;

	private final Line[] m_footprint;

	public float getWheelDiameter() {
		return m_wheelDiameter;
	}

	public float getTrackWidth() {
		return m_trackWidth;
	}

	public RegulatedMotor getLeftWheel() {
		return m_leftWheel;
	}

	public RegulatedMotor getRightWheel() {
		return m_rightWheel;
	}

	public double getRobotLength() {
		return m_robotLength;
	}

	public Line[] getFootprint() {
		return m_footprint;
	}

	public WheeledRobotConfiguration(float _wheelDiameter, float _trackWidth,
			float _robotLength, RegulatedMotor _leftWheel,
			RegulatedMotor _rightWheel) {
		super();
		m_wheelDiameter = _wheelDiameter;
		m_trackWidth = _trackWidth;
		m_robotLength = _robotLength;
		m_leftWheel = _leftWheel;
		m_rightWheel = _rightWheel;

		// assuming pose 0,0,0 is in the middle of the robot,this defines
		// the footprint of the robot. We'll start with a square based on track
		// width and robot length. These are done in relative coordinates to the
		// robot centre.

		float halfX = m_robotLength / 2;
		float halfY = m_trackWidth / 2;

		m_footprint = new Line[] {
				// front
				new Line(halfX, halfY, halfX, -halfY),
				// back
				new Line(-halfX, halfY, -halfX, -halfY),
				// top
				new Line(-halfX, halfY, halfX, halfY),
				// bottom
				new Line(-halfX, -halfY, halfX, -halfY),

		};
	}

}
