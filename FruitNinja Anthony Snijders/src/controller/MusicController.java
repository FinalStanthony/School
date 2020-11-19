package controller;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicController implements Runnable {
	private MediaPlayer mediaPlayer;
	private File file;
	private Media sound;
	private boolean active;

	public MusicController() {
		file = new File("Resources/game_music.wav");
		sound = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.2);
		active = true;

	}

	@Override
	public void run() {
		while (active) {
			mediaPlayer.play();
			try {
				Thread.sleep(122000);
				mediaPlayer.stop();

			} catch (InterruptedException e) {
			}
		}

	}

	public void disable() {
		mediaPlayer.stop();
		active = false;
	}
}
