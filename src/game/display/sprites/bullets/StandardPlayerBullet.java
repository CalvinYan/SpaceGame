package game.display.sprites.bullets;

import game.display.SpaceGame;
import game.display.sprites.ships.Ship;
import javafx.scene.image.Image;

public class StandardPlayerBullet extends Bullet {

	public StandardPlayerBullet(int x, int y, Ship parent) {
		super(x, y, parent);
		image = new Image("file:Assets/standardplayerbullet.png");
		this.x = (int)(x + (parent.getWidth() - image.getWidth())/2);
		this.y = (int)(y - image.getHeight());
		direction = 90;
		speed = 15;
		
	}

	@Override
	public void onHit() {}

}
