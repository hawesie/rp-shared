package rp.config;

import lejos.robotics.RegulatedMotor;

/**
 * This adds wheels to the robot description.
 * 
 * @author Nick Hawes
 *
 */
public interface WheeledRobotDescription extends MobileRobotDescription {

	public float getWheelDiameter();

	public RegulatedMotor getLeftWheel();

	public RegulatedMotor getRightWheel();

}
