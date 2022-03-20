package application;

import java.util.Random;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Maze {
	private int xCells; // No. of cells in each row
	private int yCells; // No. of cells in each column
	private int passageLength = 25;
	private int wallThickness = (int) (0.15 * passageLength);

	private Color passageColor = Color.CORNFLOWERBLUE;
	private Color wallColor = Color.BLACK;

	private GridPane gridPane;
	private Cell[][] internalMaze;
	// We maintain an internal maze to look up whether a cell at
	// a given (rowIndex, columnIndex) is a wall or a passage

	Maze() {
		this(20, 20);
	}

	Maze(int xCells, int yCells) {
		this.xCells = 2 * xCells + 1;
		this.yCells = 2 * yCells + 1;
		this.internalMaze = new Cell[this.yCells][this.xCells];
	}

	public GridPane drawMaze() {
		makeInternalMaze(xCells, yCells, passageColor, wallColor);
		tearWallsUsingBinaryTree();
		syncGridPaneWithInternalMaze();
		return gridPane;
	}

	public GridPane drawGrid() {
		makeInternalMaze(xCells, yCells, passageColor, wallColor);
		syncGridPaneWithInternalMaze();
		return gridPane;
	}

	private void makeInternalMaze(int xCells, int yCells, Color passageColor, Color wallColor) {
		for (int i = 0; i < yCells; ++i) {
			for (int j = 0; j < xCells; ++j) {
				Cell newCell = new Cell(wallColor); // we assume that the new cell is a wall

				if (isEven(i)) {
					newCell.setHeight(wallThickness);

					if (isEven(j)) {
						newCell.setWidth(wallThickness);
					} else {
						newCell.setWidth(passageLength);
					}
				} else {
					newCell.setHeight(passageLength);

					if (isEven(j)) {
						newCell.setWidth(wallThickness);
					} else {
						newCell.setWidth(passageLength);
						newCell.setColor(passageColor);
					}
				}
				internalMaze[i][j] = newCell;
			}
		}
		// Set top-left & bottom-right walls as passages for entry and exit
		internalMaze[1][0] = new Cell(wallThickness, passageLength, passageColor);
		internalMaze[yCells - 2][xCells - 1] = new Cell(wallThickness, passageLength, passageColor);
	}

	private void syncGridPaneWithInternalMaze() {
		gridPane = new GridPane();
		int x = internalMaze.length;
		int y = internalMaze[0].length;
		for (int i = 0; i < x; ++i)
			for (int j = 0; j < y; ++j)
				// gridPane.add(Node, colIndex, rowIndex)
				gridPane.add(internalMaze[i][j], j, i);
	}

	private void tearWallsUsingBinaryTree() {
		Random random = new Random();
		int sleepTime = 1000; // in milliseconds

		for (int i = 1; i < internalMaze.length - 1; i = i + 2) {
			for (int j = 1; j < internalMaze[0].length - 1; j = j + 2) {

				boolean hasNorth = true, hasWest = true;
				if (i == 1)
					hasNorth = false;
				if (j == 1)
					hasWest = false;

				if (hasNorth) {
					if (hasWest) {
						if (random.nextInt(2) == 0)
							internalMaze[i - 1][j].setColor(passageColor);
						else
							internalMaze[i][j - 1].setColor(passageColor);
					} else
						internalMaze[i - 1][j].setColor(passageColor);
				} else
					internalMaze[i][j - 1].setColor(passageColor);
				internalMaze[i][j].isVisited = true;
			}
		}
	}

	private void solveMazeUsingBFS() {

	}

	private boolean isEven(int n) {
		return (n & 1) == 0;
	}

	public int getPassageLength() {
		return this.passageLength;
	}

	public void setPassageLength(int passageLength) {
		this.passageLength = passageLength;
	}

	public void setWallThickness(int wallThickness) {
		this.wallThickness = wallThickness;
	}

	public void setPassageColor(Color passageColor) {
		this.passageColor = passageColor;
	}

	public void setWallColor(Color wallColor) {
		this.wallColor = wallColor;
	}
}
