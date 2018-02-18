package game.display;

import java.util.ArrayList;
import java.util.Iterator;

import game.behavior.GlideAcceleratePattern;
import game.behavior.ShootDownPattern;
import game.behavior.ShootHomingPattern;
import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;
import game.display.sprites.ships.EnemyShip;
import game.display.sprites.ships.Junior;
import game.display.sprites.ships.PlayerShip;
import game.levels.Level;
import game.levels.Level1;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SpaceGame extends Application {
	
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>(),
									toAdd = new ArrayList<Sprite>(),
									toRemove = new ArrayList<Sprite>();
	
	private static Group root;
	
	private static PlayerShip player;
	
	private double healthbarLength;
	
	private Level currentLevel;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Testing");
		
		root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		Canvas canvas = new Canvas(800, 1000);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		long startTime = System.nanoTime();
		
		player = new Junior(400, 500, 20);
		healthbarLength = player.getHealth();
		double scalar = 400 / healthbarLength;
		player.mapControls(scene);
		
		currentLevel = new Level1();
		
		new AnimationTimer() {

			@Override
			public void handle(long currentTime) {
				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.setFill(Color.RED);
				if (healthbarLength > player.getHealth()) healthbarLength -= .15;
				gc.fillRect(0, canvas.getHeight() - 40, scalar * healthbarLength, 40);
				gc.setFill(Color.GREEN);
				gc.fillRect(0, canvas.getHeight() - 40, scalar * player.getHealth(), 40);
				currentLevel.update(currentTime);
				Iterator<Sprite> iter = sprites.iterator();
				while(iter.hasNext()) {
					Sprite sprite = iter.next();
					if (!root.getChildren().contains(sprite.getImageView())) root.getChildren().add(sprite.getImageView());
					sprite.update(currentTime);
					sprite.render();
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
		root.getChildren().remove(sprite.getImageView());
		toRemove.add(sprite);
	}
	
	public static void removeImageView(ImageView iv) {
		root.getChildren().remove(iv);
	}
	
	public static PlayerShip getPlayer() { return player; }
	
	public static int getWidth() { return 800; }
	public static int getHeight() { return 1000; }
	
	public static void flashWarning() {
		Text text = new Text(200, getHeight() / 2, "WARNING!");
		/*text.setScaleX(10);
		text.setScaleY(10);*/
		text.setFill(Color.RED);
		text.setFont(Font.loadFont("file:Assets/futured.ttf", 96));
		root.getChildren().add(text);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	

}
