package rp.robotics;

/*
 * Describes the change in state of a touch event. 
 *
 */
public class TouchSensorEvent {

	// The value before the change
	private final float m_oldValue;
	// The value after the change
	private final float m_newValue;

	public TouchSensorEvent(float _oldValue, float _newValue) {
		m_oldValue = _oldValue;
		m_newValue = _newValue;
	}

	public float getNewValue() {
		return m_newValue;
	}

	public float getOldValue() {
		return m_oldValue;
	}

	public float getValueChange() {
		return getNewValue() - getOldValue();
	}

}
