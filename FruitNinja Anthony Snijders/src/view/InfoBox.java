package view;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.Player;

public class InfoBox extends HBox implements Observer {
	private int score = 0;
	private int lives = 3;
	private Label scoreLabel = new Label();
	private Label livesLabel = new Label();

	public InfoBox(int spacing) {
		super(spacing);
		scoreLabel.setText("Score: " + score);
		scoreLabel.setFont(new Font("Arial", 20));
		livesLabel.setText("Lives: " + lives);
		livesLabel.setFont(new Font("Arial", 20));
		getChildren().addAll(scoreLabel, livesLabel);
		setMargin(getChildren().get(0), new Insets(0, 0, 0, 5));
	}

	@Override
	public void update(Observable o, Object arg) {
		Player player = (Player) o;
		score = player.getScore();
		lives = player.getLives();
		scoreLabel.setText("Score:  " + score);
		livesLabel.setText("Lives:  " + lives);

	}

}
