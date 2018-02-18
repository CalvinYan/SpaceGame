package game.display.sprites.ships;

import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;
import game.util.CollisionManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Ship extends Sprite {
	
	int damageCount = 0;
	protected int health;

	public Ship(double x, double y, int health, String imageURL, CollisionManager cm) {
		super(x, y, imageURL, cm);
		this.health = health;
	}
	
	public void update(long currentTime) {
		if (damageCount > 1 && health > 0) {
			image.setImage(new Image(imageURL.split("\\.")[0] + "-damaged.png"));
			damageCount--;
		} else if (damageCount == 1) {
			image.setImage(new Image(imageURL));
			damageCount--;
		}
		
	}
	
	public int getHealth() { return health; }
	
	public void changeHealth(int amount) {
		damageCount = 4;
		health += amount;
		if (health <= 0) {
			image.setVisible(false);
			SpaceGame.remove(this);
		}
	}
	
	public boolean damageable() { return true; }
	public abstract boolean isPlayer();
	
	public void onHit() {}
}
