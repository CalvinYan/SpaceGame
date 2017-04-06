package game.display.sprites.ships;

import javafx.scene.Scene;

public abstract class PlayerShip extends Ship {
	
	public PlayerShip(int x, int y, int health, String imageURL) {
		super(x, y, health, imageURL);
	}
	
	// Called when game is initialized. Initializes ship's control scheme
	public abstract void mapControls(Scene scene);
	
	public boolean isPlayer() { return true; }
}
