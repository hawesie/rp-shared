package rp.robotics;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
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
public class DifferentialDriveRobot extends MobileRobot implements
		WheeledRobotDescription {

	private final WheeledRobotConfiguration m_config;

	public RegulatedMotor getLeftWheel() {
		return m_config.getLeftWheel();
	}

	public RegulatedMotor getRightWheel() {
		return m_config.getRightWheel();
	}

	private final DifferentialPilot m_pilot;

	public DifferentialDriveRobot(WheeledRobotConfiguration _config) {
		this(_config, new WheeledRobotSystem(_config));
	}

	public DifferentialDriveRobot(WheeledRobotConfiguration _config,
			WheeledRobotSystem _system) {
		super(_config, new ContinuousOdometryPoseProvider(_system.getPilot()));
		m_config = _config;
		m_pilot = _system.getPilot();
	}

	public DifferentialPilot getDifferentialPilot() {
		return m_pilot;
	}

	@Override
	public float getWheelDiameter() {
		return m_config.getWheelDiameter();
	}

}
