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

	public void rotateUpdate(int _amount) {
		m_heading = m_heading.rotate(_amount);
	}

	/**
	 * Move a grid position in the direction of the heading.
	 */
	public void moveUpdate() {
		int dx = 0, dy = 0;

		switch (m_heading) {
		case PLUS_X:
			dx = 1;
			break;
		case MINUS_X:
			dx = -1;
			break;
		case PLUS_Y:
			dy = 1;
			break;
		case MINUS_Y:
			dy = -1;
			break;
		}
		m_position.setLocation(getX() + dx, getY() + dy);
	}

	@Override
	public GridPose clone() {
		return new GridPose(getX(), getY(), getHeading());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("GridPose: ");
		sb.append(m_position);
		sb.append(' ');
		sb.append(m_heading);

		return sb.toString();
	}
}
