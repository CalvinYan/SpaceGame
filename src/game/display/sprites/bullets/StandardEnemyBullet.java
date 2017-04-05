package game.display.sprites.bullets;

import game.display.sprites.ships.Ship;
import javafx.scene.image.Image;

public class StandardEnemyBullet extends Bullet {

	public StandardEnemyBullet(int x, int y, Ship parent) {
		super(x, y, parent);
		image = new Image("file:Assets/standardenemybullet.png");
		this.x = (int) (x + (parent.getWidth() - image.getWidth())/2);
		this.y = y + (int)image.getHeight();
		
		direction = 3 * Math.PI / 2;
		speed = 10;
	}

	@Override
	public void onHit() {}

}
