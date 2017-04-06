package game.display.sprites.ships;

import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;

public abstract class Ship extends Sprite {

	public Ship(int x, int y, int health) {
		super(x, y);
		this.health = health;
	}
	
	int health;
	
	public int getHealth() { return health; }
	
	public void changeHealth(int amount) { 
		health += amount;
		if (health <= 0) SpaceGame.remove(this);
	}
	
	public boolean damageable() { return true; }
	public abstract boolean isPlayer();
	
	public void onHit() {}
}
