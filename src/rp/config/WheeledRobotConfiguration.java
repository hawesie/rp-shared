package rp.config;

import java.util.ArrayList;

import lejos.geom.Line;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.Pose;

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

	private ArrayList<Line[]> m_touchSensors;
	private ArrayList<RangeScannerDescription> m_rangeScanners;

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

	/**
	 * Adds a touch sensor to the front of the robot.
	 */
	public void addTouchSensor() {

		float halfX = m_robotLength / 2;
		float halfY = m_trackWidth / 2;

		float sensorLength = 0.04f;
		float sensorWidth = 0.01f;

		addTouchSensor(new Line[] {
				// front
				new Line(halfX + sensorLength, sensorWidth / 2, halfX
						+ sensorLength, -sensorWidth / 2),
				// back
				new Line(halfX, sensorWidth / 2, halfX, -sensorWidth / 2),
				// top
				new Line(halfX, sensorWidth / 2, halfX + sensorLength,
						sensorWidth / 2),
				// bottom
				new Line(halfX, -sensorWidth / 2, halfX + sensorLength,
						-sensorWidth / 2),
				// bumper
				new Line(halfX + sensorLength, halfY, halfX + sensorLength,
						-halfY),

		});

	}

	/**
	 * Adds a touch sensor with the given outline relative to the robot centre.
	 * 
	 * @param _sensorFootprint
	 */
	public void addTouchSensor(Line[] _sensorFootprint) {
		if (m_touchSensors == null) {
			m_touchSensors = new ArrayList<Line[]>(1);
		}
		m_touchSensors.add(_sensorFootprint);
	}

	public ArrayList<Line[]> getTouchSensors() {
		return m_touchSensors;
	}

	/**
	 * Add a range scanner which takes a single reading and is located at the
	 * centre of the robot facing forward.
	 */
	public void addRangeScanner() {
		addRangeScanner(new Pose(0f, 0f, 0f), new float[] { 0f }, 2.40f, 0.03f);
	}

	/**
	 * Add a range scanner relative to the robot origin.
	 * 
	 * @param _scannerPose
	 *            Pose of the scanner relative to robot origin.
	 * @param _readingAngles
	 *            Directions to take readings in relative to pose.
	 */
	public void addRangeScanner(Pose _scannerPose, float[] _readingAngles,
			float _maxRange, float _minRange) {
		if (m_rangeScanners == null) {
			m_rangeScanners = new ArrayList<RangeScannerDescription>(1);
		}

		m_rangeScanners.add(new RangeScannerDescription(_scannerPose,
				_readingAngles, _maxRange, _minRange));
	}

	@Override
	public ArrayList<RangeScannerDescription> getRangeScanners() {
		return m_rangeScanners;
	}

}
