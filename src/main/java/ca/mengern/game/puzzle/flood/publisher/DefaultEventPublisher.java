package ca.mengern.game.puzzle.flood.publisher;

import java.util.ArrayList;
import java.util.List;

import ca.mengern.game.puzzle.flood.event.EventListener;

public abstract class DefaultEventPublisher<E> implements Publisher<E> {
	private final List<EventListener<E>> listeners = new ArrayList<>();

	@Override
	public void subscribe(EventListener<E> listener) {
		listeners.add(listener);
	}

	@Override
	public void publish(E event) {
		for (EventListener<E> listener : listeners) {
			listener.onEvent(event);
		}
	}
}
