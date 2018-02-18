package game.behavior;

import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;
import game.display.sprites.ships.Ship;
import javafx.scene.image.Image;

public class ShootRandomHomingPattern extends Pattern {
	
	private double min, max, waitTime, tolerance;

	public ShootRandomHomingPattern(Sprite parent, double startTime, int timeout, double min, double max, double tolerance) {
		super(parent, startTime, timeout);
		this.min = min * 1000000000;
		this.max = max * 1000000000;
		this.tolerance = tolerance;
		setWaitTime();
	}
	
	public void update(long currentTime) {
		Image image = new Image("file:Assets/standardenemybullet.png");
		if ((currentTime - lastShot) > waitTime) {
			waitTime = (int)(Math.random() * 200000000) + 1000000000;
			lastShot = currentTime;
			Bullet bullet = new Bullet(parent.getX() + (parent.getWidth() - image.getWidth())/2, 
										parent.getY() + image.getHeight(), 1, "file:Assets/standardenemybullet.png", 
										(Sprite one, Sprite two) -> {
											if (two.damageable() && two.isPlayer()) {
												((Ship)two).changeHealth(-1);
												SpaceGame.remove(one);
											}
										}, parent);
			double playerX = SpaceGame.getPlayer().getX() - parent.getX(), playerY = parent.getY() - SpaceGame.getPlayer().getY(), angle = Math.atan2(playerY, playerX);
			angle = Math.toDegrees(angle) + Math.random() * tolerance - tolerance/2;
			bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 8, 0, angle));
		}
	}
	
	private void setWaitTime() { waitTime = (int)(Math.random() * (max - min)) + min; }
	
}
