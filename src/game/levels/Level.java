package game.levels;

import java.util.ArrayList;

public class Level {

	ArrayList<Wave> waves = new ArrayList<Wave>();
	
	long startTime, lastSpawn;
	
	public void start() {
		startTime = System.nanoTime();
	}
	
	public void update(long currentTime) {
		while (!waves.isEmpty() && isReady(waves.get(0), currentTime)) {
			waves.get(0).begin();
			waves.remove(0);
			lastSpawn = currentTime;
		}
	}
	
	public boolean isReady(Wave wave, long currentTime) {
		return currentTime - lastSpawn > wave.getWaitTime() * 1000000000;
	}
	
}
