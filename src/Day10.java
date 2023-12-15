import java.util.ArrayList;
import java.util.Arrays;
import util.InputProvider;


public class Day10 {
	static final boolean test = false;
	static final char[][] grid = parseGrid();

	public static void main(String[] args) {
		task2();
	}
	
	static void task1() {
		int i = 1;
		Integer[] start = findStart();
		Integer[][] connectedPipes = findConnectedPipes(start);
		Integer[] pos1 = connectedPipes[0];
		Integer[] pos2 = connectedPipes[1];
		Integer[] prev1 = start;
		Integer[] prev2 = start;
		do {
			i++;
			Integer[] temp1 = pos1;
			Integer[] temp2 = pos2;
			pos1 = findNextPipe(prev1, pos1);
			pos2 = findNextPipe(prev2, pos2);
				
			prev1 = temp1;
			prev2 = temp2;
			
		} while(!Arrays.equals(pos1, pos2) && !Arrays.equals(pos1, prev2));
		System.out.println(i);
	}
	
	static void task2() {
		ArrayList<Integer[]> pipes = new ArrayList<Integer[]>();
		Integer[] start = findStart();
		Integer[][] connectedPipes = findConnectedPipes(start);
		pipes.add(start);
		Integer[] pos1 = connectedPipes[0];
		Integer[] pos2 = connectedPipes[1];
		pipes.add(pos1);
		pipes.add(pos2);
		Integer[] prev1 = start;
		Integer[] prev2 = start;
		do {
			Integer[] temp1 = pos1;
			Integer[] temp2 = pos2;
			pos1 = findNextPipe(prev1, pos1);
			pos2 = findNextPipe(prev2, pos2);
			pipes.add(pos1);
			
			if (!Arrays.equals(pos1, pos2)) {
				pipes.add(pos2);
			}
			prev1 = temp1;
			prev2 = temp2;
			
		} while(!Arrays.equals(pos1, pos2) && !Arrays.equals(pos1, prev2));
		
		int s = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Integer[] pos = new Integer[] {i, j};
				if (!isPipeofLoop(pipes, pos)) {
					if (countPipesPassed(pipes, pos) % 2 == 1) {
						s++;
					}
				}
			}
		}
		
		System.out.println(s);
	}
	
	static boolean isPipeofLoop(ArrayList<Integer[]> pipes, Integer[] pos) {
		for (Integer[] pipe : pipes) {
			if (Arrays.equals(pipe, pos)) {
				return true;
			}
		}
		return false;
	}
	
	static int countPipesPassed(ArrayList<Integer[]> pipes, Integer[] pos) {
		int passed = 0;
		int row = pos[0];
		int col = pos[1];
		for (int i = 0; i < col; i++) {
			char c = grid[row][i];
			if (c == 'J' || c == '|' || c == 'L') {
				if (isPipeofLoop(pipes, new Integer[] {row, i} )) {
					passed++;
				}
			}
		}
		return passed;
	}
	
	static Integer[] findStart() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 'S') {
					return new Integer[] {i, j};
				}
			}
		}
		return null;
	}
	
	static boolean isValidPos(Integer[] start) {
		return (start[0] >= 0 && start[1] >= 0 && start[0] < grid.length && start[1] < grid[0].length);
	}
	
	static void show(int[] pos) {
		System.out.println(String.format("Pos(%d, %d)", pos[0], pos[1]));
	}
	
	static char get(Integer[] start) {
		if (!isValidPos(start)) {
			return 0;
		}
		return grid[start[0]][start[1]];
	}
	
	static boolean isConnected(Integer[] start ,Integer[] other, int dir) {
		char c = get(start);
		char o = get(other);
		
		final String northChars = "|LJS";
		final String eastChars = "-LFS";
		final String southChars = "|7FS";
		final String westChars = "-J7S";
		
		if (dir == 0) {
			return (northChars.indexOf(c) != -1 && southChars.indexOf(o) != -1);
		} 
		if (dir == 1) {
			return (eastChars.indexOf(c) != -1 && westChars.indexOf(o) != -1);
		} 
		if (dir == 2) {
			return (southChars.indexOf(c) != -1 && northChars.indexOf(o) != -1);
		} 
		if (dir == 3) {
			return (westChars.indexOf(c) != -1 && eastChars.indexOf(o) != -1);
		}
		return false;
	}
	
	static Integer[][] findConnectedPipes(Integer[] start) {
		int x = start[0];
		int y = start[1];
		int i = 0;
		
		Integer[][] pipes = new Integer[2][2];
		Integer[] north = new Integer[] {x - 1, y};
		Integer[] east = new Integer[] {x, y + 1};
		Integer[] south = new Integer[] {x + 1, y};
		Integer[] west = new Integer[] {x, y - 1};
		
		if (isConnected(start, north, 0)) {
			pipes[i] = north;
			i++;
		}
		
		if (isConnected(start, east, 1)) {
			pipes[i] = east;
			i++;
		}
		
		if (isConnected(start, south, 2)) {
			pipes[i] = south;
			i++;
		}
		
		if (isConnected(start, west, 3)) {
			pipes[i] = west;
			i++;
		}
		
		return pipes;
	}
	
	static Integer[] findNextPipe(Integer[] prev, Integer[] pos) {
		Integer[][] posArray = findConnectedPipes(pos);
		Integer[] pos1 = posArray[0];
		Integer[] pos2 = posArray[1];
		if (!Arrays.equals(prev, pos1)) {
			return pos1;
		}
		
		if (!Arrays.equals(prev, pos2)) {
			return pos2;
		}
		return null;
	}

	static char[][] parseGrid() {
		String[] lines = InputProvider.getInputLines(10, test);
		char[][] grid = new char[lines.length][lines[0].length()];
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length(); j++) {
				grid[i][j] = lines[i].charAt(j);
			}
		}
		return grid;
	}
}
