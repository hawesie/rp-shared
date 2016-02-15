package rp.util;

import java.util.Hashtable;

import lejos.robotics.RangeReadings;
import rp.config.RangeScannerDescription;

/**
 * 
 * Utility functions to make using certain sensors easier.
 * 
 * @author Nick Hawes
 *
 */
public abstract class SensorUtils {

	public static final Hashtable<Integer, String> COLOUR_NAMES;

	static {
		COLOUR_NAMES = new Hashtable<Integer, String>();
		COLOUR_NAMES.put(0, "black");
		COLOUR_NAMES.put(1, "violet");
		COLOUR_NAMES.put(2, "purple");
		COLOUR_NAMES.put(3, "blue");
		COLOUR_NAMES.put(4, "green");
		COLOUR_NAMES.put(5, "lime");
		COLOUR_NAMES.put(6, "yellow");
		COLOUR_NAMES.put(7, "orange");
		COLOUR_NAMES.put(8, "red");
		COLOUR_NAMES.put(9, "crimson");
		COLOUR_NAMES.put(10, "magenta");
		COLOUR_NAMES.put(11, "pastel");
		COLOUR_NAMES.put(12, "pastel");
		COLOUR_NAMES.put(13, "pastel");
		COLOUR_NAMES.put(14, "pastel");
		COLOUR_NAMES.put(15, "pastel");
		COLOUR_NAMES.put(16, "pastel");
		COLOUR_NAMES.put(17, "white");
	}

	public static String toColourName(int _colourNumber) {
		return COLOUR_NAMES.get(_colourNumber);
	}

	/**
	 * Return a list of range readings which are the minimum for each individual
	 * reading paired across the inputs.
	 * 
	 * @param _r1
	 * @param _r2
	 * @return
	 */
	public static RangeReadings getMinimumValues(RangeReadings _r1,
			RangeReadings _r2) {
		RangeReadings jointReadings = new RangeReadings(_r2.getNumReadings());

		assert _r1.size() <= _r2.size();

		for (int i = 0; i < _r2.getNumReadings(); i++) {

			assert _r1.getAngle(i) == _r2.getAngle(i);

			float map = _r1.getRange(i);
			float obs = _r2.getRange(i);

			// System.out.println("map: " + mapReadings.getRange(i));
			// System.out.println("obs: " + obstacleReadings.getRange(i));

			if (RangeScannerDescription.isValidReading(map)
					&& RangeScannerDescription.isValidReading(obs)) {
				jointReadings.setRange(i, _r1.getAngle(i), Math.min(map, obs));
			} else if (!RangeScannerDescription.isValidReading(map)) {
				jointReadings.setRange(i, _r1.getAngle(i), obs);
			} else {
				jointReadings.setRange(i, _r1.getAngle(i), map);
			}

		}
		return jointReadings;
	}

}
