package game.display.sprites;

import game.display.SpaceGame;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite {
	
	protected int x, y;
	
	protected ImageView image;
	
	public Sprite(int x, int y, String imageURL) {
		this.x = x;
		this.y = y;
		this.image = new ImageView(imageURL);
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
	
	public void render(GraphicsContext gc) {
		image.setX(x);
		image.setY(y);
	}
	
	public abstract void onHit(Sprite other);
	
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public double getWidth() { return image.getBoundsInLocal().getWidth(); }
	public double getHeight() { return image.getBoundsInLocal().getHeight(); }
	
	public abstract boolean damageable();
	
	public abstract boolean isPlayer();
	
	public Image getImage() { return image.getImage(); }
	public ImageView getImageView() { return image; }
	
	public boolean outOfBounds() {
		if (this.x > 800) return true;
		if (this.x + getWidth() < 0) return true;
		if (this.y > 1000) return true;
		if (this.y + getHeight() < 0) return true;
		return false;
	}

}
