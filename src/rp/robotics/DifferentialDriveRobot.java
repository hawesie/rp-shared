package rp.robotics;

import java.util.ArrayList;

import lejos.geom.Line;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;
import rp.config.RangeScannerDescription;
import rp.config.WheeledRobotConfiguration;
import rp.config.WheeledRobotDescription;
import rp.robotics.localisation.ContinuousOdometryPoseProvider;
import rp.systems.WheeledRobotSystem;

/**
 * An abstraction for a differential drive robot. If you use this class in your
 * code you should be able to write control code that is independent of a
 * particular robot design. This class can be used on both the robot and in
 * simulation.
 * 
 * @author Nick Hawes
 *
 */
public class DifferentialDriveRobot implements PoseProvider,
		WheeledRobotDescription {

	private final WheeledRobotConfiguration m_config;

	private boolean m_inCollision = false;
	private Pose m_collisionPose = null;

	public float getWheelDiameter() {
		return m_config.getWheelDiameter();
	}

	public float getTrackWidth() {
		return m_config.getTrackWidth();
	}

	public RegulatedMotor getLeftWheel() {
		return m_config.getLeftWheel();
	}

	public RegulatedMotor getRightWheel() {
		return m_config.getRightWheel();
	}

	public double getRobotLength() {
		return m_config.getRobotLength();
	}

	private final DifferentialPilot m_pilot;

	private final ContinuousOdometryPoseProvider m_odomPose;

	public DifferentialDriveRobot(WheeledRobotConfiguration _config) {
		m_config = _config;
		m_pilot = new WheeledRobotSystem(m_config).getPilot();
		m_odomPose = new ContinuousOdometryPoseProvider(m_pilot);
	}

	/**
	 * Returns the ground truth pose from the simulation.
	 */
	@Override
	public Pose getPose() {
		if (!m_inCollision) {
			return m_odomPose.getPose();
		} else {
			return m_collisionPose;
		}
	}

	/***
	 * Changes the pose of the robot in the simulation.
	 */
	@Override
	public void setPose(Pose _pose) {
		m_odomPose.setPose(_pose);
	}

	public DifferentialPilot getDifferentialPilot() {
		return m_pilot;
	}

	@Override
	public Line[] getFootprint() {
		return m_config.getFootprint();
	}

	// When the robot is in collision the wheels keep turning, but the pose of
	// the robot doesn't change
	public void startCollision() {
		if (!m_inCollision) {
			m_collisionPose = getPose();
			m_inCollision = true;
		}
	}

	@Override
	public ArrayList<Line[]> getTouchSensors() {
		return m_config.getTouchSensors();
	}

	@Override
	public ArrayList<RangeScannerDescription> getRangeScanners() {
		return m_config.getRangeScanners();
	}

}
