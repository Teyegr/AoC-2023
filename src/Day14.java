import util.InputProvider;

public class Day14 {
	static final boolean test = false;
	static final String[] lines = InputProvider.getInputLines(14, test);

	public static void main(String[] args) {
		part1();
	}
	
	static void part1() {
		long load = 0;
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length(); j++) {
				if (lines[i].charAt(j) == 'O') {
					int res = calculateLoad(i , j);
					load += res;
				}
			}
		}
		System.out.println(load);
	}
	
	static int calculateLoad(int i, int j) {
		if (i == 0) {
			return lines.length;
		}
		char c = lines[i - 1].charAt(j);
		int load;
		if (c == '#') {
			load = lines.length - i;
		} else if (c == 'O') {
			load = calculateLoad(i - 1, j) - 1;
		} else {
			load = calculateLoad(i - 1, j);
		}
		return load;
	}

}
