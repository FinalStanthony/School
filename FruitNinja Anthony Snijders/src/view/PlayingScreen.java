package view;

import controller.GameController;
import controller.MusicController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Player;
import model.PlayingField;

public class PlayingScreen extends Stage {
	private BorderPane root;
	private Scene scene;
	private PlayingPane pane;
	private Thread music;
	private MusicController runnable = new MusicController();
	private GameController controller;
	private Thread game;
	private PlayingField field;
	private Player player;
	private InfoBox info;
	private int spacingTop=20;


	public PlayingScreen() {
		music = new Thread(runnable);
		music.start();
		root = new BorderPane();
		field=new PlayingField();
		player=new Player();
		controller = new GameController(field);
		info=new InfoBox(spacingTop);
		player.addObserver(info);
		pane = new PlayingPane(this);
		game = new Thread(controller);
		game.setDaemon(true);
		game.start();
		root.setCenter(pane);
		scene = new Scene(root);
		setTitle("FruitNinja");
		centerOnScreen();
		setScene(scene);
		setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				runnable.disable();
				music.interrupt();
			}
		});

	}
	
	public Player getPlayer(){
		return player;
	}

	public MusicController getRunnable() {
		return runnable;
	}
	
	public void setEndScreen(){
		runnable.disable();
		music.interrupt();
		controller.disable();
		scene=new Scene(new EndScreen(player));
		setScene(scene);
	}

	public GameController getController() {
		return controller;
	}

	public PlayingField getField() {
		return field;
	}

	public InfoBox getInfo() {
		return info;
	}
	
	

}
