package model;

import java.util.Observable;
import java.util.Random;

public class PlayingField extends Observable {
	private SlashTrailSection slash;
	private GameObject gameobject;
	private Random random = new Random();
	private int width = 500;
	private int heigth = 500;
	private double defaultYSpeed=30;
	private double defaultXSpeed=10;
	private int size;

	public SlashTrailSection getSlash() {
		return slash;
	}

	public void setSlash(SlashTrailSection slash) {
		this.slash = slash;
	}

	public GameObject getObject() {
		return gameobject;
	}

	public void setObject(GameObject object) {
		this.gameobject = object;
	}

	public void updateGameObject() {
		gameobject.applySpeed();
		if(!(gameobject.getX()>=-size&&gameobject.getX()<=width&&gameobject.getY()>=-size&&gameobject.getY()<=heigth)){
			makeRandomGameObject();
		}
		setChanged();
		notifyObservers();
	}

	public void makeRandomGameObject() {
		boolean bombOrFruit = random.nextBoolean();
		int corner = random.nextInt(3);
		double position = random.nextDouble();
		int typeOfFruit=random.nextInt(FruitType.values().length);
		double x;
		double y;
		double xSpeed;
		double ySpeed;
		double xSpeedRatio = random.nextDouble()*1+1;
		size=50;
		if(FruitType.values()[typeOfFruit].getGroote().equals("Klein")){
			size=30;
		}

		switch (corner) {
		case 0:
			x = width;
			y = position * (heigth*0.4-size)+heigth*0.5;
			xSpeed = -defaultXSpeed*xSpeedRatio;
			ySpeed = -defaultYSpeed;
			break;
		case 1:
			double ySpeedRatio=random.nextDouble()*-0.5-0.8;
			x = position * (width-size);
			y = heigth;
			xSpeed = 0;
			ySpeed = ySpeedRatio*defaultYSpeed;
			break;
		case 2:
			x = 0;
			y = position * (heigth*0.4-size)+heigth*0.5;
			xSpeed = defaultXSpeed * xSpeedRatio;
			ySpeed = -defaultYSpeed;
			break;
		default:
			System.err.println("Corner: " + corner);
			x = 0;
			y = 0;
			xSpeed = 0;
			ySpeed = 0;

		}
		if (bombOrFruit) {
			gameobject = new Fruit(x, y, xSpeed, ySpeed, FruitType.values()[typeOfFruit]);
		} else {
			gameobject = new Bomb(x, y, xSpeed, ySpeed);
		}
		slash=null;
		setChanged();
		notifyObservers();

	}

}
