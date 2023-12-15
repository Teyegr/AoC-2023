import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.InputProvider;

public class Day13 {
	
	static final boolean test = false;
	static final String[] lines = InputProvider.getInputLines(13, test);

	public static void main(String[] args) {
		partOne();
	}
	
	static void partOne() {
		String[][] patterns = parsePatterns();
		long sum = 0;
		
		for (String[] pattern : patterns) {
			long res = findReflection(pattern);
			sum += res;
		}
		System.out.println(sum);
	}
	
	static long findReflection(String[] pattern) {
		long result = findVerticalReflection(pattern);
		if (result == -1) {
			result = findHorizontalReflection(pattern);
		}
		if (result == -1) {
			System.out.println("Pattern has not reflection");
		}
		return result;
	}
	
	static long findVerticalReflection(String[] pattern) {
		for (int i = 0; i < pattern[0].length() - 1; i++) {
			if (isVerticalReflected(pattern, i)) {
				return i + 1;
			}
		}
		return -1;
	}
	
	static boolean isVerticalReflected(String[] pattern, int i) {
		int left = i;
		int right = i + 1;
		while (left >= 0 && right < pattern[0].length()) {
			for (String line: pattern) {
				if (line.charAt(left) != line.charAt(right)) {
					return false;
				}
			}
			left--;
			right++;
		}
		return true;
	}
	
	static long findHorizontalReflection(String[] pattern) {
		for (int i = 0; i < pattern.length - 1; i++) {
			if (isHorizontalReflected(pattern, i)) {
				return (i + 1) * 100;
			}
		}
		return -1;
	}
	
	static boolean isHorizontalReflected(String[] pattern, int i) {
		int top = i;
		int bot = i + 1;
		while (top >= 0 && bot < pattern.length) {
			if (!pattern[top].equals(pattern[bot])) {
				return false;
			}
			top--;
			bot++;
		}
		return true;
	}
	
	static String[][] parsePatterns() {
		List<String[]> patterns = new ArrayList<String[]>();
		int i = 0;
		for (int j = 0; j < lines.length; j++) {
			if  (lines[j].length() == 0) {
				String[] pattern = Arrays.copyOfRange(lines, i, j);
				patterns.add(pattern);
				i = j + 1;
			}
		}
		String[] pattern = Arrays.copyOfRange(lines, i, lines.length);
		patterns.add(pattern);
		return patterns.toArray(new String[0][0]);
	}

	
}
