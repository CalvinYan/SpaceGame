package game.behavior;

import game.display.sprites.Sprite;
import game.display.sprites.ships.Ship;

public abstract class Pattern {

	Sprite parent;
	
	long patternStartTime, timeout;
	double startTime;
	
	boolean finished = false;
	
	public Pattern(Sprite parent, double startTime, int timeout) {
		this.parent = parent;
		this.startTime = startTime;
		this.timeout = timeout;
	}
	
	public void start() {
		patternStartTime = System.nanoTime();
	}
	
	public long getPatternStartTime() { return patternStartTime; }
	public double getStartTime() { return startTime; }
	
	public long getTimeout() { return timeout; }
	
	public abstract void update(long currentTime);
	
	public abstract boolean isFinished();
	
	public boolean isTimeOut(long currentTime) { return timeout != 0 && currentTime - patternStartTime > timeout * 1000000000; }
	
}
