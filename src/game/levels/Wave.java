package game.levels;

import java.util.ArrayList;

import game.display.SpaceGame;
import game.display.sprites.ships.Ship;
import game.util.SpawnManager;

public class Wave {
	
	private SpawnManager sm;
	
	private ArrayList<Ship> ships;
	
	public Wave(SpawnManager sm) {
		this.sm = sm;
	}
	
	public void begin() { ships = sm.spawn(); }
	
	public boolean isCleared() { 
		for (Ship ship : ships) {
			if (SpaceGame.sprites.contains(ship)) return false;
		}
		return true;
	}
	
}
