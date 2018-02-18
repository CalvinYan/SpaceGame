package game.display.sprites.ships.bosses;

import java.util.ArrayList;
import java.util.Random;

import game.behavior.GlideAcceleratePattern;
import game.behavior.Pattern;
import game.behavior.ShootRandomHomingPattern;
import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;
import game.display.sprites.ships.EnemyShip;
import game.display.sprites.ships.Ship;
import game.util.PatternUpdater;
import javafx.scene.image.Image;

public class Boss1 extends EnemyShip{

	private ArrayList<EnemyShip> turrets = new ArrayList<EnemyShip>();
	private EnemyShip tower;
	
	private ArrayList<Pattern> firstTurretShootPatterns = new ArrayList<Pattern>();
	
	private Pattern firstTowerShootPattern;
	
	private boolean turretsDown = false, towerDown = false;
	
	public Boss1() {
		super(0, -3400, 1, "file:Assets/boss1.png", (Sprite one, Sprite two) -> {});
		
		addPattern(new GlideAcceleratePattern(this, 0, 4, 13, 0, 270));
		Image image = new Image("file:Assets/turret.png");
		for (int i = -2; i <= 0; i++) {
			for (int j = -4; j <= 4; j++) {
				if (Math.abs(i) + Math.abs(j) == 4) {
					double x = (SpaceGame.getWidth() - image.getWidth()) / 2 + j * 84,
							y = -3000 - i * 84;
					EnemyShip turret = new EnemyShip(x, y, 50, "file:Assets/turret.png", (Sprite one, Sprite two) -> {});
					turret.addPattern(new GlideAcceleratePattern(turret, 0, 4, 13, 0, 270));
					
					Pattern pattern = new Pattern(turret, 4, 0, new PatternUpdater() {
						public void update(Sprite parent, Pattern pattern, long currentTime) {
							double playerX = SpaceGame.getPlayer().getX() - parent.getX(),
									playerY = parent.getY() - SpaceGame.getPlayer().getY(),
									angle = Math.atan2(playerY, playerX);
							parent.setDirection(Math.toDegrees(angle));
						}
					});
					
					turret.addPattern(pattern);
					firstTurretShootPatterns.add(pattern);
					turrets.add(turret);
					turret.addPattern(new ShootRandomHomingPattern(turret, 0.5, 0, 1, 1.5, 30));
				}
			}
		}
		
		image = new Image("file:Assets/tower.png");
		
		tower = new EnemyShip((SpaceGame.getWidth() - image.getWidth()) / 2, -3100, 200, "file:Assets/tower.png", (Sprite one, Sprite two) -> {});
		tower.addPattern(new GlideAcceleratePattern(tower, 0, 4, 13, 0, 270));
		firstTowerShootPattern = new Pattern(tower, 4 + Math.random() * 3, 2, new PatternUpdater() {
			
			public void update(Sprite parent, Pattern pattern, long currentTime) {
				
				if (currentTime - pattern.getPatternStartTime() < 2000000) {
					((EnemyShip)parent).addPattern(new Pattern(parent, pattern.getStartTime() + 5 + Math.random() * 2, 2, this));
					System.out.println();
				}
				if (currentTime - pattern.getLastShot() > 1000000000/5) {
					pattern.setLastShot(currentTime);
					double value = (currentTime - pattern.getPatternStartTime()) / 2000000000.0;
					double angle = (180 + value * 180) % 360;
					for (int i = 0; i < 5; i++) {
						Image image = new Image("file:Assets/standardenemybullet.png");
						Bullet bullet = new Bullet(parent.getX() + (parent.getWidth() - image.getWidth())/2, 
								parent.getY() + parent.getHeight()/2, 1, "file:Assets/standardenemybullet.png", 
								(Sprite one, Sprite two) -> {
									if (two.damageable() && two.isPlayer()) {
										((Ship)two).changeHealth(-1);
										SpaceGame.remove(one);
									}
								}, parent);
						bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 3*(i+1), 0, angle));
					}
				}
			}
		});
		tower.addPattern(firstTowerShootPattern);
	}
	
	public boolean damageable() { return false; }
	
	public void update(long currentTime) {
		super.update(currentTime);
		health = 0;
		
		boolean turretsNowDown = true;
		for (EnemyShip turret : turrets) {
			if (turret.getHealth() > 0) turretsNowDown = false;
			health += turret.getHealth();
		}
		
		if (turretsNowDown && !turretsDown) {
			turretsDown = true;
			tower.cancelAllPatterns();
			tower.addPattern(new Pattern(tower, 0, 0, new PatternUpdater() {
				public void update(Sprite parent, Pattern pattern, long currentTime) {
					Image image = new Image("file:Assets/standardenemybullet.png");
					if (currentTime - pattern.getLastShot() > 1000000000 / 30) {
						pattern.setLastShot(currentTime);
						double angle = 180 + (new Random().nextGaussian()/3 + 0.5) * 180;
						for (int i = -1; i <= 1; i++) {
							Bullet bullet = new Bullet(parent.getX() + (parent.getWidth() - image.getWidth())/2 + i * 8 * Math.sin(Math.toRadians(angle)), 
									parent.getY() + parent.getHeight()/2 + i * 5 * Math.cos(Math.toRadians(angle)), 1, "file:Assets/standardenemybullet.png", 
									(Sprite one, Sprite two) -> {
										if (two.damageable() && two.isPlayer()) {
											((Ship)two).changeHealth(-1);
											SpaceGame.remove(one);
										}
									}, parent);
							bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 10, 0, angle));
						}
					}
				}
			}));
		}
		
		if (tower.getHealth() <= 0 && !towerDown) {
			towerDown = true;
			for (int i = 0; i < 6; i++) {
				EnemyShip turret = turrets.get(i);
				turret.cancelAllPatterns();
				turret.addPattern(new Pattern(turret, 4, 0, new PatternUpdater() {
						public void update(Sprite parent, Pattern pattern, long currentTime) {
							double playerX = SpaceGame.getPlayer().getX() - parent.getX(),
									playerY = parent.getY() - SpaceGame.getPlayer().getY(),
									angle = Math.atan2(playerY, playerX);
							parent.setDirection(Math.toDegrees(angle));
						}
				}));
				turret.addPattern(new Pattern(turret, 0, 0, new PatternUpdater() {
					public void update(Sprite parent, Pattern pattern, long currentTime) {
						Image image = new Image("file:Assets/standardenemybullet");
						if (currentTime - pattern.getLastShot() > 1000000000 / 1.25) {
							pattern.setLastShot(currentTime);
							double playerX = SpaceGame.getPlayer().getX() - parent.getX(),
									playerY = parent.getY() - SpaceGame.getPlayer().getY(),
									angle = Math.atan2(playerY, playerX);
							for (int i = -1; i <= 1; i++) {
								Bullet bullet = new Bullet(parent.getX() + (parent.getWidth() - image.getWidth())/2, 
										parent.getY() + image.getHeight(), 1, "file:Assets/standardenemybullet.png", 
										(Sprite one, Sprite two) -> {
											if (two.damageable() && two.isPlayer()) {
												((Ship)two).changeHealth(-1);
												SpaceGame.remove(one);
											}
										}, parent);
								bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 10, 0, Math.toDegrees(angle) + i * 30));
							}
						}
					}
				}));
			}
		}
		
	}
	
}
