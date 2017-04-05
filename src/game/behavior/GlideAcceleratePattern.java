package game.behavior;

import game.display.sprites.ships.Ship;

public class GlideAcceleratePattern extends Pattern {
	
	private int velocity, acceleration, direction;

	public GlideAcceleratePattern(Ship parent, int vel, int accel, int dir) {
		super(parent);
		velocity = vel;
		acceleration = accel;
		direction = dir;
	}

	@Override
	public void update(long currentTime) {
		parent.setX((int)(parent.getX() + velocity * Math.cos(Math.toRadians(direction))));
		parent.setY((int)(parent.getY() - velocity * Math.sin(Math.toRadians(direction))));
		velocity += acceleration;
		System.out.println(velocity);
	}
	
	public boolean isFinished() { return velocity == 0; }

}
