package game.bullets;

import game.display.SpaceGame;
import game.ships.Ship;
import javafx.scene.image.Image;

public abstract class Bullet {
	
	Ship parent;
	
	int speed, x, y;
	double direction;
	
	Image image;
	
	public Bullet(Ship parent) {
		this.parent = parent;
		SpaceGame.bullets.add(this);
	}

	public void update() {
		
		x += speed * Math.cos(Math.toRadians(direction));
		y -= speed * Math.sin(Math.toRadians(direction));
		
		if (outOfBounds()) parent.getBullets().remove(this);
	}
	
	public abstract void onHit();
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	public Image getImage() { return image; }
	
	private boolean outOfBounds() {
		if (this.x > 800) return true;
		if (this.x + image.getWidth() < 0) return true;
		if (this.y > 1000) return true;
		if (this.y + image.getHeight() < 0) return true;
		return false;
	}
	
}
