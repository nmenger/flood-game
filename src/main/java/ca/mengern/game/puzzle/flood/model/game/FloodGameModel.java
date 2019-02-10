package ca.mengern.game.puzzle.flood.model.game;

public class FloodGameModel {

	private int numberMoves;

	public FloodGameModel() {
	}

	public void incrementNumberOfMoves() {
		numberMoves++;
	}

	public int getNumberMoves() {
		return numberMoves;
	}
}
