package rp.config;

import java.util.ArrayList;

import lejos.geom.Line;
import lejos.robotics.RegulatedMotor;

public interface WheeledRobotDescription {
	public float getWheelDiameter();

	public float getTrackWidth();

	public RegulatedMotor getLeftWheel();

	public RegulatedMotor getRightWheel();

	public double getRobotLength();

	/**
	 * Returns a shape which describes the outline of the robot relative to its
	 * centre point.
	 * 
	 * @return
	 */
	public Line[] getFootprint();

	/**
	 * Returns the relative footprint of any touch sensors.
	 * 
	 * @return
	 */
	public ArrayList<Line[]> getTouchSensors();

	/**
	 * Returns the description of any range sensors.
	 * 
	 * @return
	 */
	public ArrayList<RangeScannerDescription> getRangeScanners();
}
