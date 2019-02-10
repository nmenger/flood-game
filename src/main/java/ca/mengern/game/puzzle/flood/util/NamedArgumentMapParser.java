package ca.mengern.game.puzzle.flood.util;

import java.util.HashMap;
import java.util.Map;

public class NamedArgumentMapParser {

	public static Map<String, String> parseArgumentsIntoMap(String[] args) {
		// Must be an even amount of arguments to specify
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException("Odd number of passed in arguments");
		}

		Map<String, String> argumentMap = new HashMap<String, String>();

		int i = 0;

		while (i < args.length - 1) {
			String key = args[i];
			String value = args[i + 1];

			argumentMap.put(key, value);
			i += 2;
		}

		return argumentMap;
	}
}
