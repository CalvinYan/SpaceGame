package game.util;

import game.behavior.Pattern;
import game.display.sprites.Sprite;

public interface PatternUpdater {

	public abstract void update(Sprite parent, Pattern pattern, long currentTime);
	
}
