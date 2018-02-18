package game.behavior;

import game.display.sprites.Sprite;
import game.display.sprites.ships.Ship;

public class GlideAcceleratePattern extends Pattern {
	
	private double velocity, acceleration, direction;

	public GlideAcceleratePattern(Sprite parent, double startTime, double timeout, double vel, double accel, double dir) {
		super(parent, startTime, timeout);
		velocity = vel;
		acceleration = accel;
		direction = dir;
	}
	
	public void start() {
		super.start();
		parent.setDirection(direction);
	}

	@Override
	public void update(long currentTime) {
		parent.setX(parent.getX() + velocity * Math.cos(Math.toRadians(direction)));
		parent.setY(parent.getY() - velocity * Math.sin(Math.toRadians(direction)));
		velocity += acceleration;
	}
	
	public boolean isFinished() { return velocity < 0; }

}
