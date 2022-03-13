package application;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Maze {
	private int xCells, yCells;
	private int passageSideLength = 10;
	private int wallWidth = passageSideLength, wallHeight = passageSideLength; // wallHeight and passageSideLength will always be same
	private Color passageColor = Color.BLUEVIOLET;
	private Color wallColor = Color.BLACK;
	
	Maze(){
		this(15, 15);
	}
	
	Maze(int xCells, int yCells){
		this.xCells = 2*xCells + 1;
		this.yCells = 2*yCells + 1;
	}
	
	public GridPane getMaze() {
		return getMaze(xCells, yCells, passageColor, wallColor);
	}
	
	public GridPane getMaze(int xCells, int yCells, Color passageColor) {
		return getMaze(xCells, yCells, passageColor, wallColor);
	}
	
	public GridPane getMaze(int xCells, int yCells, Color passageColor, Color wallColor) {
		GridPane maze = new GridPane();
		
		for(int i=0; i<xCells; ++i) {
			for(int j=0; j<yCells; ++j) {
				// Starting and ending are always walls 
//				if(i==0 || i==xCells-1 || j==0 || j==yCells-1) {
				
				// Whenever i is even OR whenever j is even, the whole row OR column respectively are walls
				if((i&1)==0 || (j&1)==0) {
					maze.add(Cell.getCell(wallWidth, wallHeight, wallColor), i, j);
					// NOTE: GridPane.add(Node, COLUMN_INDEX, ROW_INDEX)
				} else {
					maze.add(Cell.getCell(passageSideLength, passageColor), i, j);
				}
			}
		}
		
		return maze;
	}
	
	public int getPassageSideLength() {
		return this.passageSideLength;
	}
	public void setPassageSideLength(int passageSideLength) {
		this.passageSideLength = passageSideLength;
	}
	public void setPassageColor(Color passageColor) {
		this.passageColor = passageColor;
	}
	public void setWallColor(Color wallColor) {
		this.wallColor = wallColor;
	}
}
