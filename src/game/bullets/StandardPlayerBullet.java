package game.bullets;

import game.ships.Ship;
import javafx.scene.image.Image;

public class StandardPlayerBullet extends Bullet {

	public StandardPlayerBullet(int x, int y, Ship parent) {
		super(parent);
		image = new Image("file:Assets/standardplayerbullet.png");
		this.x = (int)(x + (parent.getWidth() - image.getWidth())/2);
		this.y = (int)(y - image.getHeight());
		direction = 90;
		speed = 25;
		
	}

	@Override
	public void onHit() {
		parent.getBullets().remove(this);
	}

}
