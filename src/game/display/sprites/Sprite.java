package game.display.sprites;

import game.display.SpaceGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
	
	protected int x, y;
	
	protected Image image;
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		SpaceGame.add(this);
	}
	
	public abstract void update(long currentTime);
	
	public void render(GraphicsContext gc) {
		gc.drawImage(image, x, y);
	}
	
	public abstract void onHit();
	
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public double getWidth() { return image.getWidth(); }
	public double getHeight() { return image.getHeight(); }
	
	public Image getImage() { return image; }
	
	public boolean outOfBounds() {
		if (this.x > 800) return true;
		if (this.x + image.getWidth() < 0) return true;
		if (this.y > 1000) return true;
		if (this.y + image.getHeight() < 0) return true;
		return false;
	}

}
