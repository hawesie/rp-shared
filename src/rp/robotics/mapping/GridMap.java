package rp.robotics.mapping;

import java.awt.geom.Point2D;

import lejos.geom.Line;
import lejos.geom.Point;
import lejos.robotics.navigation.Pose;
import rp.robotics.navigation.GridPose;
import rp.robotics.navigation.Heading;

/**
 * Creates a regular grid of points on top of a regular leJOS LineMap. The grid
 * is defined by the grid width and height parameters in the constructor. and
 * the grid is placed at the given offset from the origin.
 * 
 * @author Nick Hawes
 * 
 */
public class GridMap extends LineMap implements IGridMap {

	private float m_cellSize;
	private float m_yStart;
	private float m_xStart;
	private int m_gridHeight;
	private int m_gridWidth;

	/**
	 * 
	 * Overall grid width is (_gridWidth - 1) * _cellSize.
	 * 
	 * @param _gridWidth
	 *            The number of points the grid is wide.
	 * @param _gridHeight
	 *            The number of points the grid is high.
	 * @param _xStart
	 *            The x offset of the first point
	 * @param _yStart
	 *            The y offset of the first point
	 * @param _cellSize
	 *            The spacing between grid points
	 * @param _lines
	 *            The lines that will make up the underlying LineMap
	 * 
	 */
	public GridMap(int _gridWidth, int _gridHeight, float _xStart,
			float _yStart, float _cellSize, LineMap _lineMap) {
		super(_lineMap.getLines(), _lineMap.getBoundingRect());

		// Sanity check, test whether the given grid fits within the bounds of
		// the map
		float gridMetricWidth = (_gridWidth - 1) * _cellSize;
		assert gridMetricWidth < _lineMap.getBoundingRect().getWidth() : "Given grid is wider than bounding rectangle: "
				+ gridMetricWidth + " " + _lineMap.getBoundingRect().getWidth();

		float gridMetricHeight = (_gridHeight - 1) * _cellSize;
		assert gridMetricHeight < _lineMap.getBoundingRect().getHeight() : "Given grid is taller than bounding rectangle: "
				+ gridMetricHeight
				+ " "
				+ _lineMap.getBoundingRect().getHeight();

		m_gridWidth = _gridWidth;
		m_gridHeight = _gridHeight;
		m_xStart = _xStart;
		m_yStart = _yStart;
		m_cellSize = _cellSize;

	}

	/**
	 * Tests whether the input point is a point on the grid.
	 * 
	 * @param _x
	 * @param _y
	 * @return
	 */
	@Override
	public boolean isValidGridPosition(int _x, int _y) {
		return _x >= 0 && _x < m_gridWidth && _y >= 0 && _y < m_gridHeight;
	}

	/**
	 * Tests whether the input point is inside the mapped area.
	 * 
	 * @param _x
	 * @param _y
	 * @return
	 */
	@Override
	public boolean isObstructed(int _x, int _y) {
		return !inside(getCoordinatesOfGridPosition(_x, _y));
	}

	/**
	 * Get the coordinate of the given grid point.
	 * 
	 * @param _x
	 * @param _y
	 * @return
	 */
	@Override
	public final Point getCoordinatesOfGridPosition(int _x, int _y) {
		assert (isValidGridPosition(_x, _y));
		return new Point(getXCoordinateOfGridPoint(_x),
				getYCoordinateOfGridPoint(_y));
	}

	/**
	 * @param _x
	 * @return
	 */
	private float getYCoordinateOfGridPoint(int _y) {
		return m_yStart + (_y * m_cellSize);
	}

	/**
	 * @param _x
	 * @return
	 */
	private float getXCoordinateOfGridPoint(int _x) {
		return m_xStart + (_x * m_cellSize);
	}

	public boolean isValidTransition(Point2D _start, Point2D _end) {
		return isValidTransition((int) _start.getX(), (int) _start.getY(),
				(int) _end.getX(), (int) _end.getY());
	}

	/**
	 * Can the robot move from grid point x1,y1 to x2,y2 without passing through
	 * an obstacle.
	 * 
	 * @param _x1
	 * @param _y1
	 * @param _x2
	 * @param _y2
	 * @return
	 */
	@Override
	public boolean isValidTransition(int _x1, int _y1, int _x2, int _y2) {

		if (!isValidGridPosition(_x1, _y1) || !isValidGridPosition(_x2, _y2)) {
			return false;
		}

		if (isObstructed(_x1, _y1) || isObstructed(_x2, _y2)) {
			return false;
		}

		Line transition = createLineBetweenGridPoints(_x1, _y1, _x2, _y2);
		Line[] lines = getLines();
		for (int i = 0; i < lines.length; i++) {

			// System.out.println(i+ " checking against: " + lines[i].x1 + " "
			// + lines[i].y1 + ", " + lines[i].x2 + " " + lines[i].y2);

			Point p = intersectsAt(lines[i], transition);

			if (p != null) {
				return false;
			}

		}
		return true;
	}

	/**
	 * @param _x1
	 * @param _y1
	 * @param _x2
	 * @param _y2
	 * @return
	 */
	private Line createLineBetweenGridPoints(int _x1, int _y1, int _x2, int _y2) {
		Line transition = new Line(getXCoordinateOfGridPoint(_x1),
				getYCoordinateOfGridPoint(_y1), getXCoordinateOfGridPoint(_x2),
				getYCoordinateOfGridPoint(_y2));
		return transition;
	}

	/**
	 * Gets the distance to the nearest obstacle at a given heading from the
	 * grid point.
	 * 
	 * @param _x
	 * @param _y
	 * @param _heading
	 * @return
	 */
	@Override
	public float rangeToObstacleFromGridPosition(int _x, int _y, float _heading) {
		return range(new Pose(getXCoordinateOfGridPoint(_x),
				getYCoordinateOfGridPoint(_y), _heading));
	}

	@Override
	public int getXSize() {
		return m_gridWidth;
	}

	@Override
	public int getYSize() {
		return m_gridHeight;
	}

	public float getCellSize() {
		return m_cellSize;
	}

	@Override
	public Pose toPose(GridPose _pose) {
		return new Pose(getXCoordinateOfGridPoint(_pose.getX()),
				getYCoordinateOfGridPoint(_pose.getY()),
				Heading.toDegrees(_pose.getHeading()));
	}
}
