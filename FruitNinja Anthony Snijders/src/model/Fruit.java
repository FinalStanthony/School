package model;

public class Fruit extends GameObject {
	private FruitType fruitType;

	public Fruit(double x, double y, double xSpeed, double ySpeed, FruitType fruitType) {
		super(x, y, xSpeed, ySpeed);
		this.fruitType = fruitType;
	}

	public FruitType getFruitType() {
		return fruitType;
	}

	public void setFruitType(FruitType fruitType) {
		this.fruitType = fruitType;
	}

}
