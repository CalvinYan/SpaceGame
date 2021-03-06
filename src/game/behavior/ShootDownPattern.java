package game.behavior;

import game.display.sprites.bullets.Bullet;
import game.display.sprites.ships.Ship;
import javafx.scene.image.Image;
import game.display.SpaceGame;
import game.display.sprites.Sprite;

public class ShootDownPattern extends Pattern {
	
	public ShootDownPattern(Sprite parent, double startTime, double timeout) {
		super(parent, startTime, timeout);
	}

	@Override
	public void update(long currentTime) {
		int bulletsPerSecond = 3;
		if (currentTime - lastShot > 1000000000/bulletsPerSecond) {
			lastShot = currentTime;
			Image image = new Image("file:Assets/standardenemybullet.png");
			Bullet bullet = new Bullet(parent.getX() + (parent.getWidth() - image.getWidth())/2, 
										parent.getY() + image.getHeight(), 1, "file:Assets/standardenemybullet.png", 
						(Sprite one, Sprite two) -> {
							if (two.damageable() && two.isPlayer()) {
								((Ship)two).changeHealth(-1);
								SpaceGame.remove(one);
							}
						}, parent);
			bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 15, 0, 270));
		}
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
