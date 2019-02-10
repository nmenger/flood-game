package ca.mengern.game.puzzle.flood.model.FloodGridModel;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import ca.mengern.game.puzzle.flood.model.grid.FloodGridModel;

public class TestFloodGridModel {

	@Test
	void testAssignNeighbours() {
		FloodGridModel gridModel = FloodGridModel.generateFloodMap(3, 3, 2); // 9 nodes

		int numNeighbours = 0;
		for (int x = 0; x < gridModel.getGridLength(); x++) {
			for (int y = 0; y < gridModel.getGridHeight(); y++) {

				numNeighbours += gridModel.getNode(x, y).getNeighbouringNodes().size();
			}
		}

		assertTrue("This grid should have 24 neighbours", numNeighbours == 24);
	}

}
