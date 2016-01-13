package rp.robotics.mapping;

import rp.config.RangeScannerDescription;
import rp.geom.GeometryUtils;
import lejos.geom.Line;
import lejos.geom.Point;
import lejos.geom.Rectangle;
import lejos.robotics.RangeReadings;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;

/**
 * An extension of the leJOS LineMap containing a few bug fixes.
 * 
 * @author Nick Hawes
 * 
 */
public class RPLineMap extends LineMap {

	// the longest line possible on the map. Used to check for out of range
	// readings in range()
	private final float m_largestDimension;

	/**
	 * 
	 * @param _lines
	 *            The lines that will make up the underlying LineMap
	 * @param _boundingRect
	 *            The bounding rectangle of the whole map
	 */
	public RPLineMap(Line[] _lines, Rectangle _boundingRect) {
		super(_lines, _boundingRect);
		m_largestDimension = Math
				.max(_boundingRect.width, _boundingRect.height);

	}

	/**
	 * Calculate the range from the robot to the nearest wall. Edited version
	 * from LineMap as it doesn't handle maps over a certain size.
	 * 
	 * @param pose
	 *            the pose of the robot
	 * @return the range or -1 if not in range
	 */
	@Override
	public float range(Pose pose) {
		Line l = new Line(pose.getX(), pose.getY(), pose.getX()
				+ m_largestDimension
				* (float) Math.cos(Math.toRadians(pose.getHeading())),
				pose.getY() + m_largestDimension
						* (float) Math.sin(Math.toRadians(pose.getHeading())));
		Line rl = null;
		// System.out.println("target: " + l.x1 + " " + l.y1 + ", " + l.x2 + " "
		// + l.y2);

		Line[] lines = getLines();
		for (int i = 0; i < lines.length; i++) {

			Point p = intersectsAt(lines[i], l);

			if (p == null) {
				// Does not intersect
				// System.out.println(i + " checking against: " + lines[i].x1
				// + " " + lines[i].y1 + ", " + lines[i].x2 + " "
				// + lines[i].y2);
				//
				// System.out.println("does not intersect");
				continue;
			}

			Line tl = new Line(pose.getX(), pose.getY(), p.x, p.y);

			// System.out.println(i + " checking against: " + lines[i].x1 + " "
			// + lines[i].y1 + ", " + lines[i].x2 + " " + lines[i].y2);
			//
			// System.out.println("does intersect: " + tl.length());

			// If the range line intersects more than one map line
			// then take the shortest distance.
			if (rl == null || tl.length() < rl.length()) {
				rl = tl;
			}
		}
		return (rl == null ? -1 : rl.length());
	}

	/**
	 * 
	 * 
	 * Calculate the point of intersection of two lines. Copied from Line in
	 * leJOS for debugging purposes.
	 * 
	 * @param l1
	 *            the first line
	 * @param l2
	 *            the second line
	 * 
	 * @return the point of intersection or null if the lines do not intercept
	 *         or are coincident
	 */
	public Point intersectsAt(Line l1, Line l2) {
		float x, y, a1, a2, b1, b2;

		if (l1.y2 == l1.y1 && l2.y2 == l2.y1) {
			return null; // horizontal parallel
		}

		if (l1.x2 == l1.x1 && l2.x2 == l2.x1) {
			return null; // vertical parallel
		}

		// Find the point of intersection of the lines extended to infinity
		if (l1.x1 == l1.x2 && l2.y1 == l2.y2) { // perpendicular
			x = l1.x1;
			y = l2.y1;
		} else if (l1.y1 == l1.y2 && l2.x1 == l2.x2) { // perpendicular
			x = l2.x1;
			y = l1.y1;
		} else if (l1.y2 == l1.y1 || l2.y2 == l2.y1) { // one line is horizontal
			a1 = (l1.y2 - l1.y1) / (l1.x2 - l1.x1);
			b1 = l1.y1 - a1 * l1.x1;
			a2 = (l2.y2 - l2.y1) / (l2.x2 - l2.x1);
			b2 = l2.y1 - a2 * l2.x1;

			if (a1 == a2) {
				return null; // parallel
			}
			x = (b2 - b1) / (a1 - a2);
			y = a1 * x + b1;
		} else {
			a1 = (l1.x2 - l1.x1) / (l1.y2 - l1.y1);
			b1 = l1.x1 - a1 * l1.y1;
			a2 = (l2.x2 - l2.x1) / (l2.y2 - l2.y1);
			b2 = l2.x1 - a2 * l2.y1;

			if (a1 == a2) {
				return null; // parallel
			}
			y = (b2 - b1) / (a1 - a2);
			x = a1 * y + b1;
		}

		// System.out.println("here: " + x + "," + y);

		// Check that the point of intersection is within both line segments
		if (!between(x, l1.x1, l1.x2)) {
			return null;
		}
		if (!between(y, l1.y1, l1.y2)) {
			return null;
		}
		if (!between(x, l2.x1, l2.x2)) {
			return null;
		}
		if (!between(y, l2.y1, l2.y2)) {
			return null;
		}
		return new Point(x, y);
	}

	/**
	 * 
	 * Copied from Line in leJOS.
	 * 
	 * Return true iff x is between x1 and x2
	 */
	private boolean between(float x, float x1, float x2) {
		if (x1 <= x2 && x >= x1 && x <= x2) {
			return true;
		}
		if (x2 < x1 && x >= x2 && x <= x1) {
			return true;
		}
		return false;
	}

	/**
	 * Check if a point is within the accessible free space within the map, i.e.
	 * it's NOT inside an obstacle or outside the bounds of the map.
	 * 
	 * @param p
	 *            the Point
	 * @return true iff the point is with the mapped area
	 */
	public boolean inside(Point p) {
		return super.inside(p);
	}

	/**
	 * Returns true if any given line intersects with any line in the map. This
	 * could be made faster for large maps.
	 * 
	 * @param _lines
	 * @return
	 */
	public boolean intersectsWith(Line[] _lines) {
		for (Line mapLine : getLines()) {
			for (Line inputLine : _lines) {
				if (mapLine.intersectsLine(inputLine)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Obtain range readings from the map at the predefined angles relative to
	 * the robot
	 */
	public RangeReadings takeReadings(Pose _robotPose,
			RangeScannerDescription _ranger) {

		float[] readingAngles = _ranger.getReadingAngles();

		RangeReadings readings = new RangeReadings(readingAngles.length);

		// the pose to use for taking range readings
		Pose readingPose = GeometryUtils.transform(_robotPose,
				_ranger.getScannerPose());

		float readingPoseHeading = readingPose.getHeading();

		for (int i = 0; i < readingAngles.length; i++) {

			// rotate the reading pose to the angle of the sensor
			readingPose.setHeading(readingPoseHeading + readingAngles[i]);

			// and take a reading from there
			float mapRange = range(readingPose);

//			System.out.println(mapRange);
//			System.out.println(_ranger.getMaxRange());
//			System.out.println(_ranger.getMinRange());

			// bound reading to configured parameters
			if (mapRange > _ranger.getMaxRange()) {
				mapRange = 2.55f;
			} else if (mapRange < _ranger.getMinRange()) {
				mapRange = 0;
			}

			readings.setRange(i, readingAngles[i], mapRange);

//			System.out.println(mapRange);

		}

		return readings;

	}
}
