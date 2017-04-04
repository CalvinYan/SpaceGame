package game.ships;

import java.util.ArrayList;

import game.bullets.Bullet;
import javafx.scene.Scene;

public abstract class PlayerShip extends Ship {
	
	public PlayerShip(int x, int y) {
		super(x, y);
	}
	
	// Called when game is initialized. Initializes ship's control scheme
	public abstract void mapControls(Scene scene);
	
}
