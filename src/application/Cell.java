package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	private static final int defaultX = 0;
	private static final int defaultY = 0;
	private static final int defaultSideLength = 15;
	private static Color color = Color.BLACK;
	
	Cell(){
		this(defaultX, defaultY, defaultSideLength, defaultSideLength, color);
	}
	
	Cell(Color color){
		this(defaultX, defaultY, defaultSideLength, defaultSideLength, color);
	}
	
	Cell(double sideLength, Color color){
		this(defaultX, defaultY, sideLength, sideLength, color);
	}
	
	Cell(double width, double height, Color color){
		this(defaultX, defaultY, width, height, color);
	}
	
	Cell(double x, double y, double width, double height, Color color){
		super.setX(x);
		super.setY(y);
		super.setWidth(width);
		super.setHeight(height);
		super.setFill(color);
	}
	
	public static Cell getCell(int sideLength, Color color) {
		return getCell(sideLength, sideLength, color);
	}
	
	public static Cell getCell(int width, int height, Color color) {
		return new Cell(width, height, color);
	}
	
	public Color getColor() {
		return (Color) getFill();
	}
	
	public void setColor() {
		this.setFill(color);
	}
}
