package game.levels;

import game.util.SpawnManager;

public class Wave {

	private long waitTime;
	
	private SpawnManager sm;
	
	public Wave(long waitTime, SpawnManager sm) {
		this.waitTime = waitTime;
		this.sm = sm;
	}
	
	public void begin() { sm.spawn(); }
	
	public long getWaitTime() { return waitTime; }
	
}
