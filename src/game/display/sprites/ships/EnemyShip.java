package game.display.sprites.ships;

import java.util.ArrayList;
import java.util.Iterator;

import game.behavior.Pattern;
import game.display.sprites.Sprite;
import game.util.CollisionManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyShip extends Ship {
	
	Pattern currentPattern = null;
	
	ArrayList<Pattern> toExecute = new ArrayList<Pattern>(), running = new ArrayList<Pattern>();

	public EnemyShip(int x, int y, int health, String imageURL, CollisionManager cm) {
		super(x, y, health, imageURL, cm);
		offscreenAllowed = true;
	}

	public void update(long currentTime) {
		for (Pattern pattern : toExecute) {
			if (currentTime - spawnTime > pattern.getStartTime() * 1000000000) {
				running.add(pattern);
				pattern.start();
			}
		}
		Iterator<Pattern> iter = running.iterator();
		while (iter.hasNext()) {
			Pattern pattern = iter.next();
			if (pattern.isFinished() || pattern.isTimeOut(currentTime)) {
				iter.remove();
			} else {
				pattern.update(currentTime);
			}
		}
		toExecute.removeAll(running);
	}

	public void onHit(Sprite other) {
		// TODO Auto-generated method stub
		
	}
	
	public void addPattern(Pattern pattern) { toExecute.add(pattern); }
	
	public boolean isPlayer() { return false; }

}
