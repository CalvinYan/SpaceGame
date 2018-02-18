package game.levels;

import java.util.ArrayList;

import game.behavior.GlideAcceleratePattern;
import game.behavior.Pattern;
import game.behavior.ShootHomingPattern;
import game.behavior.ShootRandomHomingPattern;
import game.behavior.TurnAnglePattern;
import game.display.SpaceGame;
import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;
import game.display.sprites.ships.EnemyShip;
import game.display.sprites.ships.Ship;
import game.display.sprites.ships.bosses.Boss1;
import game.util.PatternUpdater;
import game.util.SpawnManager;
import javafx.scene.image.Image;

public class Level1 extends Level {

	public Level1() {
		/*waves.add(new Wave(new SpawnManager() {
			public ArrayList<Ship> spawn() {
				ArrayList<Ship> retval = new ArrayList<Ship>();
				for (int i = 0; i < 5; i++) {
					EnemyShip grunt = new EnemyShip(48 + 160 * i, -64 * (i+1), 3, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
					grunt.addPattern(new GlideAcceleratePattern(grunt, 0, 0, Math.sqrt(400 + i * 128), -1, 270));
					grunt.addPattern(new ShootHomingPattern(grunt, 0.3, 0));
					grunt.addPattern(new GlideAcceleratePattern(grunt, 0.4, 0, 1, 0, 270));
					retval.add(grunt);
				}
				return retval;
			}
		}));
		
		
		waves.add(new Wave(new SpawnManager() {
			public ArrayList<Ship> spawn() {
				ArrayList<Ship> retval = new ArrayList<Ship>();
				for (int a = 0; a < 3; a++) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if ((i + j) % 2 == 0) {
								EnemyShip grunt = new EnemyShip(250 + i * 150, -400 + j * 100 - a * 400, 3, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
								grunt.addPattern(new GlideAcceleratePattern(grunt, 0, 0, 3, 0, 270));
								grunt.addPattern(new ShootHomingPattern(grunt, 0, 0));
								retval.add(grunt);
							}
						}
					}
				}
				return retval;
			}
		}));
		
		waves.add(new Wave(new SpawnManager() {
			public ArrayList<Ship> spawn() {
				ArrayList<Ship> retval = new ArrayList<Ship>();
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 5; j++) {
						Image image = new Image("file:Assets/grunt.png");
						EnemyShip grunt = new EnemyShip((800 - (int)image.getWidth()) * i, -200 - (int)image.getHeight() - 160 * j, 3, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
						double duration = (200 - grunt.getY()) / 480;
						grunt.addPattern(new GlideAcceleratePattern(grunt, 0, duration, 8, 0, 270));
						grunt.addPattern(new TurnAnglePattern(grunt, duration, 0.75, 90 - 180 * i, 8));
						grunt.addPattern(new GlideAcceleratePattern(grunt, duration + 0.75, 0, 5, 0, 180 * i));
						grunt.addPattern(new Pattern(grunt, duration + 0.75, 0, new PatternUpdater() {
							long waitTime = (int)(Math.random() * 250000000) + 750000000;
							public void update(Sprite parent, Pattern pattern, long currentTime) {
								if (currentTime - pattern.getLastShot() > waitTime) {
									pattern.setLastShot(currentTime);
									waitTime = (int)(Math.random() * 500000000) + 500000000;
									Bullet bullet = new Bullet(parent.getX() + (parent.getWidth() - image.getWidth())/2, 
																parent.getY() + image.getHeight(), 1, "file:Assets/standardenemybullet.png", 
																(Sprite one, Sprite two) -> {
																	if (two.damageable() && two.isPlayer()) {
																		((Ship)two).changeHealth(-3);
																		SpaceGame.remove(one);
																	}
																}, parent);
									bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 0, 0.2, 270));
								}
							}
						}));
						retval.add(grunt);
					}
				}
				return retval;
			}
		}));
				
		waves.add(new Wave(new SpawnManager() {
			public ArrayList<Ship> spawn() {
				ArrayList<Ship> retval = new ArrayList<Ship>();
				Image image = new Image("file:Assets/grunt.png");
				EnemyShip grunt = new EnemyShip(400 - (int)image.getWidth() / 2, -1 * (int)image.getHeight(), 30, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
				grunt.addPattern(new GlideAcceleratePattern(grunt, 0, 0, 15, -1, 270));
				grunt.addPattern(new Pattern(grunt, 0.3, 10, new PatternUpdater() {
					public void update(Sprite parent, Pattern pattern, long currentTime) {
						if ((currentTime - pattern.getLastShot()) > 1000000000/20) {
							pattern.setLastShot(currentTime);
							for (int i = 0; i < 2; i++) {
								double value = (currentTime - pattern.getPatternStartTime()) / 2000000000.0 * 360;
								Bullet bullet = new Bullet(400-10, 
									parent.getY() - parent.getHeight()/2 + image.getHeight(), 1, "file:Assets/standardenemybullet.png", 
									(Sprite one, Sprite two) -> {
										if (two.damageable() && two.isPlayer()) {
											((Ship)two).changeHealth(-1);
											SpaceGame.remove(one);
										}
									}, parent);
								bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 5, 0, (value + 180 * i) % 360));
							}
						}
					}
				}));
				grunt.addPattern(new GlideAcceleratePattern(grunt, 10.3, 0, 3, 1, 270));
				retval.add(grunt);
				return retval;
			}
		}));
		
		waves.add(new Wave(new SpawnManager() {
			public ArrayList<Ship> spawn() {
				ArrayList<Ship> retval = new ArrayList<Ship>();
				Image image = new Image("file:Assets/grunt.png");
				for (int i = 0; i < 8; i++) {
					EnemyShip grunt = new EnemyShip((int)(Math.random() * (SpaceGame.getWidth() - 200)) + 100, -1 * (int)image.getHeight() - 50, 4, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
					grunt.addPattern(new GlideAcceleratePattern(grunt, i, 0, 0, 0.1, Math.random() * 60 + 240));
					grunt.addPattern(new Pattern(grunt, 0, 10, new PatternUpdater() {
						public void update(Sprite parent, Pattern pattern, long currentTime) {
							if ((currentTime - pattern.getLastShot()) > 1000000000/10) {
								pattern.setLastShot(currentTime);
								for (int i = 0; i < 2; i++) {
									double value = (currentTime - pattern.getPatternStartTime()) / 2000000000.0 * 360;
									Bullet bullet = new Bullet(parent.getX(), 
										parent.getY() - parent.getHeight()/2 + image.getHeight(), 1, "file:Assets/standardenemybullet.png", 
										(Sprite one, Sprite two) -> {
											if (two.damageable() && two.isPlayer()) {
												((Ship)two).changeHealth(-1);
												SpaceGame.remove(one);
											}
										}, parent);
									bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 5, 0, (value + 180 * i) % 360));
								}
							}
						}
					}));
					retval.add(grunt);
				}
				return retval;
			}
		}));
		
		waves.add(new Wave(new SpawnManager() {
			public ArrayList<Ship> spawn() {
				ArrayList<Ship> retval = new ArrayList<Ship>();
				Image image = new Image("file:Assets/grunt.png");
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 2; j++) {
						EnemyShip grunt = new EnemyShip((SpaceGame.getWidth() - image.getWidth())/2 - 84 + 168 * j, -1 * (int)image.getHeight() - 84 * (i+1), 5, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
						double duration = (500 - grunt.getY()) / 240;
						grunt.addPattern(new GlideAcceleratePattern(grunt, 0, duration, 4, 0, 270));
						grunt.addPattern(new TurnAnglePattern(grunt, duration, 0.375, -90 + 180 * j, 4));
						grunt.addPattern(new TurnAnglePattern(grunt, duration + 0.375, 0.375, -90 + 180 * j, 4));
						grunt.addPattern(new GlideAcceleratePattern(grunt, duration + 0.75, 1, 4, 0, 90));
						grunt.addPattern(new TurnAnglePattern(grunt, duration + 1.75, 0.75, 90 - 180 * j, 4));
						grunt.addPattern(new GlideAcceleratePattern(grunt, duration + 2.5, 0, 4, 0, 180 - 180 * j));
						grunt.addPattern(new ShootRandomHomingPattern(grunt, 0, 0, 1, 1.2, 0));
						retval.add(grunt);
					}
				}
				return retval;
			}
		}));
		
		
		waves.add(new Wave(new SpawnManager() {
			public ArrayList<Ship> spawn() {
				ArrayList<Ship> retval = new ArrayList<Ship>();
				Image image = new Image("file:Assets/grunt.png");
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 9; j += (j%5 == 0) ? 3 : 1) {
						EnemyShip grunt = new EnemyShip((SpaceGame.getWidth() - image.getWidth()) / 8 * j, -400 * i - image.getHeight(), 3, ("file:Assets/grunt.png"), (Sprite one, Sprite two) -> {});
						grunt.addPattern(new GlideAcceleratePattern(grunt, 0, 0, 3, 0, 270));
						grunt.addPattern(new ShootRandomHomingPattern(grunt, 0, 0, 0.5, 1, 0));
						retval.add(grunt);
					}
				}
				return retval;
			}
		}));
		
		waves.add(new Wave(new SpawnManager() {
			public ArrayList<Ship> spawn() {
				ArrayList<Ship> retval = new ArrayList<Ship>();
				Image image = new Image("file:Assets/grunt.png");
				for (int i = -4; i < 5; i++) {
					double x = (SpaceGame.getWidth() - image.getWidth())/2 + 100 * i,
						y = (SpaceGame.getHeight() + 20 * i * i);
					EnemyShip grunt = new EnemyShip(x, y, 4, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
					grunt.addPattern(new GlideAcceleratePattern(grunt, 0.1 * Math.abs(i), 0, 30, -9/16.0, 90));
					grunt.addPattern(new Pattern(grunt, 0.5, 0, new PatternUpdater() {
						public void update(Sprite parent, Pattern pattern, long currentTime) {
							if (currentTime - pattern.getLastShot() > 1000000000) {
								pattern.setLastShot(currentTime);
								double playerX = SpaceGame.getPlayer().getX(), playerY = SpaceGame.getPlayer().getY(),
										angle = Math.atan2(200 - playerY, playerX - SpaceGame.getWidth()/2);
								Image image = new Image("file:Assets/standardenemybullet.png");
								Bullet bullet = new Bullet(parent.getX() + (parent.getWidth() - image.getWidth()), 
										parent.getY() - parent.getHeight()/2, 1, "file:Assets/standardenemybullet.png", 
										(Sprite one, Sprite two) -> {
											if (two.damageable() && two.isPlayer()) {
												((Ship)two).changeHealth(-1);
												SpaceGame.remove(one);
											}
										}, parent);
								bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 0, 12, 0, Math.toDegrees(angle)));
							}
						}
					}));
					retval.add(grunt);
				}
				return retval;
			}
		}));
		
		waves.add(new Wave(new SpawnManager() {
			public ArrayList<Ship> spawn() {
				ArrayList<Ship> retval = new ArrayList<Ship>();
				Image image = new Image("file:Assets/grunt.png");
				for (double i = 0; i < 2*Math.PI; i += Math.PI / 6) {
					double x = (SpaceGame.getWidth() - image.getWidth())/2 + 300 * Math.cos(i),
							y = 200 * Math.sin(i) - 250 - image.getHeight();
					EnemyShip grunt = new EnemyShip(x, y, 6, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
					grunt.addPattern(new GlideAcceleratePattern(grunt, 0, 0, 1, 0, 270));
					grunt.addPattern(new ShootRandomHomingPattern(grunt, 0, 0, 4, 6, 0));
					retval.add(grunt);
				}
				double x = (SpaceGame.getWidth() - image.getWidth())/2, y = -250 - image.getHeight();
				EnemyShip grunt = new EnemyShip(x, y, 30, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
				grunt.addPattern(new GlideAcceleratePattern(grunt, 0, 0, 1, 0, 270));
				grunt.addPattern(new Pattern(grunt, 0, 0, new PatternUpdater() {
					public void update(Sprite parent, Pattern pattern, long currentTime) {
						if (currentTime - pattern.getLastShot() > 1500000000) {
							pattern.setLastShot(currentTime);
							for (int i = 0; i < 360; i += 360/12) {
								Bullet bullet = new Bullet(parent.getX() + (parent.getWidth() - new Image("file:Assets/standardenemybullet.png").getWidth())/2, 
										parent.getY() - parent.getHeight()/2 + image.getHeight(), 1, "file:Assets/standardenemybullet.png", 
										(Sprite one, Sprite two) -> {
											if (two.damageable() && two.isPlayer()) {
												((Ship)two).changeHealth(-1);
												SpaceGame.remove(one);
											}
										}, parent);
									bullet.addPattern(new GlideAcceleratePattern(bullet, 0, 20/60, 15, -0.5, i));
									bullet.addPattern(new GlideAcceleratePattern(bullet, 20/60, 0, 5, 0, i));
							}
						}
					}
				}));
				retval.add(grunt);
				return retval;
			}
		}));*/
	}
	
	public void spawnBoss() { new Boss1(); }
	
}
