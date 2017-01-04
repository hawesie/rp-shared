package rp.util;

import lejos.util.Delay;

public class Rate {

	private final long m_sleepDurationNS;
	private long m_lastCall = System.nanoTime();

	public Rate(double _hz) {
		m_sleepDurationNS = Math.round(1000000000d / _hz);
	}

	public synchronized void sleep() {
//		System.out.println("In sleep");
		long current = System.nanoTime();
		long elapsed = current - m_lastCall;
		long toSleep = m_sleepDurationNS - elapsed;
		if (toSleep > 0) {
			Delay.nsDelay(toSleep);
		}
		m_lastCall = System.nanoTime();
//		System.out.println("Out of sleep");
	}
	
	@Override
	public String toString() {
		return "Rate: " + (1000000000 / m_sleepDurationNS) + "hz";
	}
}
