package ca.mengern.game.puzzle.flood;

import java.util.Map;

import ca.mengern.game.puzzle.flood.controller.game.GameController;
import ca.mengern.game.puzzle.flood.model.grid.FloodGridModel;
import ca.mengern.game.puzzle.flood.util.NamedArgumentMapParser;
import ca.mengern.game.puzzle.flood.view.grid.FloodGrid;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FloodGame extends Application {

	private static final int MAX_GAME_DIMENSION = 600;
	private static final int SQUARE_BORDER_WIDTH = 1;

	private static int gameWidth;
	private static int gameHeight;
	private static int numColors;

	public static void main(String[] args) {
		Map<String, String> argumentMap = NamedArgumentMapParser.parseArgumentsIntoMap(args);

		gameWidth = Integer.valueOf(argumentMap.get("gameWidth"));
		gameHeight = Integer.valueOf(argumentMap.get("gameHeight"));
		numColors = Integer.valueOf(argumentMap.get("numColors"));

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		double squareSize = MAX_GAME_DIMENSION / (Math.max(gameWidth, gameHeight));

		Group rootNode = new Group();

		double sceneWidth = squareSize * gameWidth + 2 * SQUARE_BORDER_WIDTH;
		double sceneHeight = squareSize * gameHeight + 2 * SQUARE_BORDER_WIDTH;
		Scene scene = new Scene(rootNode, sceneWidth, sceneHeight);
		primaryStage.setScene(scene);

		FloodGridModel floodMapModel = FloodGridModel.generateFloodMap(gameWidth, gameHeight, numColors);
		GameController gameController = new GameController(floodMapModel);

		rootNode.getChildren().add(FloodGrid.generateFloodGrid(floodMapModel, squareSize, SQUARE_BORDER_WIDTH));

		primaryStage.setTitle("Flood");
		primaryStage.show();
	}
}