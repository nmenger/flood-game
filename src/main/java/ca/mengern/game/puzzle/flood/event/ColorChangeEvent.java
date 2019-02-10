package ca.mengern.game.puzzle.flood.event;

import ca.mengern.game.puzzle.flood.view.square.SquareColor;

public class ColorChangeEvent {

	private final SquareColor newColor;

	public ColorChangeEvent(SquareColor newColor) {
		super();
		this.newColor = newColor;
	}

	public SquareColor getNewColor() {
		return newColor;
	}
}
