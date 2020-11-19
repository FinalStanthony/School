package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Player;

public class EndScreen extends Pane {
	private Label label;
	private Button highScore;
	
	public EndScreen(Player p){
		label=new Label("Game Over! \nYour Score is: \n"+p.getScore());
		getChildren().add(label);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setFont(new Font("Arial", 50));
		highScore=new Button("View HighScores");
		highScore.setTranslateY(150);
		getChildren().add(highScore);
	
	}
}
