package model;

import java.util.Observable;

public class Player extends Observable {
	private int lives = 3;
	private int score = 0;

	public Player() {
	}

	public int getLives() {
		return lives;
	}

	public void removeLive() {
		lives--;
		setChanged();
		notifyObservers();
	}

	public int getScore() {
		return score;
	}

	public void addScore(int score) {
		this.score += score;
		setChanged();
		notifyObservers();
	}
	
	public void slashObject(GameObject objectToSlash) {
		objectToSlash.setSlasher(this);
		if (objectToSlash instanceof Bomb) {
			removeLive();
		} else {
			Fruit fruit = (Fruit) objectToSlash;
			if (fruit.getFruitType().getGroote().equals("Klein")) {
				addScore(100);
			} else {
				addScore(50);
			}
		}
	}

}
