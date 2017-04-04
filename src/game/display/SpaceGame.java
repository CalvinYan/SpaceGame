package game.display;

import java.util.ArrayList;
import java.util.Iterator;

import game.bullets.Bullet;
import game.ships.Junior;
import game.ships.PlayerShip;
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
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
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
				player.update(currentTime);
				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				int x = player.getX() + player.getXVelocity(), y = player.getY() + player.getYVelocity();
				// Player can't move past walls
				if (x < 0 || x > canvas.getWidth() - player.getWidth()) x = player.getX();
				if (y < 0 || y > canvas.getHeight() - player.getHeight()) y = player.getY();
				gc.drawImage(player.getImage(), x, y);
				Iterator<Bullet> iter = bullets.iterator();
				while(iter.hasNext()) {
					Bullet bullet = iter.next();
					bullet.update();
					gc.drawImage(bullet.getImage(), bullet.getX(), bullet.getY());
				}
				player.setX(x);
				player.setY(y);
			}
			
		}.start();
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	

}
