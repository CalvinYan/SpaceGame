package game.ships;

import java.util.ArrayList;

import game.bullets.Bullet;
import javafx.scene.image.Image;

public abstract class Ship {

	int x, y;
	
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	Image image;
	
	public Ship(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Manages timed actions of the ship
	public abstract void update(long currentTime);
	
	// Getter and setter methods
	
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public double getWidth() { return image.getWidth(); }
	public double getHeight() { return image.getHeight(); }
	
	// Determine where the ship is going based on player input
	public abstract int getXVelocity();
	public abstract int getYVelocity();
	
	public ArrayList<Bullet> getBullets() { return bullets; }
	
	public Image getImage() { return image; }
	
}
