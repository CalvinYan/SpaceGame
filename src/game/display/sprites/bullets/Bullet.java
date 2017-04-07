package game.display.sprites.bullets;

import java.util.ArrayList;

import game.behavior.Pattern;
import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.ships.Ship;
import game.util.CollisionManager;
import javafx.scene.image.Image;

public class Bullet extends Sprite {
	
	Sprite parent;
	
	int damage;
	
	Pattern currentPattern = null;

	ArrayList<Pattern> toExecute = new ArrayList<Pattern>(), running = new ArrayList<Pattern>();
	
	public Bullet(double x, double y, int damage, String imageURL, CollisionManager cm, Sprite parent) {
		super(x, y, imageURL, cm);
		this.damage = damage;
		this.parent = parent;
	}

	public void update(long currentTime) {
		for (Pattern pattern : toExecute) {
			if (currentTime - spawnTime > pattern.getStartTime() * 1000000000) {
				running.add(pattern);
			}
		}
		for (Pattern pattern : running) {
			if (pattern.isFinished() || pattern.isTimeOut(currentTime)) {
				running.remove(pattern);
			} else {
				pattern.update(currentTime);
			}
		}
		toExecute.removeAll(running);
	}
	
	public boolean damageable() { return false; }
	public boolean isPlayer() { return parent.isPlayer(); }
	
	public void addPattern(Pattern pattern) { toExecute.add(pattern); }
	
}
