package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	private static final int defaultX = 0;
	private static final int defaultY = 0;
	private static final int defaultSideLength = 15;
	private static Color color = Color.BLACK;
	public boolean isVisited = false;

	Cell() {
		this(defaultX, defaultY, defaultSideLength, defaultSideLength, color);
	}

	Cell(Color color) {
		this(defaultX, defaultY, defaultSideLength, defaultSideLength, color);
	}

	Cell(double sideLength, Color color) {
		this(defaultX, defaultY, sideLength, sideLength, color);
	}

	Cell(double width, double height, Color color) {
		this(defaultX, defaultY, width, height, color);
	}

	Cell(double x, double y, double width, double height, Color color) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setColor(color);
	}

	public Cell getCell() {
		return this;
	}

	public Color getColor() {
		return (Color) getFill();
	}

	public void setColor(Color color) {
		this.setFill(color);
	}
}
