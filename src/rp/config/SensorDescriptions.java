package rp.config;

import lejos.robotics.navigation.Pose;

public class SensorDescriptions {

	public final static RangeFinderDescription OPTICAL_DISTANCE_MEDIUM = new RangeFinderDescription(
			new Pose(0, 0, 0), 0.8f, 0.1f, 0f, 10f);

}
