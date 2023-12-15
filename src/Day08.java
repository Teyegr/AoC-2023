import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import util.InputProvider;

public class Day08 {
	
	static final String[] lines = InputProvider.getInputLines(8, false);

	public static void main(String[] args) throws IOException {
		partTwo();
	}
	
	static void partOne() {
		String pattern = parsePattern();
		Graph graph = parseGraph();
		int steps = graph.countSteps("AAA", pattern);
		System.out.println(steps);
	}
	
	static void partTwo() {
		String pattern = parsePattern();
		Graph graph = parseGraph();
		
		long steps = graph.countStepsSimultaniously(pattern);
		System.out.println(steps);
	}
	
	
	static String parsePattern() {
		return lines[0].trim();
	}
	
	static Graph parseGraph() {
		Graph graph = new Graph();
		
		for (int i = 2; i < lines.length; i++) {
			String line = lines[i];
			String node = line.substring(0, 3);
			String[] directions = {line.substring(7, 10), line.substring(12, 15)};
			
			graph.addNode(node, directions);
		}
		return graph;
	}

	static long lcm(long n1, long n2) {
		if (n1 == 0 || n2 == 0) {
	        return 0;
	    }
		
		long abs1 = Math.abs(n1);
	    long abs2 = Math.abs(n2);
	    long absHigh = Math.max(abs1, abs2);
	    long absLow = Math.min(abs1, abs2);
	    long lcm = absHigh;
	    while (lcm % absLow != 0) {
	        lcm += absHigh;
	    }
	    return lcm;
	}
	

	static class Graph {
		HashMap<String, String[]> map;
		
		Graph() {
			this.map = new HashMap<String, String[]>();
		}
		
		void addNode(String name, String[] destinations) {
			map.put(name, destinations);
		}
		
		String getLeftWay(String node) {
			return map.get(node)[0];
		}
		
		String getRightWay(String node) {
			return map.get(node)[1];
		}
		
		int countSteps(String node, String pattern) {
			int steps = 0;
			do {
				int i = steps % pattern.length();
				node = (pattern.charAt(i) == 'L') ? getLeftWay(node) : getRightWay(node);
				steps++;
			} while (node.charAt(2) != 'Z');
			
			return steps;
		}
		
		long countStepsSimultaniously(String pattern) {
			String[] nodes = findAllStartingNodes();
			long steps = countSteps(nodes[0], pattern);
			
			for (int i = 1; i < nodes.length; i++) {
				steps = lcm(steps, countSteps(nodes[i], pattern));
			}
			return steps;
		}
		
		String[] findAllStartingNodes() {
			ArrayList<String> nodes = new ArrayList<String>();
			for (String node: map.keySet()) {
				if (node.charAt(2) == 'A') {
					nodes.add(node);
				}
			}
			return nodes.toArray(new String[0]);
		}
		
		static boolean doAllEndWithZ(String[] nodes) {
			for (String node: nodes) {
				if (node.charAt(2) != 'Z') {
					return false;
				}
			}
			return true;
		}
		
		public String toString() {
			return String.format("Graph(%d)", map.size());
		}
	}

}
