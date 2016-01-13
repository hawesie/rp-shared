package rp.robotics;

/*
 * Describes the change in state of a touch event. 
 *
 */
public class TouchSensorEvent {

	// The value before the change
	private final int m_oldValue;
	// The value after the change
	private final int m_newValue;

	public TouchSensorEvent(int _oldValue, int _newValue) {
		super();
		m_oldValue = _oldValue;
		m_newValue = _newValue;
	}

	public int getNewValue() {
		return m_newValue;
	}

	public int getOldValue() {
		return m_oldValue;
	}

	public int getValueChange() {
		return getNewValue() - getOldValue();
	}

}
