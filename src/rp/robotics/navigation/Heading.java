package rp.robotics.navigation;

/**
 * Enumeration for discrete headings that the robot might want to represent.
 * 
 * @author Nick Hawes
 * 
 */
public enum Heading {

	// Heading along the positive x axis
	PLUS_X,
	// Heading along the positive y axis
	PLUS_Y,
	// Heading along the negative x axis
	MINUS_X,
	// Heading along the negative y axis
	MINUS_Y;

	/**
	 * Gets the degree orientation for a given enum value.
	 * 
	 * @param _heading
	 * @return
	 */
	public static int toDegrees(Heading _heading) {
		int heading = 0;

		if (_heading == Heading.PLUS_X) {
			heading = 0;
		} else if (_heading == Heading.PLUS_Y) {
			heading = 90;
		} else if (_heading == Heading.MINUS_X) {
			heading = 180;
		} else if (_heading == Heading.MINUS_Y) {
			heading = -90;
		} else {
			assert false : "Unknown value for enumeration";
		}
		return heading;
	}

	public Heading rotate(int _amount) {
		return fromDegrees(toDegrees(this) + _amount);
	}

	public Heading fromDegrees(int _heading) {

		if (_heading > 180) {
			_heading -= 360;
		} else if (_heading < -180) {
			_heading += 360;
		}

		// System.out.println(_heading);

		Heading heading = null;
		if (_heading == 0) {
			heading = Heading.PLUS_X;
		} else if (_heading == 90) {
			heading = Heading.PLUS_Y;
		} else if (_heading == 180 || _heading == -180) {
			heading = Heading.MINUS_X;
		} else if (_heading == -90) {
			heading = Heading.MINUS_Y;
		} else {
			assert false : "Unknown value for enumeration";
		}
		return heading;
	}

	@Override
	public String toString() {
		switch (this) {
		case PLUS_X:
			return "PLUS_X";
		case MINUS_X:
			return "MINUS_X";
		case PLUS_Y:
			return "PLUS_Y";
		case MINUS_Y:
			return "MINUS_Y";
		}
		return "UNKNOWN";
	}

}