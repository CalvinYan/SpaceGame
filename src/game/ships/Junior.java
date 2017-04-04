package game.ships;

import game.bullets.StandardPlayerBullet;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
	
	private long shootStartTime;
	
	public Junior(int x, int y) {
		super(x, y);
		image = new Image("file:Assets/Drawing.png");
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
					if (!enterPressed) fire();
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
		int bulletsPerSecond = 10;
		if (enterPressed && (currentTime - shootStartTime) > 1000000000/bulletsPerSecond) {
			fire();
		}
	}
	
	// Determine where the ship is going based on which keys are pressed
	
	public int getXVelocity() {
		int xVel = 0;
		if (aPressed) xVel -= 10;
		if (dPressed) xVel += 10;
		return xVel;
	}
	public int getYVelocity() {
		int yVel = 0;
		if (wPressed) yVel -= 10;
		if (sPressed) yVel += 10;
		return yVel;
	}
	
	private void fire() {
		shootStartTime = System.nanoTime();
		bullets.add(new StandardPlayerBullet(x, y, this));
	}
	
}
