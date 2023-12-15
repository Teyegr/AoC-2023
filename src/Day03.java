import java.util.ArrayList;

import util.InputProvider;

public class Day03 {

	public static void main(String[] args) {
		String[] lines =  InputProvider.getInputLines(3, false);
		partOne(lines);
		partTwo(lines);
	}
	
	private static void partTwo(String[] lines) {
		int row = 0;
		int column = 0;
		int[] pos;
		int sum = 0;
		
		do {
			pos = findNextStar(lines, row, column);
			ArrayList<Integer> numbers = findNumbersAround(lines, pos[0], pos[1]);
			if (numbers.size() == 2) {
				sum += numbers.get(0) * numbers.get(1);
			}
			
			
			row = pos[0];
			column = pos[1] + 1;
		} while (pos[1] != -1);
		
		System.out.println(sum);
	}
	
	//return {row, column}
	private static int[] findNextStar(String[] lines, int row, int column) {
		int index = lines[row].substring(column).indexOf('*');
		index = (index == -1) ? -1 : index + column;
		while (row + 1 < lines.length && index == -1) {
			row++;
			index = lines[row].indexOf('*');
		}
		return new int[] {row, index};
	}
	
	private static ArrayList<Integer> findNumbersAround(String[] lines, int row, int column) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for (int i = row - 1; i < row + 2; i++) {
			for (int j = column - 1; j < column + 2; j++) {
				if (i == row && j == column) {continue;};
				if (i < 0 || i >= lines.length || j < 0 || j >= lines[i].length()) {continue;};
				
				if (isDigit(lines[i].charAt(j))) {
					if (i == row) {
						numbers.add(parseNumber(lines, i, j));
					} else if (j == column) {
						numbers.add(parseNumber(lines, i, j));
						break;
					} else if(!isDigit(lines[i].charAt(column))) {
						numbers.add(parseNumber(lines, i, j));
					} else {
					}
				}
			}
		}
		
		return numbers;
	}
	
	private static int parseNumber(String[] lines, int row , int column) {
		String line = lines[row];
		int left = column - 1;
		int right = column + 1;
		while (left >= 0 && isDigit(line.charAt(left))) {
			left--;
		}
		
		while (right < line.length() && isDigit(line.charAt(right))) {
			right++;
		}
		left++;
		
		return Integer.valueOf(lines[row].substring(left, right));
	}
	
	//int[] number = {row, column, length};
	private static void partOne(String[] lines) {
		int[] nextNumber;
		int row = 0;
		int column = 0;
		int sum = 0;
		while ((nextNumber = findNextNumber(lines, row, column)) != null) {
			if (hasAdjacentSymbol(lines, nextNumber)) {
				sum += getNumber(lines, nextNumber);
			}
			
			row = nextNumber[0];
			column = nextNumber[1] + nextNumber[2];
		}
		
		System.out.println(sum);
	}
	
	private static int[] findNextNumber(String[] lines, int row, int column) {
		for (int i = row; i < lines.length; i++) {
			for (int j = column; j < lines[i].length(); j++) {
				if (isDigit(lines[i].charAt(j))) {
					return parseNumberData(lines, i, j);
				}
			}
			column = 0;
		}
		return null;
	}
	
	private static int[] parseNumberData(String[] lines, int row, int column) {
		for (int i = column + 1; i < lines[row].length(); i++) {
			if (!isDigit(lines[row].charAt(i))) {
				return new int[] {row, column, i - column};
			}
		}
		return new int[] {row, column, lines[row].length() - column};
	}

	private static boolean hasAdjacentSymbol(String[] lines, int[] number) {
		int row = number[0];
		int column = number[1];
		int length = number[2];
		for (int i = row - 1; i < row + 2; i++) {
			if (i < 0) {continue;};
			if (i >= lines.length) {continue;}
			
			for (int j = column - 1; j < column + length + 1; j++) {
				if (j < 0) {continue;};
				if (j >= lines[i].length()) {continue;};
				if (isSymbol(lines[i].charAt(j))) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isSymbol(char c) {
		return (!isDot(c) && !(isDigit(c)));
	}
	
	private static boolean isDot(char c) {
		return (c == 46);
	}
	
	private static boolean isDigit(char c) {
		return (c > 47 && c < 58);
	}
	
	private static int getNumber(String[] lines, int[] number) {
		int row = number[0];
		int column = number[1];
		int length = number[2];
		String string = lines[row].substring(column, column + length);
		return Integer.valueOf(string);
	}
}