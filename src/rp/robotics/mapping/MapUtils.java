package rp.robotics.mapping;

import java.util.ArrayList;
import lejos.geom.Line;
import lejos.geom.Rectangle;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.Pose;

/**
 * 
 * @author Nick Hawes
 */
public class MapUtils {

	private static final float BOARD_WIDTH = 1.7f;

	/**
	 * Create an empty, rectangular map with the given width and height
	 * 
	 * @param _width
	 * @param _height
	 * @return
	 */
	public static LineMap createRectangularMap(float _width, float _height) {

		// these are the walls for the world outline
		Line[] lines = new Line[] { new Line(0f, 0f, _width, 0f),
				new Line(_width, 0f, _width, _height),
				new Line(_width, _height, 0f, _height),
				new Line(0f, _height, 0f, 0f) };

		LineMap map = new LineMap(lines, new Rectangle(0, 0, _width, _height));

		return map;
	}

	/**
	 * Turns a straight line into a box with 4 walls around the line at the
	 * given width
	 * 
	 * @return
	 */
	public static ArrayList<Line> lineToBox(float _x1, float _y1, float _x2,
			float _y2) {

		ArrayList<Line> box = new ArrayList<Line>(4);

		float expand = Math.round(BOARD_WIDTH / 2f);

		// extend in x direction
		if (_x1 == _x2) {
			box.add(new Line(_x1 - expand, _y1, _x1 + expand, _y1));
			box.add(new Line(_x1 + expand, _y1, _x2 + expand, _y2));
			box.add(new Line(_x2 + expand, _y2, _x2 - expand, _y2));
			box.add(new Line(_x2 - expand, _y2, _x1 - expand, _y1));
		} else if (_y1 == _y2) {
			box.add(new Line(_x1, _y1 + expand, _x2, _y2 + expand));
			box.add(new Line(_x2, _y2 - expand, _x2, _y2 + expand));
			box.add(new Line(_x1, _y1 - expand, _x2, _y2 - expand));
			box.add(new Line(_x1, _y1 - expand, _x1, _y1 + expand));

		} else {
			throw new RuntimeException(
					"can only use this with axis-aligned lines");
		}

		return box;
	}

	/**
	 * Creates a grid map to match the training map as of 6/3/2013.
	 * 
	 * @return
	 */
	public static LineMap createTrainingMap() {
		float height = 238;
		float width = 366;

		ArrayList<Line> lines = new ArrayList<Line>();

		// these are the walls for the world outline
		lines.add(new Line(0f, 0f, width, 0f));
		lines.add(new Line(width, 0f, width, height));
		lines.add(new Line(width, height, 0f, height));
		lines.add(new Line(0f, height, 0f, 0f));

		lines.add(new Line(75.0f, 133f, 100f, 133f));
		lines.add(new Line(75.0f, 193.0f, 100f, 193.0f));
		lines.add(new Line(100f, 133f, 100f, 193.0f));
		lines.add(new Line(75.0f, 133f, 75.0f, 193.0f));

		lines.addAll(lineToBox(42f, 67f, 287f, 67f));
		lines.add(new Line(287f, 0, 287f, 67f));
		lines.add(new Line(257f, 0, 257f, 67f));

		lines.addAll(lineToBox(135f, 129f, 255f, 129f));
		lines.addAll(lineToBox(135f, 129f, 135f, height));

		lines.addAll(lineToBox(194f, 191f, 254f, 191f));
		lines.addAll(lineToBox(194f, 191f, 194f, height));

		lines.add(new Line(width - 42f, 99f, width, 99f));
		lines.add(new Line(width - 42f, 159f, width, 159f));
		lines.add(new Line(width - 42f, 99f, width - 42f, 159f));
		Line[] lineArray = new Line[lines.size()];
		lines.toArray(lineArray);

		return new LineMap(lineArray, new Rectangle(0, 0, width, height));

	}

	public static GridMap createMarkingWarehouseMap() {

		float height = 2.44f;
		float width = 3.67f;

		float xInset = 0.17f, yInstet = 0.155f;
		int gridWitdth = 12, gridHeight = 8;
		float cellSize = 0.30f;

		ArrayList<Line> lines = new ArrayList<Line>();

		// these are the walls for the world outline
		lines.add(new Line(0f, 0f, width, 0f));
		lines.add(new Line(width, 0f, width, height));
		lines.add(new Line(width, height, 0f, height));
		lines.add(new Line(0f, height, 0f, 0f));

		lines.add(new Line(0.63f, 0.57f, 0.63f, 0.57f + 1.255f));
		lines.add(
				new Line(0.63f, 0.57f + 1.255f, 0.63f + 0.3f, 0.57f + 1.255f));
		lines.add(new Line(0.63f + 0.3f, 0.57f + 1.255f, 0.63f + 0.3f, 0.57f));
		lines.add(new Line(0.63f + 0.3f, 0.57f, 0.63f, 0.57f));

		lines.add(new Line(1.515f, 0.89f, 1.515f, 0.89f + 0.62f));
		lines.add(
				new Line(1.515f, 0.89f + 0.62f, 1.515f + 0.62f, 0.89f + 0.62f));
		lines.add(
				new Line(1.515f + 0.62f, 0.89f + 0.62f, 1.515f + 0.62f, 0.89f));
		lines.add(new Line(1.515f + 0.62f, 0.89f, 1.515f, 0.89f));

		lines.add(new Line(width - 0.3f - 0.63f, 0.57f, width - 0.3f - 0.63f,
				0.57f + 1.255f));
		lines.add(new Line(width - 0.3f - 0.63f, 0.57f + 1.255f, width - 0.63f,
				0.57f + 1.255f));
		lines.add(
				new Line(width - 0.63f, 0.57f + 1.255f, width - 0.63f, 0.57f));
		lines.add(new Line(width - 0.63f, 0.57f, width - 0.3f - 0.63f, 0.57f));

		Line[] lineArray = new Line[lines.size()];

		lines.toArray(lineArray);

		return new GridMap(gridWitdth, gridHeight, xInset, yInstet, cellSize,
				new LineMap(lineArray, new Rectangle(0, 0, width, height)));
	}

	public static GridMap createRealWarehouse() {

		float height = 2.44f;
		float width = 3.67f;

		float xInset = 0.17f, yInstet = 0.155f;
		int gridWitdth = 12, gridHeight = 8;
		float cellSize = 0.30f;

		// First ins 36 39 56 188

		ArrayList<Line> lines = new ArrayList<Line>();

		// these are the walls for the world outline
		lines.add(new Line(0f, 0f, width, 0f));
		lines.add(new Line(width, 0f, width, height));
		lines.add(new Line(width, height, 0f, height));
		lines.add(new Line(0f, height, 0f, 0f));

		lines.add(new Line(0.31f, 0.29f, 0.31f, height - 0.61f));
		lines.add(new Line(0.61f, 0.29f, 0.61f, height - 0.61f));
		lines.add(new Line(0.31f, 0.29f, 0.61f, 0.29f));
		lines.add(new Line(0.31f, height - 0.61f, 0.61f, height - 0.61f));

		lines.add(new Line(1.24f, 0.28f, 1.24f, height - 0.62f));
		lines.add(new Line(1.54f, 0.28f, 1.54f, height - 0.62f));
		lines.add(new Line(1.24f, 0.28f, 1.54f, 0.28f));
		lines.add(new Line(1.24f, height - 0.62f, 1.54f, height - 0.62f));

		lines.add(new Line(2.14f, 0.29f, 2.14f, height - 0.61f));
		lines.add(new Line(2.44f, 0.29f, 2.44f, height - 0.61f));
		lines.add(new Line(2.14f, 0.29f, 2.44f, 0.29f));
		lines.add(new Line(2.14f, height - 0.61f, 2.44f, height - 0.61f));

		lines.add(new Line(3.08f, 0.30f, 3.08f, height - 0.60f));
		lines.add(new Line(3.38f, 0.30f, 3.38f, height - 0.60f));
		lines.add(new Line(3.08f, 0.30f, 3.38f, 0.30f));
		lines.add(new Line(3.08f, height - 0.60f, 3.38f, height - 0.60f));

		Line[] lineArray = new Line[lines.size()];

		lines.toArray(lineArray);

		return new GridMap(gridWitdth, gridHeight, xInset, yInstet, cellSize,
				new LineMap(lineArray, new Rectangle(0, 0, width, height)));
	}

	public static GridMap createRealWarehouse2016() {

		float height = 2.48f;
		float width = 3.68f;

		float xInset = 0.14f, yInset = 0.15f;
		int gridWidth = 12, gridHeight = 8;
		float cellSize = 0.30f;

		ArrayList<Line> lines = new ArrayList<Line>();

		// these are the walls for the world outline
		lines.add(new Line(0f, 0f, width, 0f));
		lines.add(new Line(width, 0f, width, height));
		lines.add(new Line(width, height, 0f, height));
		lines.add(new Line(0f, height, 0f, 0f));

		lines.add(new Line(0.28f, 0.31f, 0.28f, 1.83f));
		lines.add(new Line(0.58f, 0.31f, 0.58f, 1.83f));
		lines.add(new Line(0.28f, 0.31f, 0.58f, 0.31f));
		lines.add(new Line(0.28f, 1.83f, 0.58f, 1.83f));

		lines.add(new Line(1.20f, 0.28f, 1.20f, 1.85f));
		lines.add(new Line(1.50f, 0.28f, 1.50f, 1.85f));
		lines.add(new Line(1.20f, 0.28f, 1.50f, 0.28f));
		lines.add(new Line(1.20f, 1.85f, 1.50f, 1.85f));

		lines.add(new Line(2.13f, 0.29f, 2.13f, 1.84f));
		lines.add(new Line(2.44f, 0.29f, 2.44f, 1.84f));
		lines.add(new Line(2.13f, 0.29f, 2.44f, 0.29f));
		lines.add(new Line(2.13f, 1.84f, 2.44f, 1.84f));

		lines.add(new Line(3.05f, 0.30f, 3.05f, 1.83f));
		lines.add(new Line(3.35f, 0.30f, 3.35f, 1.83f));
		lines.add(new Line(3.05f, 0.30f, 3.35f, 0.30f));
		lines.add(new Line(3.05f, 1.83f, 3.35f, 1.83f));

		Line[] lineArray = new Line[lines.size()];

		lines.toArray(lineArray);

		return new GridMap(gridWidth, gridHeight, xInset, yInset, cellSize,
				new LineMap(lineArray, new Rectangle(0, 0, width, height)));
	}
	
	public static GridMap createRealWarehouse2018() {

		float height = 2.48f;
		float width = 3.68f;

		float xInset = 0.14f, yInset = 0.15f;
		int gridWidth = 12, gridHeight = 8;
		float cellSize = 0.30f;

		ArrayList<Line> lines = new ArrayList<Line>();

		// these are the walls for the world outline
		lines.add(new Line(0f, 0f, width, 0f));
		lines.add(new Line(width, 0f, width, height));
		lines.add(new Line(width, height, 0f, height));
		lines.add(new Line(0f, height, 0f, 0f));
		
		float yMin = 0.3f;
		float yMax = 1.78f;
		
		float x1Min = 0.30f;
		float x1Max = 0.59f;
		
		float x2Min = 1.2f;
		float x2Max = 1.49f;
		
		float x3Min = 2.10f;
		float x3Max = 2.39f;
		
		float x4Min = 3.01f;
		float x4Max = 3.30f;

		lines.add(new Line(x1Min, yMin, x1Min, yMax));
		lines.add(new Line(x1Max, yMin, x1Max, yMax));
		lines.add(new Line(x1Min, yMin, x1Max, yMin));
		lines.add(new Line(x1Min, yMax, x1Max, yMax));

		lines.add(new Line(x2Min, yMin, x2Min, yMax));
		lines.add(new Line(x2Max, yMin, x2Max, yMax));
		lines.add(new Line(x2Min, yMin, x2Max, yMin));
		lines.add(new Line(x2Min, yMax, x2Max, yMax));

		lines.add(new Line(x3Min, yMin, x3Min, yMax));
		lines.add(new Line(x3Max, yMin, x3Max, yMax));
		lines.add(new Line(x3Min, yMin, x3Max, yMin));
		lines.add(new Line(x3Min, yMax, x3Max, yMax));

		lines.add(new Line(x4Min, yMin, x4Min, yMax));
		lines.add(new Line(x4Max, yMin, x4Max, yMax));
		lines.add(new Line(x4Min, yMin, x4Max, yMin));
		lines.add(new Line(x4Min, yMax, x4Max, yMax));

		Line[] lineArray = new Line[lines.size()];

		lines.toArray(lineArray);

		return new GridMap(gridWidth, gridHeight, xInset, yInset, cellSize,
				new LineMap(lineArray, new Rectangle(0, 0, width, height)));
	}

	public static LineMap create2014Map1() {

		float height = 241f;

		float width = 324f;

		ArrayList<Line> lines = new ArrayList<Line>();

		// these are the walls for the world outline

		lines.add(new Line(0f, 0f, width, 0f));

		lines.add(new Line(width, 0f, width, height));

		lines.add(new Line(width, height, 0f, height));

		lines.add(new Line(0f, height, 0f, 0f));

		// 1

		// down

		lines.addAll(lineToBox(27, 78, 27, 108));

		// right

		lines.addAll(lineToBox(27, 108, 60, 108));

		// up

		lines.addAll(lineToBox(60, 108, 60, 76));

		// right

		lines.addAll(lineToBox(60, 76, 92, 76));

		// up

		lines.addAll(lineToBox(92, 76, 92, 42));

		// left

		lines.addAll(lineToBox(92, 42, 62, 42));

		// 2

		// right

		lines.addAll(lineToBox(126, 45, 206, 45));

		// right

		lines.addAll(lineToBox(126, 78, 206, 78));

		// 3

		// left

		lines.addAll(lineToBox(240, 45, 270, 45));

		// down

		lines.addAll(lineToBox(240, 45, 240, 79));

		// right

		lines.addAll(lineToBox(240, 79, 272, 79));

		// down

		lines.addAll(lineToBox(272, 79, 272, 113));

		// right

		lines.addAll(lineToBox(272, 113, 306, 113));

		// up

		lines.addAll(lineToBox(306, 113, 306, 83));

		// 4 center

		// down

		lines.addAll(lineToBox(149, 108, 149, 140));

		// right

		lines.addAll(lineToBox(149, 140, 179, 140));

		// down

		lines.addAll(lineToBox(179, 140, 179, 108));

		// 5

		// up

		lines.addAll(lineToBox(28, 168, 28, 138));

		// right

		lines.addAll(lineToBox(28, 138, 62, 138));

		// down

		lines.addAll(lineToBox(62, 138, 62, 169));

		// right

		lines.addAll(lineToBox(62, 169, 94, 169));

		// down

		lines.addAll(lineToBox(94, 169, 94, 203));

		// left

		lines.addAll(lineToBox(94, 203, 63, 203));

		// 6

		// right

		lines.addAll(lineToBox(126, 169, 206, 169));

		// right

		lines.addAll(lineToBox(126, 198, 206, 198));

		// 7

		// left

		lines.addAll(lineToBox(267, 198, 237, 198));

		// up

		lines.addAll(lineToBox(237, 198, 237, 168));

		// right

		lines.addAll(lineToBox(237, 168, 269, 168));

		// up

		lines.addAll(lineToBox(269, 168, 269, 138));

		// right

		lines.addAll(lineToBox(269, 138, 303, 138));

		// down

		lines.addAll(lineToBox(303, 138, 303, 168));

		Line[] lineArray = new Line[lines.size()];

		lines.toArray(lineArray);

		return new LineMap(lineArray, new Rectangle(0, 0, width, height));
	}

	public static LineMap create2014Map2() {
		float height = 242;
		float width = 306;

		ArrayList<Line> lines = new ArrayList<Line>();

		lines.add(new Line(0f, 0f, width, 0f));
		lines.add(new Line(width, 0f, width, height));
		lines.add(new Line(width, height, 0f, height));
		lines.add(new Line(0f, height, 0f, 0f));

		// Left line
		lines.addAll(lineToBox(30, 71, 30, 171));

		// Top box
		lines.add(new Line(131, 0, 131, 37));
		lines.add(new Line(131, 37, 171, 37));
		lines.add(new Line(171, 37, 171, 0));

		// Middle
		lines.addAll(lineToBox(93, 108, 218, 108));
		lines.add(new Line(134, 108, 134, 168));
		lines.add(new Line(177, 108, 177, 168));
		lines.addAll(lineToBox(94, 168, 219, 168));

		// Bottom box
		lines.add(new Line(134, 242, 134, 206));
		lines.add(new Line(134, 206, 176, 206));
		lines.add(new Line(176, 206, 176, 242));

		// Right box
		lines.addAll(lineToBox(278, 71, 278, 171));
		// lines.addAll(lineToBox(268,71,268,171));

		Line[] lineArray = new Line[lines.size()];
		lines.toArray(lineArray);

		return new LineMap(lineArray, new Rectangle(0, 0, width, height));
	}

	public static LineMap create2015Map1() {

		float height = 2.44f;
		float width = 3.68f;

		final boolean createBoxLinesAsBoxes = false;

		ArrayList<Line> lines = new ArrayList<Line>();

		// Rectangular outer wall edges
		lines.addAll(createBox(0, 0, width, height, false));

		// Top left box
		lines.addAll(createBox(0, height - 0.30f, 0.32f, height - 0.60f,
				createBoxLinesAsBoxes));
		// Bottom left box
		lines.addAll(createBox(0, 0.65f, 0.30f, 0.31f, createBoxLinesAsBoxes));
		// Top right box
		lines.addAll(createBox(width - 0.32f, height - 0.30f, width,
				height - 0.60f, createBoxLinesAsBoxes));
		// Bottom right box
		lines.addAll(createBox(width - 0.32f, 0.61f, width, 0.31f,
				createBoxLinesAsBoxes));

		// Top-edge box
		lines.addAll(createBox(1.22f, height, 1.22f + 0.90f, height - 0.31f,
				createBoxLinesAsBoxes));
		// Bottom-edge box
		lines.addAll(createBox(1.21f, 0.31f, 1.21f + 0.90f, 0,
				createBoxLinesAsBoxes));

		// Mid-left box
		lines.addAll(createBox(1.19f, height - .90f, 1.19f + .32f,
				height - (.90f + .62f), createBoxLinesAsBoxes));
		// Mid-right box
		lines.addAll(createBox(1.19f + .32f + .62f, height - .90f,
				1.19f + .32f + .62f + .32f, height - (.90f + .62f),
				createBoxLinesAsBoxes));

		lines.add(new Line(1.19f + .32f, height - (.90f + .62f),
				1.19f + .32f + .62f, height - (.90f + .62f)));

		Line[] lineArray = new Line[lines.size()];

		lines.toArray(lineArray);

		return new LineMap(lineArray, new Rectangle(0, 0, width, height));
	}

	/**
	 * Create a box of lines.
	 * 
	 * @param left
	 *            The x-coordinate of the left side.
	 * @param top
	 *            The y-coordinate of the top side.
	 * @param right
	 *            The x-coordinate of the right side.
	 * @param bottom
	 *            The y-coordinate of the bottom side.
	 * @return An arraylist of lines that make up that box.
	 */
	public static ArrayList<Line> createBox(float left, float top, float right,
			float bottom, boolean asLineBoxes) {
		ArrayList<Line> boxLines = new ArrayList<Line>();

		if (asLineBoxes) {
			boxLines.addAll(lineToBox(left, top, left, bottom)); // Left
			boxLines.addAll(lineToBox(left, top, right, top)); // Top
			boxLines.addAll(lineToBox(right, top, right, bottom)); // Right
			boxLines.addAll(lineToBox(left, bottom, right, bottom)); // Bottom
		} else {
			boxLines.add(new Line(left, top, left, bottom)); // Left
			boxLines.add(new Line(left, top, right, top)); // Top
			boxLines.add(new Line(right, top, right, bottom)); // Right
			boxLines.add(new Line(left, bottom, right, bottom)); // Bottom
		}

		return boxLines;
	}

	/**
	 * To string representation for a pose.
	 * 
	 * @param _pose
	 * @return
	 */
	public static String toString(Pose _pose) {
		StringBuilder sb = new StringBuilder("Pose: ");
		sb.append(_pose.getX());
		sb.append(", ");
		sb.append(_pose.getY());
		sb.append(", ");
		sb.append(_pose.getHeading());
		return sb.toString();
	}

	/**
	 * Calculate the change in X coordinate to pose from move.
	 * 
	 * @param _previousPose
	 * @param _move
	 * @return
	 */
	public static float changeInX(Pose _previousPose, Move _move) {
		return (_move.getDistanceTraveled() * ((float) Math
				.cos(Math.toRadians(_previousPose.getHeading()))));
	}

	/**
	 * Calculate the change in Y coordinate to pose from move.
	 * 
	 * @param _previousPose
	 * @param _move
	 * @return
	 */
	public static float changeInY(Pose _previousPose, Move _move) {
		return (_move.getDistanceTraveled() * ((float) Math
				.sin(Math.toRadians(_previousPose.getHeading()))));
	}
}
