package rp.config;

import java.util.ArrayList;

import lejos.geom.Line;
import lejos.robotics.navigation.Pose;

public class MobileRobotConfiguration implements MobileRobotDescription {

	protected final float m_trackWidth;
	protected final float m_robotLength;
	protected final Line[] m_footprint;
	private ArrayList<Line[]> m_touchSensors;
	private ArrayList<RangeScannerDescription> m_rangeScanners;

	public MobileRobotConfiguration(float _trackWidth, float _robotLength) {
		m_trackWidth = _trackWidth;
		m_robotLength = _robotLength;

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

	public double getRobotLength() {
		return m_robotLength;
	}

	public Line[] getFootprint() {
		return m_footprint;
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
	public RangeScannerDescription addRangeScanner() {
		return addRangeScanner(new Pose(0f, 0f, 0f), new float[] { 0f }, 2.40f,
				0.03f, 0.03f, 10f);
	}

	/**
	 * Add a range scanner relative to the robot origin.
	 * 
	 * @param _scannerPose
	 *            Pose of the scanner relative to robot origin.
	 * @param _readingAngles
	 *            Directions to take readings in relative to pose.
	 */
	public RangeScannerDescription addRangeScanner(Pose _scannerPose,
			float[] _readingAngles, float _maxRange, float _minRange,
			float _noise, float _rate) {
		if (m_rangeScanners == null) {
			m_rangeScanners = new ArrayList<RangeScannerDescription>(1);
		}

		RangeScannerDescription desc = new RangeScannerDescription(
				_scannerPose, _maxRange, _minRange, _noise, _rate,
				_readingAngles);

		m_rangeScanners.add(desc);

		return desc;
	}

	public ArrayList<RangeScannerDescription> getRangeScanners() {
		return m_rangeScanners;
	}

	@Override
	public float getTrackWidth() {
		return m_trackWidth;
	}

}