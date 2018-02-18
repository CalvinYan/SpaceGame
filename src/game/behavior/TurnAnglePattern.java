package game.behavior;

import game.display.sprites.Sprite;

public class TurnAnglePattern extends Pattern {
	
	double rate, velocity;
	
	public TurnAnglePattern(Sprite parent, double startTime, double timeout, double amount, double velocity) {
		super(parent, startTime, timeout);
		rate = amount / (60 * timeout);
		this.velocity = velocity;
	}

	@Override
	public void update(long currentTime) {
		double direction = parent.getDirection();
		parent.setX(parent.getX() + velocity * Math.cos(Math.toRadians(direction)));
		parent.setY(parent.getY() - velocity * Math.sin(Math.toRadians(direction)));
		direction += rate;
		parent.setDirection(direction);
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
