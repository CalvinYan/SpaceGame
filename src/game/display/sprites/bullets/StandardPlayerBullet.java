package game.display.sprites.bullets;

import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.ships.Ship;

public class StandardPlayerBullet extends Bullet {

	public StandardPlayerBullet(int x, int y, int damage, Ship parent) {
		super(x, y, damage, "file:Assets/standardplayerbullet.png", parent);
		this.x = (int)(x + (parent.getWidth() - getWidth())/2);
		this.y = (int)(y - getHeight());
		System.out.println(parent.getWidth());
		direction = Math.PI / 2;
		speed = 25;
		
	}

	@Override
	public void onHit(Sprite other) {
		if (!other.equals(parent) && other.damageable()) {
			((Ship) other).changeHealth(damage * -1);
			SpaceGame.remove(this);
		}
		
	}

}
