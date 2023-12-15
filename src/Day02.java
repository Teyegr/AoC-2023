import util.InputProvider;

public class Day02 {

	public static void main(String[] args) throws Exception {
		String[] lines = InputProvider.getInputLines(2, false);
		int[] allowedMaxValues = { 12, 13, 14 };

		System.out.println(partOne(lines, allowedMaxValues));
		System.out.println(partTwo(lines));
	}

	private static int partOne(String[] lines, int[] allowedMaxValues) throws Exception {
		int sum = 0;

		for (String line : lines) {
			boolean valid = true;
			String[] sets = parseSets(line);
			int[] maxValues = getGameMaxValues(sets);
			for (int i = 0; i < maxValues.length && valid; i++) {
				if (maxValues[i] > allowedMaxValues[i]) {
					valid = false;
				}
			}
			if (valid) {
				sum += parseGameId(line);
			}
		}
		return sum;
	}
	
	private static int partTwo(String[] lines) throws Exception {
		int sum = 0;
		for (String line: lines) {
			String[] sets = parseSets(line);
			int[] maxValues = getGameMaxValues(sets);
			sum += maxValues[0] * maxValues[1] * maxValues[2];
		}
		return sum;
	}
	
	private static int parseGameId(String line) {
		return Integer.valueOf(line.split(":", 2)[0].substring(5)); 
	}
	
	private static String[] parseSets(String line) {
		return line.split(":", 2)[1].split(";");
	}

	private static int[] getGameMaxValues(String[] sets) throws Exception {
		int[] maxValues = {0, 0, 0};
		for (String set: sets) {
			int[] values = getSetValues(set);
			
			for (int i = 0; i < values.length; i++) {
				if (values[i] > maxValues[i]) {
					maxValues[i] = values[i];
				}
			}
		}
		return maxValues;
	}

	private static int[] getSetValues(String set) throws Exception {
		int[] values = { 0, 0, 0 };
		String[] parts = set.split(",");

		for (int i = 0; i < parts.length; i++) {
			String[] numberColor = parts[i].trim().split(" ", 2);
			int number = Integer.valueOf(numberColor[0]);
			String color = numberColor[1];

			switch (color) {
			case "red":
				values[0] = number;
				break;
			case "green":
				values[1] = number;
				break;
			case "blue":
				values[2] = number;
				break;
			default:
				throw new Exception("invalid color");
			}
		}
		return values;
	}

}
