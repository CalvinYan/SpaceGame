package game.display;

import java.util.ArrayList;
import java.util.Iterator;

import game.behavior.GlideAcceleratePattern;
import game.behavior.ShootDownPattern;
import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;
import game.display.sprites.ships.EnemyShip;
import game.display.sprites.ships.Junior;
import game.display.sprites.ships.PlayerShip;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SpaceGame extends Application {
	
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>(),
									toAdd = new ArrayList<Sprite>(),
									toRemove = new ArrayList<Sprite>();
	
	private PlayerShip player;
	
	@Override
	public void start(Stage stage) throws Exception {
		player = new Junior(400, 500, 10);

		stage.setTitle("Testing");
		
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		Canvas canvas = new Canvas(800, 1000);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		long startTime = System.nanoTime();
		
		player.mapControls(scene);
		
		for (int i = 0; i < 5; i++) {
			EnemyShip grunt = new EnemyShip(100 * i + 100, 0, 4);
			grunt.addPattern(new GlideAcceleratePattern(grunt, 20, -1, 270));
			grunt.addPattern(new ShootDownPattern(grunt));
		}
		
		
		new AnimationTimer() {

			@Override
			public void handle(long currentTime) {
				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				Iterator<Sprite> iter = sprites.iterator();
				while(iter.hasNext()) {
					Sprite sprite = iter.next();
					sprite.update(currentTime);
					sprite.render(gc);
					sprite.checkCollision();
					if (sprite.outOfBounds()) remove(sprite);
				}
				sprites.addAll(toAdd);
				sprites.removeAll(toRemove);
				toAdd = new ArrayList<Sprite>();
				toRemove = new ArrayList<Sprite>();
			}
			
		}.start();
		
		stage.show();
	}
	
	public static void add(Sprite sprite) {
		toAdd.add(sprite);
	}
	
	public static void remove(Sprite sprite) {
		toRemove.add(sprite);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	

}
