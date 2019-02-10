package ca.mengern.game.puzzle.flood.event;

import ca.mengern.game.puzzle.flood.model.node.FloodNodeModel;

public class NodeClickEvent {

	private final FloodNodeModel clickedNode;

	public NodeClickEvent(FloodNodeModel clickedNode) {
		super();
		this.clickedNode = clickedNode;
	}

	public FloodNodeModel getClickedNode() {
		return clickedNode;
	}
}
