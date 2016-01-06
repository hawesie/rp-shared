package rp.geom;

import lejos.geom.Line;
import lejos.robotics.navigation.Pose;

public class GeometryUtils {

	public static Line transform(Pose _transformation, Line _relativeLine) {
		// translate
		float x1 = _relativeLine.x1 + _transformation.getX();
		float y1 = _relativeLine.y1 + _transformation.getY();
		float x2 = _relativeLine.x2 + _transformation.getX();
		float y2 = _relativeLine.y2 + _transformation.getY();

		// rotate
		double angle = Math.toRadians(_transformation.getHeading());

		float rx1 = (float) (Math.cos(angle) * (x1 - _transformation.getX())
				- Math.sin(angle) * (y1 - _transformation.getY()) + _transformation
				.getX());
		float ry1 = (float) (Math.sin(angle) * (x1 - _transformation.getX())
				+ Math.cos(angle) * (y1 - _transformation.getY()) + _transformation
				.getY());

		float rx2 = (float) (Math.cos(angle) * (x2 - _transformation.getX())
				- Math.sin(angle) * (y2 - _transformation.getY()) + _transformation
				.getX());
		float ry2 = (float) (Math.sin(angle) * (x2 - _transformation.getX())
				+ Math.cos(angle) * (y2 - _transformation.getY()) + _transformation
				.getY());

		return new Line(rx1, ry1, rx2, ry2);
	}

	public static void transform(Pose _transformation, Line[] _relativeLines,
			Line[] _transformedLines) {

		for (int i = 0; i < _relativeLines.length; i++) {
			_transformedLines[i] = transform(_transformation, _relativeLines[i]);
		}
	}

}
