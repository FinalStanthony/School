package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.PlayingScreen;

public class MainController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = new PlayingScreen();
		primaryStage.show();
	}

	public static void main() {
		launch();
	}

}
