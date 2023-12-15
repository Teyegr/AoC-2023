import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import util.InputProvider;

public class Day11 {
	
	static final boolean test = false;
	static String[] lines = InputProvider.getInputLines(11, test);

	public static void main(String[] args) {
		part2();
	}
	
	static void part1() {
		int fak = 2;
		List<Integer[]> galaxies = findAllGalaxies();
		boolean[][] galaxyLookup = generateGalaxyLookup(galaxies);
		long sum = 0;
		for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Integer[] galaxie1 = galaxies.get(i);
                Integer[] galaxie2 = galaxies.get(j);
                sum += shortestPath(galaxie1, galaxie2, fak, galaxyLookup);
            }
        }
		System.out.println(sum);
	}
	
	static void part2() {
		int fak = 1000000;
		List<Integer[]> galaxies = findAllGalaxies();
		boolean[][] galaxyLookup = generateGalaxyLookup(galaxies);
		long sum = 0;
		for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Integer[] galaxie1 = galaxies.get(i);
                Integer[] galaxie2 = galaxies.get(j);
                sum += shortestPath(galaxie1, galaxie2, fak, galaxyLookup);
            }
        }
		System.out.println(sum);
	}

	static List<Integer[]> findAllGalaxies() {
		List<Integer[]> posList = new ArrayList<Integer[]>();
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length(); j++) {
				if (lines[i].charAt(j) == '#') {
					posList.add(new Integer[] {i, j});
				}
			}
		}
		return posList;
	}
	
	static boolean[][] generateGalaxyLookup(List<Integer[]> galaxies) {
		boolean[] rowLookup = new boolean[lines.length];
		boolean[] colLookup = new boolean[lines[0].length()];
		
		Arrays.fill(rowLookup, false);
		Arrays.fill(colLookup, false);
		
		for (Integer[] pos: galaxies) {
			rowLookup[pos[0]] = true;
			colLookup[pos[1]] = true;
		}
		return new boolean[][] {rowLookup, colLookup};
	}

	static long shortestPath(Integer[] pos, Integer[] end, int fak, boolean[][] galaxyLookup) {
		int i1 = Math.min(pos[0], end[0]);
		int i2 = Math.max(pos[0], end[0]);
		int j1 = Math.min(pos[1], end[1]);
		int j2 = Math.max(pos[1], end[1]);
		long path = 0;
		
		for (int i = i1; i < i2; i++) {
			path += galaxyLookup[0][i] ? 1 : fak;
		}
		for (int j = j1; j < j2; j++) {
			path += galaxyLookup[1][j] ? 1 : fak;
		}
		return path;
	}
	
}
