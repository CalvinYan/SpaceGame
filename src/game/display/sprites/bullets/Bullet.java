package game.display.sprites.bullets;

import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.ships.Ship;
import javafx.scene.image.Image;

public abstract class Bullet extends Sprite {
	
	Ship parent;
	
	int speed, damage;
	double direction;
	
	public Bullet(int x, int y, int damage, String imageURL, Ship parent) {
		super(x, y, imageURL);
		this.damage = damage;
		this.parent = parent;
	}

	public void update(long currentTime) {
		x += speed * Math.cos(direction);
		y -= speed * Math.sin(direction);
	}
	
	public boolean damageable() { return false; }
	public boolean isPlayer() { return parent.isPlayer(); }
	
	public abstract void onHit(Sprite other);
	
}
