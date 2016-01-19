package rp.robotics;

import rp.config.RangeScannerDescription;
import lejos.robotics.RangeFinder;
import lejos.robotics.RangeScanner;
import lejos.robotics.localization.PoseProvider;

/**
 * Defines an interface for a range scanner that has a pose. The angles for the
 * range scanner are relative to the heading of the pose. When just the
 * RangeFinder interface it reports the first result from the range of reading
 * angles.
 * 
 * @author Nick Hawes
 *
 */
public interface LocalisedRangeScanner extends PoseProvider, RangeScanner,
		RangeFinder {

	public RangeScannerDescription getDescription();

}
