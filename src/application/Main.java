package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Maze Runner");
		Maze maze;
		BorderPane borderPane = new BorderPane();

		// BorderPane - Top
		Label mainTitle = new Label("Maze Runner");
		mainTitle.setFont(new Font("Comic Sans MS", 28));
		borderPane.setTop(mainTitle);
		BorderPane.setAlignment(mainTitle, Pos.CENTER);

		// BorderPane - Right
		GridPane menu = new GridPane();
		menu.setHgap(5);
		menu.setVgap(10);
		menu.setPadding(new Insets(10));
		int rowCounter = 0;

		Label xCellsLabel = new Label("Cells in each row:");
		Label yCellsLabel = new Label("Cells in each column:");
		Label passageLengthLabel = new Label("Passage length (px):");
		Label wallLengthLabel = new Label("Wall thickness (px):");
		Spinner<Integer> xCellsInput = new Spinner<>(2, 100, 20);
		xCellsInput.setEditable(true);
		Spinner<Integer> yCellsInput = new Spinner<>(2, 100, 20);
		yCellsInput.setEditable(true);
		Spinner<Integer> passageLengthInput = new Spinner<>(5, 50, 25);
		Spinner<Integer> wallThicknessInput = new Spinner<>(2, 50, 3); // 0.15 * 25 = 3.75
		menu.addRow(rowCounter++, xCellsLabel, xCellsInput);
		menu.addRow(rowCounter++, yCellsLabel, yCellsInput);
		menu.addRow(rowCounter++, passageLengthLabel, passageLengthInput);
		menu.addRow(rowCounter++, wallLengthLabel, wallThicknessInput);

		Label passageColorLabel = new Label("Passage Color:");
		Label wallColorLabel = new Label("Wall Color:");
		ColorPicker passageColorPicker = new ColorPicker(Color.CORNFLOWERBLUE);
		ColorPicker wallColorPicker = new ColorPicker(Color.BLACK);
		menu.addRow(rowCounter++, passageColorLabel, passageColorPicker);
		menu.addRow(rowCounter++, wallColorLabel, wallColorPicker);
		borderPane.setRight(menu);

		// BorderPane - Center
		// Just creating a blank grid
		maze = new Maze();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(maze.drawGrid());
		borderPane.setCenter(scrollPane);
		BorderPane.setMargin(scrollPane, new Insets(0.5 * passageLengthInput.getValue()));

		// BorderPane - Right
		Button drawMaze = new Button("Draw Maze");
		drawMaze.setMaxWidth(Double.MAX_VALUE);
		menu.add(drawMaze, 0, rowCounter++, 2, 1);
		drawMaze.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Maze maze = new Maze(xCellsInput.getValue(), yCellsInput.getValue());
				maze.setPassageLength(passageLengthInput.getValue());
				maze.setWallThickness(wallThicknessInput.getValue());
				maze.setPassageColor(passageColorPicker.getValue());
				maze.setWallColor(wallColorPicker.getValue());

				scrollPane.setContent(maze.drawMaze());
				borderPane.setCenter(scrollPane);
			}
		});

		Button solveMaze = new Button("Solve Maze");
		solveMaze.setMaxWidth(Double.MAX_VALUE);
		menu.add(solveMaze, 0, rowCounter++, 2, 1);

		// Scene
		Scene scene = new Scene(borderPane, 1024, 660);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
