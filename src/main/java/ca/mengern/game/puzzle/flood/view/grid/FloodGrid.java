package ca.mengern.game.puzzle.flood.view.grid;

import ca.mengern.game.puzzle.flood.model.grid.FloodGridModel;
import ca.mengern.game.puzzle.flood.view.square.FloodSquare;
import javafx.scene.Group;

public class FloodGrid {

	public static Group generateFloodGrid(FloodGridModel floodMap, double squareSize, int squareBorderWidth) {
		Group floodSquareGrid = new Group();

		for (int i = 0; i < floodMap.getGridLength(); i++) {
			for (int j = 0; j < floodMap.getGridHeight(); j++) {
				FloodSquare square = new FloodSquare(squareSize, squareBorderWidth, floodMap.getNode(i, j));

				square.setX(i * squareSize + squareBorderWidth);
				square.setY(j * squareSize + squareBorderWidth);

				floodSquareGrid.getChildren().add(square);
			}
		}

		return floodSquareGrid;
	}
}
