package ca.mengern.game.puzzle.flood.model.node;

import java.util.ArrayList;
import java.util.List;

import ca.mengern.game.puzzle.flood.event.ColorChangeEvent;
import ca.mengern.game.puzzle.flood.event.EventListener;
import ca.mengern.game.puzzle.flood.event.NodeClickEvent;
import ca.mengern.game.puzzle.flood.publisher.DefaultEventPublisher;
import ca.mengern.game.puzzle.flood.view.square.SquareColor;

public class FloodNodeModel {

	private final List<FloodNodeModel> neighbouringNodes = new ArrayList<FloodNodeModel>();

	private final ColorChangeEventPublisher colorChangeEventPublisher = new ColorChangeEventPublisher();
	private final NodeClickEventPublisher nodeClickEventPublisher = new NodeClickEventPublisher();

	private boolean attachedToRootNode;
	private SquareColor squareColor;

	public FloodNodeModel(SquareColor squareColor) {
		this.squareColor = squareColor;
	}

	public void nodeClicked() {
		nodeClickEventPublisher.publish(new NodeClickEvent(this));
	}

	public void addNeighbour(FloodNodeModel node) {
		neighbouringNodes.add(node);
	}

	public List<FloodNodeModel> getNeighbouringNodes() {
		return new ArrayList<FloodNodeModel>(neighbouringNodes);
	}

	public void setAttachedToRootNode() {
		attachedToRootNode = true;
	}

	public boolean isAttachedToRootNode() {
		return attachedToRootNode;
	}

	public void setSquareColor(SquareColor squareColor) {
		this.squareColor = squareColor;
		colorChangeEventPublisher.publish(new ColorChangeEvent(squareColor));
	}

	public SquareColor getSquareColor() {
		return squareColor;
	}

	public void subscribeToColorChangeEvent(EventListener<ColorChangeEvent> listener) {
		colorChangeEventPublisher.subscribe(listener);
	}

	public void subscribeToNodeClickEvent(EventListener<NodeClickEvent> listener) {
		nodeClickEventPublisher.subscribe(listener);
	}

	private final class ColorChangeEventPublisher extends DefaultEventPublisher<ColorChangeEvent> {
	};

	private final class NodeClickEventPublisher extends DefaultEventPublisher<NodeClickEvent> {
	};
}
