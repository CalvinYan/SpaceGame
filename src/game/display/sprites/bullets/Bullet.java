package game.display.sprites.bullets;

import game.display.sprites.Sprite;
import game.display.sprites.ships.Ship;
import javafx.scene.image.Image;

public abstract class Bullet extends Sprite {
	
	Ship parent;
	
	int speed;
	double direction;
	
	public Bullet(int x, int y, Ship parent) {
		super(x, y);
		this.parent = parent;
	}

	public void update(long currentTime) {
		System.out.println(direction);
		x += speed * Math.cos(direction);
		y -= speed * Math.sin(direction);
	}
	
	public boolean damageable() { return false; }
	
}
