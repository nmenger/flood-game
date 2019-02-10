package ca.mengern.game.puzzle.flood.view.square;

import ca.mengern.game.puzzle.flood.event.ColorChangeEvent;
import ca.mengern.game.puzzle.flood.event.EventListener;
import ca.mengern.game.puzzle.flood.model.node.FloodNodeModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class FloodSquare extends Rectangle implements EventListener<ColorChangeEvent> {

	public FloodSquare(double squareSize, int squareBorderWidth, FloodNodeModel nodeModel) {
		super(squareSize, squareSize, nodeModel.getSquareColor().getColor());

		nodeModel.subscribeToColorChangeEvent(this);

		setStrokeType(StrokeType.OUTSIDE);
		setStroke(Color.web("black"));
		setStrokeWidth(squareBorderWidth);

		setOnMouseClicked((e) -> nodeModel.nodeClicked());
	}

	@Override
	public void onEvent(ColorChangeEvent event) {
		setFill(event.getNewColor().getColor());
	}
}
