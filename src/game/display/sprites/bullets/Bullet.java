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
		x += speed * Math.cos(Math.toRadians(direction));
		y -= speed * Math.sin(Math.toRadians(direction));
		System.out.println(x + " " + y);
	}
	
}
