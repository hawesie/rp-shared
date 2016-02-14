package rp.robotics.navigation;

import java.awt.Point;
import java.awt.geom.Point2D;

public class GridPose {

	private Heading m_heading;
	private Point2D m_position;

	public GridPose(Point _position, Heading _heading) {
		m_position = _position;
		m_heading = _heading;
	}

	public GridPose() {
		this(0, 0, Heading.PLUS_X);
	}

	public GridPose(int _x, int _y, Heading _heading) {
		this(new Point(_x, _y), _heading);
	}

	public Heading getHeading() {
		return m_heading;
	}

	public Point2D getPosition() {
		return m_position;
	}

	public int getX() {
		return (int) getPosition().getX();
	}

	public int getY() {
		return (int) getPosition().getY();
	}

}
