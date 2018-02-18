package game.levels;

import java.util.ArrayList;

import game.display.SpaceGame;

public class Level {

	ArrayList<Wave> waves = new ArrayList<Wave>();
	
	private boolean isBossBattle = false;
	
	private Wave current;
	
	public void update(long currentTime) {
		if (current == null || current.isCleared()) {
			if (!waves.isEmpty()) {
				current = waves.get(0);
				current.begin();
				waves.remove(0);
			} else {
				if (!isBossBattle) {
					SpaceGame.flashWarning();
					spawnBoss();
					isBossBattle = true;
				}
			}
			
		}
	}
	
	public void spawnBoss() { return; }
}
