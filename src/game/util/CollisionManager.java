package game.util;

import game.display.sprites.Sprite;

public interface CollisionManager {

	public abstract void onHit(Sprite one, Sprite two);
	
}
