package game.behavior;

import game.display.sprites.bullets.StandardEnemyBullet;
import game.display.sprites.ships.Ship;

public class ShootDownPattern extends Pattern {

	public ShootDownPattern(Ship parent) {
		super(parent);
	}

	@Override
	public void update(long currentTime) {
		int bulletsPerSecond = 3;
		if (currentTime - patternStartTime > 1000000000/bulletsPerSecond) {
			patternStartTime = currentTime;
			new StandardEnemyBullet(parent.getX(), parent.getY(), parent);
		}
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
