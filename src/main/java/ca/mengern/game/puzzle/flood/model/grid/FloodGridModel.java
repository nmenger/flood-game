package ca.mengern.game.puzzle.flood.model.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.mengern.game.puzzle.flood.model.node.FloodNodeModel;
import ca.mengern.game.puzzle.flood.view.square.SquareColor;

public class FloodGridModel {

	private final FloodNodeModel[][] grid;
	private final int gridLength;
	private final int gridHeight;

	private FloodGridModel(int gridLength, int gridHeight) {
		super();
		grid = new FloodNodeModel[gridLength][gridHeight];

		this.gridLength = gridLength;
		this.gridHeight = gridHeight;
	}

	public static FloodGridModel generateFloodMap(int gridLength, int gridHeight, int numColors) {
		FloodGridModel floodMap = new FloodGridModel(gridLength, gridHeight);

		List<SquareColor> availableColors = generateAvailableColors(numColors);
		floodMap.fillGridRandomly(availableColors);
		floodMap.assignNeighbours();
		floodMap.assignRootMap();

		return floodMap;
	}

	public FloodNodeModel getNode(int x, int y) {
		if (x >= gridLength || x < 0) {
			throw new IllegalArgumentException("x is outside the grid boundaries");
		}

		if (y >= gridHeight || y < 0) {
			throw new IllegalArgumentException("x is outside the grid boundaries");
		}

		return grid[x][y];
	}

	public FloodNodeModel getRootNode() {
		return getNode(0, 0);
	}

	public int getGridLength() {
		return gridLength;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	private static List<SquareColor> generateAvailableColors(int numColors) {
		List<SquareColor> availableColors = new ArrayList<SquareColor>();
		for (SquareColor color : SquareColor.values()) {
			availableColors.add(color);

			if (availableColors.size() == numColors) {
				break;
			}
		}
		return availableColors;
	}

	private void fillGridRandomly(List<SquareColor> availableColors) {
		// Assign each square a random color from the list of available colors
		Random rand = new Random();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new FloodNodeModel(availableColors.get(rand.nextInt(availableColors.size())));
			}
		}
	}

	private void assignNeighbours() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				// Same column
				if (i > 0) {
					grid[i][j].addNeighbour(grid[i - 1][j]);
				}
				if (i < grid.length - 1) {
					grid[i][j].addNeighbour(grid[i + 1][j]);
				}

				// Same row
				if (j > 0) {
					grid[i][j].addNeighbour(grid[i][j - 1]);
				}
				if (j < grid[i].length - 1) {
					grid[i][j].addNeighbour(grid[i][j + 1]);
				}
			}
		}
	}

	private void assignRootMap() {
		FloodNodeModel rootNode = getRootNode();
		SquareColor rootColor = rootNode.getSquareColor();

		rootNode.setAttachedToRootNode();

		propagateAttachment(rootNode, rootColor);
	}

	private void propagateAttachment(FloodNodeModel node, SquareColor rootColor) {
		for (FloodNodeModel neighbour : node.getNeighbouringNodes()) {
			if (!neighbour.isAttachedToRootNode() && neighbour.getSquareColor().equals(rootColor)) {
				neighbour.setAttachedToRootNode();
				propagateAttachment(neighbour, rootColor);
			}
		}
	}
}
