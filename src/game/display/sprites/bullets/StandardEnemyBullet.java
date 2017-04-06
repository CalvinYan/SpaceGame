package game.display.sprites.bullets;

import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.ships.Ship;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StandardEnemyBullet extends Bullet {

	public StandardEnemyBullet(int x, int y, int damage, Ship parent) {
		super(x, y, damage, "file:Assets/standardenemybullet.png", parent);
		this.x = (int) (x + (parent.getWidth() - getWidth())/2);
		this.y = y + (int)getHeight();
		
		direction = 3 * Math.PI / 2;
		speed = 10;
	}

	@Override
	public void onHit(Sprite other) {
		if (other.isPlayer() && other.damageable()) {
			((Ship)other).changeHealth(damage * -1);
			SpaceGame.remove(this);
		}
	}

}
