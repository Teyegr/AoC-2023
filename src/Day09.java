import util.InputProvider;

public class Day09 {
	static boolean test = false;
	static String[] lines = InputProvider.getInputLines(9, test);
	
	
	public static void main(String[] args) {
		task2();
	}
	
	static void task1() {
		int sum = 0;
		for (String line: lines) {
			int[] values = parseLine(line);
			sum += calculateNextValue(values);
		}
		System.out.println(sum);
	}

	static void task2() {
		int sum = 0;
		for (String line: lines) {
			int[] values = parseLine(line);
			sum += calculatePreviousValue(values);
		}
		System.out.println(sum);
	}
	
	static int calculatePreviousValue(int[] values) {
		if (allZero(values)) {
			return 0;
		}
		return values[0] - calculatePreviousValue(calculateDeltas(values));
	}
	
	static int calculateNextValue(int[] values) {
		if (allZero(values)) {
			return 0;
		}
		return values[values.length - 1] + calculateNextValue(calculateDeltas(values));
	}
	
	static int[] calculateDeltas(int[] values) {
		int[] deltas = new int[values.length - 1];
		
		for (int i = 0; i < deltas.length; i++) {
			deltas[i] = values[i + 1] - values[i];
		}
		return deltas;
	}
	
	static boolean allZero(int[] values) {
		for (int i : values) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}
	
	static int[] parseLine(String line) {
		String[] valuesRaw = line.split(" ");
		int[] values = new int[valuesRaw.length];
		
		for (int i = 0; i < valuesRaw.length; i++) {
			values[i] = Integer.valueOf(valuesRaw[i]);
		}
		return values;
	}

}
 