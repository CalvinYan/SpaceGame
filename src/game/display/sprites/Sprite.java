package game.display.sprites;

import game.display.SpaceGame;
import game.util.CollisionManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite {
	
	protected double x, y, direction = 0;
	
	protected ImageView image;
	
	private CollisionManager cm;
	
	protected long spawnTime;
	
	protected boolean offscreenAllowed = false;
	
	protected String imageURL;
	
	public Sprite(double x, double y, String imageURL, CollisionManager cm) {
		this.x = x;
		this.y = y;
		this.imageURL = imageURL;
		this.image = new ImageView(imageURL);
		this.cm = cm;
		spawnTime = System.nanoTime();
		SpaceGame.add(this);
	}
	
	public abstract void update(long currentTime);
	
	public void checkCollision() {
		for (Sprite sprite : SpaceGame.sprites) {
			if (equals(sprite)) continue;
			if (image.getBoundsInLocal().intersects(sprite.getImageView().getBoundsInLocal())) {
				onHit(sprite);
			}
		}
	}
	
	public void render() {
		image.setRotate(-1 * direction);
		image.setX(x);
		image.setY(y);
	}
	
	public void onHit(Sprite other) {
		if (cm == null) {
			System.out.println(getX());
			System.exit(0);
		}
		cm.onHit(this, other);
	}
	
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	public double getY() { return y; }
	public void setY(double y) { this.y = y; }
	public double getWidth() { return image.getBoundsInLocal().getWidth(); }
	public double getHeight() { return image.getBoundsInLocal().getHeight(); }
	public double getDirection() { 
		if (direction < 0) direction += 360;
		direction %= 360;
		return direction;
	}
	public void setDirection(double val) { direction = val; }
	
	public abstract boolean damageable();
	
	public abstract boolean isPlayer();
	
	public Image getImage() { return image.getImage(); }
	public ImageView getImageView() { return image; }
	
	public void setOffscreenAllowed(boolean val) { offscreenAllowed = val; }
	
	public boolean outOfBounds() {
		if (this.y > 1000) return true;
		if (this.x > 800) return true;
		if (this.x + getWidth() < 0) return true;
		if (offscreenAllowed) return false;
		if (this.y + getHeight() < 0) return true;
		return false;
	}

}
