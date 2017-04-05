package game.display;

import java.util.ArrayList;
import java.util.Iterator;

import game.display.sprites.Sprite;
import game.display.sprites.bullets.Bullet;
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
									toAdd = new ArrayList<Sprite>();
	
	private PlayerShip player;
	
	@Override
	public void start(Stage stage) throws Exception {
		player = new Junior(400, 500);

		stage.setTitle("Testing");
		
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		Canvas canvas = new Canvas(800, 1000);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		long startTime = System.nanoTime();
		
		player.mapControls(scene);
		
		new AnimationTimer() {

			@Override
			public void handle(long currentTime) {
				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				System.out.println(sprites.size());
				Iterator<Sprite> iter = sprites.iterator();
				while(iter.hasNext()) {
					Sprite sprite = iter.next();
					sprite.update(currentTime);
					sprite.render(gc);
					if (sprite.outOfBounds()) iter.remove();
				}
				sprites.addAll(toAdd);
				toAdd = new ArrayList<Sprite>();
			}
			
		}.start();
		
		stage.show();
	}
	
	public static void add(Sprite sprite) {
		toAdd.add(sprite);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	

}
