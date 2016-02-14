package rp.robotics.navigation;

/**
 * Describes the relative movements available to a robot moving on a 4-connected
 * grid.
 * 
 * @author Nick Hawes
 *
 */
public interface RelativeGridMoveController {

	/**
	 * Rotate in the positive direction to face along the next available grid
	 * line.
	 */
	void rotatePositive();

	/**
	 * Rotate in the negative direction to face along the next available grid
	 * line.
	 */
	void rotateNegative();

	/***
	 * Move forward to the next available grid position.
	 */
	void moveForward();

}
