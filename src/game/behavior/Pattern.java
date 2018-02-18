package game.behavior;

import game.display.sprites.Sprite;
import game.display.sprites.ships.Ship;
import game.util.PatternUpdater;

public class Pattern {

	Sprite parent;
	
	long patternStartTime;
	double startTime, lastShot = 0, timeout;
	
	PatternUpdater pu;
	
	boolean finished = false;
	
	public Pattern(Sprite parent, double startTime, double timeout) {
		this.parent = parent;
		this.startTime = startTime;
		this.timeout = timeout;
		pu = null;
	}
	
	public Pattern(Sprite parent, double startTime, double timeout, PatternUpdater pu) {
		this.parent = parent;
		this.startTime = startTime;
		this.timeout = timeout;
		this.pu = pu;
	}
	
	public void start() {
		patternStartTime = System.nanoTime();
		lastShot = patternStartTime;
	}
	
	public long getPatternStartTime() { return patternStartTime; }
	public double getStartTime() { return startTime; }
	public double getLastShot() { return lastShot; }
	public void setLastShot(double val) { lastShot = val; }
	
	public double getTimeout() { return timeout; }
	
	public void update(long currentTime) { pu.update(parent, this, currentTime); }
	
	public boolean isFinished() { return false; }
	
	public boolean isTimeOut(long currentTime) { return timeout != 0 && currentTime - patternStartTime > timeout * 1000000000; }
	
}
