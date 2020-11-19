package view;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Bomb;
import model.Fruit;
import model.GameObject;
import model.Player;
import model.PlayingField;
import model.SlashTrailSection;

public class PlayingPane extends Pane implements Observer {
	private ImageView background;
	private Image img;
	private File file;
	private PlayingField field;
	private Image strawberry;
	private Image apple;
	private Image orange;
	private Image bomb;
	private Image currentImg;
	private ImageView fruitView = new ImageView();
	private Media slash;
	private MediaPlayer slashPlayer;
	private Player player;
	private GameController controller;
	private PlayingScreen screen;

	public PlayingPane(PlayingScreen screen) {
		this.screen=screen;
		loadFiles();
		background = new ImageView(img);
		getChildren().add(background);
		field=screen.getField();
		field.addObserver(this);
		player=screen.getPlayer();
		getChildren().add(fruitView);
		getChildren().add(screen.getInfo());
		controller=screen.getController();
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				field.setSlash(new SlashTrailSection(event.getX(), event.getY()));
			}

		});
		setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (field.getSlash() != null) {
					int size = (int) currentImg.getHeight();
					field.getSlash().setEnd(event.getX(), event.getY());
					if (field.getSlash().crossesPoint(fruitView.getTranslateX(), fruitView.getTranslateY(), size,
							size)) {
						field.setSlash(null);
						slashPlayer.stop();
						slashPlayer.play();
						player.slashObject(field.getObject());
						controller.makeNewObject();
						try {
							Thread.sleep(1000 / 24);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(player.getLives()<=0){
							screen.setEndScreen();
						}
					}
				}

			}

		});
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				field.setSlash(null);
			}
		});

	}

	@Override
	public void update(Observable o, Object arg) {
		field = (PlayingField) o;
		GameObject gameobject = field.getObject();
		if (gameobject instanceof Bomb) {
			fruitView.setImage(bomb);
			currentImg = bomb;
		} else {
			Fruit fruit = (Fruit) gameobject;
			switch (fruit.getFruitType()) {
			case STRAWBERRY:
				fruitView.setImage(strawberry);
				currentImg = strawberry;
				break;
			case ORANGE:
				fruitView.setImage(orange);
				currentImg = orange;
				break;
			case APPLE:
				fruitView.setImage(apple);
				currentImg = apple;
				break;
			}
		}

		fruitView.setTranslateX(field.getObject().getX());
		fruitView.setTranslateY(field.getObject().getY());

	}

	public void loadFiles() {
		file = new File("Resources/background.png");
		img = new Image(file.toURI().toString());
		file = new File("Resources/strawberry.png");
		strawberry = new Image(file.toURI().toString());
		file = new File("Resources/apple.png");
		apple = new Image(file.toURI().toString());
		file = new File("Resources/orange.png");
		orange = new Image(file.toURI().toString());
		file = new File("Resources/bomb.png");
		bomb = new Image(file.toURI().toString());
		file = new File("Resources/slash.wav");
		slash = new Media(file.toURI().toString());
		slashPlayer = new MediaPlayer(slash);
	}

}
