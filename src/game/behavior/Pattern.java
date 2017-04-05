package game.behavior;

import game.display.sprites.ships.Ship;

public abstract class Pattern {

	Ship parent;
	
	long patternStartTime;
	
	public Pattern(Ship parent) {
		this.parent = parent;
	}
	
	public void start() {
		patternStartTime = System.nanoTime();
	}
	
	public abstract void update(long currentTime);
	
	public abstract boolean isFinished();
	
}
