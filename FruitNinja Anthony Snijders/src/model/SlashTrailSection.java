package model;

public class SlashTrailSection {
	private double beginX;
	private double beginY;
	private double endX = -1;
	private double endY = -1;
	private double stepSize;
	private int numberOfSteps=100;
	private double rc;
	private boolean vertical;

	public SlashTrailSection(double beginX, double beginY) {
		this.beginX = beginX;
		this.beginY = beginY;
	}

	public void setEnd(double x, double y) {
		if (!(Math.abs(beginX-x)<10 && Math.abs(beginY-y)<10)) {
			endX = x;
			endY = y;
			if (endX != beginX) {
				rc = (endY - beginY) / (endX - beginX);
				vertical = false;
			} else {
				rc = Double.NaN;
				vertical = true;
			}

		}else{
			endX=-1;
			endY=-1;
		}
	}

	public boolean crossesPoint(double x, double y, double width, double heigth) {
		if (endX != -1 && endY != -1) {
			double currentX;
			double currentY;
			if (vertical) {
				currentX = beginX;
				if (beginY < endY) {
					currentY = beginY;
				} else {
					currentY = endY;
				}
				stepSize=(Math.abs(beginY-endY))/numberOfSteps;
				while (currentY >= Double.min(beginY, endY) && currentY <Double.max(beginY, endY)) {
					if (currentX >= x && currentX <= x + width && currentY >= y && currentY <= y + heigth) {
						return true;
					}
					currentY += stepSize;
				}

			} else {
				if (beginX < endX) {
					currentX = beginX;
					currentY = beginY;
				} else {
					currentX = endX;
					currentY = endY;
				}
				stepSize=(Math.abs(beginX-endX))/numberOfSteps;

				while (currentX >= Double.min(beginX, endX) && currentX <Double.max(beginX, endX)) {
					currentX += stepSize;
					currentY += stepSize * rc;
					if (currentX >= x && currentX <= x + width && currentY >= y && currentY <= y + heigth) {
						return true;
					}
				}
			}

		}
		return false;
	}

}
