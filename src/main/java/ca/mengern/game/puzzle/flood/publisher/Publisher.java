package ca.mengern.game.puzzle.flood.publisher;

import ca.mengern.game.puzzle.flood.event.EventListener;

public interface Publisher<E> {
	public void subscribe(EventListener<E> listener);

	public void publish(E event);
}