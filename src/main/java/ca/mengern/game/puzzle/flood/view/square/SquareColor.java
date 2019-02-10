package ca.mengern.game.puzzle.flood.view.square;

import javafx.scene.paint.Color;

public enum SquareColor {

	RED(Color.RED),
	BLUE(Color.BLUE),
	GREEN(Color.GREEN),
	ORANGE(Color.ORANGE),
	YELLOW(Color.YELLOW),
	PURPLE(Color.MAGENTA),
	BROWN(Color.web("brown")),
	PINK(Color.PINK),
	DARK_GREEN(Color.web("darkgreen")),
	CYAN(Color.CYAN);

	private Color color;

	private SquareColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
