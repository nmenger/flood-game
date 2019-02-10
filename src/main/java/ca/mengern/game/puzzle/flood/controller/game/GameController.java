package ca.mengern.game.puzzle.flood.controller.game;

import ca.mengern.game.puzzle.flood.event.EventListener;
import ca.mengern.game.puzzle.flood.event.NodeClickEvent;
import ca.mengern.game.puzzle.flood.model.game.FloodGameModel;
import ca.mengern.game.puzzle.flood.model.grid.FloodGridModel;
import ca.mengern.game.puzzle.flood.model.node.FloodNodeModel;
import ca.mengern.game.puzzle.flood.view.square.SquareColor;

public class GameController implements EventListener<NodeClickEvent> {

	private final FloodGameModel gameModel;
	private final FloodGridModel gridModel;

	public GameController(FloodGridModel gridModel) {
		this.gridModel = gridModel;

		gameModel = new FloodGameModel();

		for (int x = 0; x < gridModel.getGridLength(); x++) {
			for (int y = 0; y < gridModel.getGridHeight(); y++) {
				gridModel.getNode(x, y).subscribeToNodeClickEvent(this);
			}
		}
	}

	@Override
	public void onEvent(NodeClickEvent event) {
		handleNodeClicked(event.getClickedNode());
	}

	private void handleNodeClicked(FloodNodeModel clickedNode) {
		if (clickedNode.isAttachedToRootNode()) {
			System.out.println("Clicked node is attached to the root map. Nothing happens");
			return;
		}

		if (!isClickedColorBesideRootMap(clickedNode.getSquareColor())) {
			System.out.println("Clicked color is not next to the flood. Nothing happens");
			return;
		}

		System.out.println("New color is " + clickedNode.getSquareColor());

		propagateClickedColor(clickedNode.getSquareColor(), gridModel.getRootNode());

		gameModel.incrementNumberOfMoves();

		System.out.println("Number of moves: " + gameModel.getNumberMoves());

		if (checkSolved()) {
			System.out.println("SOLVED");
		}
	}

	private boolean isClickedColorBesideRootMap(SquareColor clickedColor) {
		for (int x = 0; x < gridModel.getGridLength(); x++) {
			for (int y = 0; y < gridModel.getGridHeight(); y++) {
				FloodNodeModel checkNode = gridModel.getNode(x, y);

				if (checkNode.getSquareColor().equals(clickedColor) && !checkNode.isAttachedToRootNode()) {
					for (FloodNodeModel neighbour : checkNode.getNeighbouringNodes()) {
						if (neighbour.isAttachedToRootNode()) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	private void propagateClickedColor(SquareColor newColor, FloodNodeModel fromNode) {
		fromNode.setSquareColor(newColor);
		fromNode.setAttachedToRootNode();

		for (FloodNodeModel neighbour : fromNode.getNeighbouringNodes()) {
			// Propagate to root map neighbours
			if (neighbour.isAttachedToRootNode() && !neighbour.getSquareColor().equals(newColor)) {
				propagateClickedColor(newColor, neighbour);
			}

			// Propagate to new nodes that were of the same colour as the clicked color
			if (!neighbour.isAttachedToRootNode() && neighbour.getSquareColor().equals(newColor)) {
				propagateClickedColor(newColor, neighbour);
			}
		}
	}

	private boolean checkSolved() {
		for (int x = 0; x < gridModel.getGridLength(); x++) {
			for (int y = 0; y < gridModel.getGridHeight(); y++) {
				if (!gridModel.getNode(x, y).isAttachedToRootNode()) {
					return false;
				}
			}
		}

		return true;
	}
}
