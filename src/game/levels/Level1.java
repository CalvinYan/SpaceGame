package game.levels;

import game.behavior.GlideAcceleratePattern;
import game.behavior.ShootHomingPattern;
import game.display.sprites.Sprite;
import game.display.sprites.ships.EnemyShip;
import game.util.SpawnManager;

public class Level1 extends Level {

	public Level1() {
		waves.add(new Wave(0L, new SpawnManager() {
			public void spawn() {
				for (int i = 0; i < 5; i++) {
					EnemyShip grunt = new EnemyShip(160 * i, -64 * (i+1), 4, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {System.out.println("DAMMIT");});
					grunt.addPattern(new GlideAcceleratePattern(grunt, 0, 0, Math.sqrt(400 + i * 128), -1, 270));
					grunt.addPattern(new ShootHomingPattern(grunt, 0.3, 0));
					grunt.addPattern(new GlideAcceleratePattern(grunt, 5, 0, 5, 0, 270));
				}
			}
		}));
		
		for (int a = 0; a < 3; a++) {
			waves.add(new Wave(4, new SpawnManager() {
				public void spawn() {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if ((i + j) % 2 == 0) {
								EnemyShip grunt = new EnemyShip(250 + i * 150, -300 + j * 100, 2, "file:Assets/grunt.png", (Sprite one, Sprite two) -> {});
								grunt.addPattern(new GlideAcceleratePattern(grunt, 0, 0, 3, 0, 270));
								grunt.addPattern(new ShootHomingPattern(grunt, 0, 0));
							}
						}
					}
				}
			}));
		}
	}
	
}
