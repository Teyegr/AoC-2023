import java.util.ArrayList;
import java.util.Arrays;

import util.InputProvider;

public class Day11 {
	
	static final boolean test = true;
	static char[][] universe = parseUniverse();

	public static void main(String[] args) {
		part2();
	}
	
	static void part1() {
		char[][] expUniverse = expandUniverse();
		Pos[] galaxies = findAllGalaxies(expUniverse);
		
		int sum = 0;
		for (Pos galaxie1 : galaxies) {
			for (Pos galaxie2 : galaxies) {
				if (galaxie1.equals(galaxie2)) {continue;};
				sum += calculateShortestPath(galaxie1, galaxie2);
			}
		}
		System.out.println(sum/2);
	}
	
	static void part2() {
		boolean[] expandedRows = new boolean[universe.length];
		boolean[] expandedCols = new boolean[universe[0].length];
		Pos[] poses = extractData(expandedRows, expandedCols);
		
		int sum = 0;
		for (int i = 0; i < poses.length - 1; i++) {
			for (int j = i + 1; j < poses.length; j++) {
				sum += expandedPath(poses[i], poses[j], expandedRows, expandedCols, 10);
			}
		}
		System.out.println(sum);
	}
	
	static int expandedPath(Pos pos1, Pos pos2, boolean[] expandedRows, boolean[] expandedCols, int faktor) {
		Pos pos = new Pos(pos1.x, pos1.y);
		int x = (pos1.x <= pos2.x) ? 1: -1;
		int y = (pos1.y <= pos2.y) ? 1: -1;
		
		int sum = 0;
		for (; pos.x != pos2.x; pos.x += x) {
			sum += isExtended(pos, expandedRows, expandedCols) ? faktor : 1;
		}
		for (; pos.y != pos2.y; pos.y += y) {
			sum += isExtended(pos, expandedRows, expandedCols) ? faktor : 1;
		}
		return sum;
	}
	
	static boolean isExtended(Pos pos, boolean[] expandedRows, boolean[] expandedCols) {
		return (expandedRows[pos.x] || expandedCols[pos.y]);
	}
	
	static Pos[] extractData(boolean[] expandedRows, boolean[] expandedCols) {
		Arrays.fill(expandedRows, true);
		Arrays.fill(expandedCols, true);
		
		ArrayList<Pos> posList = new ArrayList<Day11.Pos>();
		for (int i = 0; i < universe.length; i++) {
			for (int j = 0; j < universe[i].length; j++) {
				if (universe[i][j] == '#') {
					expandedRows[i] = false;
					expandedCols[j] = false;
					posList.add(new Pos(i, j));
				}
			}
		}
		Pos[] pos = new Pos[posList.size()];
		for (int i = 0; i < posList.size(); i++) {
			pos[i] = posList.get(i);
		}
		return pos;
	}
	
	static int calculateShortestPath(Pos pos1, Pos pos2) {
		return Math.abs(pos1.x - pos2.x) + Math.abs(pos1.y - pos2.y);
	}
	
	static Pos[] findAllGalaxies(char[][] universe) {
		ArrayList<Pos> posList = new ArrayList<Day11.Pos>();
		for (int i = 0; i < universe.length; i++) {
			for (int j = 0; j < universe[i].length; j++) {
				if (universe[i][j] == '#') {
					posList.add(new Pos(i, j));
				}
			}
		}
		Pos[] pos = new Pos[posList.size()];
		for (int i = 0; i < posList.size(); i++) {
			pos[i] = posList.get(i);
		}
		return pos;
	}
	
	static char[][] parseUniverse() {
		String[] lines = InputProvider.getInputLines(11, test);
		char[][] universe = new char[lines.length][lines[0].length()];
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length(); j++) {
				universe[i][j] = lines[i].charAt(j);
			}
		}
		return universe;
	}
	
	static char[][] expandUniverse() {
		int[] expandableRows = new int[universe.length];
		int[] expandableCols = new int[universe[0].length];
		
		Arrays.fill(expandableRows, 1);
		Arrays.fill(expandableCols, 1);
		
		for (int i = 0; i < universe.length; i++) {
			for (int j = 0; j < universe[i].length; j++) {
				if (universe[i][j] == '#') {
					expandableRows[i] = 0;
					expandableCols[j] = 0;
				}
			}
		}
		return expandHorizontal(expandVertical(universe, expandableRows), expandableCols);
	}
	
	static char[][] expandVertical(char[][] universe, int[] expandables) {
		int rows = universe.length + (int) Arrays.stream(expandables).filter(value -> value == 1).count();
		char[][] expandedUniverse = new char[rows][universe[0].length];
		int row = 0;
		for (int i = 0; i < universe.length; i++) {
			for (int j = 0; j < universe[0].length; j++) {
				expandedUniverse[row][j] = universe[i][j];
			}
			row++;
			if (expandables[i] == 1) {
				for (int j = 0; j < universe[0].length; j++) {
					expandedUniverse[row][j] = universe[i][j];
				}
				row++;
			}
		}
		return expandedUniverse;
	}
	
	static char[][] expandHorizontal(char[][] universe, int[] expandables) {
		int cols = universe[0].length + (int) Arrays.stream(expandables).filter(value -> value == 1).count();
		char[][] expandedUniverse = new char[universe.length][cols];
		int col = 0;
		for (int i = 0; i < universe[0].length; i++) {
			for (int j = 0; j < universe.length; j++) {
				expandedUniverse[j][col] = universe[j][i];
			}
			col++;
			if (expandables[i] == 1) {
				for (int j = 0; j < universe.length; j++) {
					expandedUniverse[j][col] = universe[j][i];
				}
				col++;
			}
		}
		return expandedUniverse;
	}

	static class Pos {
		int x;
		int y;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public boolean equals(Pos other) {
			return (x == other.x && y == other.y);
		}
		
		public String toString() {
			return String.format("Pos(%d, %d)", x, y);
		}
	}
}
