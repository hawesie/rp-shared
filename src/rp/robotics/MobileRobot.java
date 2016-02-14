package rp.robotics;

import java.util.ArrayList;

import lejos.geom.Line;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.Pose;
import rp.config.MobileRobotConfiguration;
import rp.config.MobileRobotDescription;
import rp.config.RangeScannerDescription;

/**
 * An abstraction for a mobile robot. If you use this class in your code you
 * should be able to write control code that is independent of a particular
 * robot design. This class can be used on both the robot and in simulation.
 * 
 * @author Nick Hawes
 *
 */
public class MobileRobot implements PoseProvider, MobileRobotDescription {

	private final MobileRobotDescription m_config;

	private boolean m_inCollision = false;
	private Pose m_collisionPose = null;
	private final PoseProvider m_poser;

	public float getTrackWidth() {
		return m_config.getTrackWidth();
	}

	public double getRobotLength() {
		return m_config.getRobotLength();
	}

	public MobileRobot(MobileRobotConfiguration _config, PoseProvider _poser) {
		m_config = _config;
		m_poser = _poser;
	}

	/**
	 * Returns the ground truth pose from the simulation.
	 */
	@Override
	public Pose getPose() {
		if (!m_inCollision) {
			return m_poser.getPose();
		} else {
			return m_collisionPose;
		}
	}

	/***
	 * Changes the pose of the robot in the simulation.
	 */
	@Override
	public void setPose(Pose _pose) {
		m_poser.setPose(_pose);
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
