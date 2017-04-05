package game.display.sprites.ships;

import game.display.sprites.Sprite;

public abstract class Ship extends Sprite {

	public Ship(int x, int y) {
		super(x, y);
	}
	
	int health;
	
	public int getHealth() { return health; }
	
}
