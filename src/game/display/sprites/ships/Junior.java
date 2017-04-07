package game.display.sprites.ships;

import game.behavior.GlideAcceleratePattern;
import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * Default ship. Uses WASD controls and can move diagonally. Automatic firing.
 * Nothing much to see here.
 * @author Calvin
 *
 */

public class Junior extends PlayerShip {
	
	// Boolean flags for key presses allows smooth movement
	private boolean wPressed = false, aPressed = false, sPressed = false, dPressed = false;
	private boolean enterPressed = false;
	
	private long shootStartTime = 0;
	
	public Junior(int x, int y, int health) {
		super(x, y, health, "file:Assets/Drawing.png");
	}
	
	// Map WASD keys to movement
	public void mapControls(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				switch (event.getCode().toString()) {
				case "W":
					wPressed = true;
					break;
				case "A":
					aPressed = true;
					break;
				case "S":
					sPressed = true;
					break;
				case "D":
					dPressed = true;
					break;
				case "ENTER":
					// Fire first shot if haven't already
					enterPressed = true;
					
					break;
				default:
					break;
				}
			}
			
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				switch (event.getCode().toString()) {
				case "W":
					wPressed = false;
					break;
				case "A":
					aPressed = false;
					break;
				case "S":
					sPressed = false;
					break;
				case "D":
					dPressed = false;
					break;
				case "ENTER":
					enterPressed = false;
					break;
				default:
					break;
				}
			}
			
		});
		
	}
	
	// Manages timed actions of the ship
	public void update(long currentTime) {
		int xVel = 0;
		if (aPressed) xVel -= 10;
		if (dPressed) xVel += 10;
		if (x + xVel < 0 || x + xVel + getWidth() > 800) xVel = 0;
		
		int yVel = 0;
		if (wPressed) yVel -= 10;
		if (sPressed) yVel += 10;
		if (y + yVel < 0 || y + yVel + getHeight() > 1000) yVel = 0;
		
		x += xVel;
		y += yVel;
		
		int bulletsPerSecond = 10;
		if (enterPressed && (currentTime - shootStartTime) > 1000000000/bulletsPerSecond) {
			fire();
		}
	}
	
	private void fire() {
		shootStartTime = System.nanoTime();
		Image image = new Image("file:Assets/standardplayerbullet.png");
		Bullet bullet = new Bullet((int)(x + (getWidth() - image.getWidth())/2), (int)(y - image.getHeight()), 1, "file:Assets/standardplayerbullet.png",
								(Sprite one, Sprite two) -> {
									if (!two.equals(this) && two.damageable()) {
										((Ship) two).changeHealth(-1);
										SpaceGame.remove(one);
									}
								}, this);
		bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 25, 0, 90));
	}

	@Override
	public void onHit(Sprite other) {
		// TODO Auto-generated method stub
		
	}
	
}
