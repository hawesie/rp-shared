package rp.robotics;

import lejos.geom.Line;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;
import rp.config.WheeledRobotConfiguration;
import rp.config.WheeledRobotDescription;
import rp.systems.WheeledRobotSystem;

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

	private final OdometryPoseProvider m_odomPose;

	public DifferentialDriveRobot(WheeledRobotConfiguration _config) {
		m_config = _config;
		m_pilot = new WheeledRobotSystem(m_config).getPilot();
		m_odomPose = new OdometryPoseProvider(m_pilot);
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
	
	

}
