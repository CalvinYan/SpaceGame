package game.display.sprites.ships;

import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;

public abstract class Ship extends Sprite {

	public Ship(int x, int y) {
		super(x, y);
	}
	
	int health;
	
	public int getHealth() { return health; }
	
	public boolean damageable() { return true; }
	
}
