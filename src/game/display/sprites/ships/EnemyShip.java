package game.display.sprites.ships;

import java.util.ArrayList;

import game.behavior.Pattern;
import game.display.sprites.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyShip extends Ship {
	
	Pattern currentPattern = null;
	
	ArrayList<Pattern> patterns = new ArrayList<Pattern>();

	public EnemyShip(int x, int y, int health, String imageURL) {
		super(x, y, health, imageURL);
	}

	@Override
	public void update(long currentTime) {
		if (currentPattern != null) {
			currentPattern.update(currentTime);
			if (currentPattern.isFinished()) {
				patterns.remove(0);
				currentPattern = null;
			}
		} else if (!patterns.isEmpty()) {
			currentPattern = patterns.get(0);
			currentPattern.start();
		}
	}

	@Override
	public void onHit(Sprite other) {
		// TODO Auto-generated method stub
		
	}
	
	public void addPattern(Pattern pattern) { patterns.add(pattern); }
	
	public boolean isPlayer() { return false; }

}
