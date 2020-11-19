package model;

public abstract class GameObject {
	private Player slasher;
	private double x;
	private double y;
	private double xSpeed;
	private double ySpeed;
	private double gravity = 1.5;

	public GameObject(double x, double y, double xSpeed, double ySpeed) {
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public void applySpeed() {
		ySpeed += gravity;
		x += xSpeed;
		y += ySpeed;
	}

	public Player getSlasher() {
		return slasher;
	}
	
	public void setSlasher(Player p){
		slasher=p;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

}
