import java.util.Arrays;

import util.InputProvider;

public class Day04 {
	
	public static void main(String[] args) {
		boolean test = false;
		String[] lines = InputProvider.getInputLines(4, test);
		partOne(lines, test);
		partTwo(lines, test);
	}
	
	private static void partTwo(String[] lines, boolean test) {
		int[] cardsCount = new int[lines.length];
		Arrays.fill(cardsCount, 1);
		
		for (int i = 0; i < lines.length; i++) {
			processCard(lines[i], cardsCount, i, test);
		}
		 int sum = Arrays.stream(cardsCount).sum();
		 System.out.println(sum);
		
	}
	
	private static void processCard(String line, int[] cardsCount, int card, boolean test) {
		int cardAmmount = cardsCount[card];
		int matches = parseMatches(line, test);
		for (int i = 1; i < matches + 1 && card + i < cardsCount.length; i++) {
			cardsCount[card + i] = cardsCount[card + i] + cardAmmount;
		}
	}
	
	private static void partOne(String[] lines, boolean test) {
		int sum = 0;
		for (String line: lines) {
			sum += parsePoints(line, test);
		}
		System.out.println(sum);
	}
	
	private static int parseMatches(String line, boolean test) {
		String content = parseContent(line);
		int winningCount = test ? 5 : 10;
		int guessingCount = test ? 8 : 25;
		int splitIndex = content.indexOf('|');
		int[] winningNumbers = parseNumbers(content.substring(0, splitIndex - 1), winningCount);
		int[] guessingNumbers = parseNumbers(content.substring(splitIndex + 1), guessingCount);
		
		int points = 0;
		for (int i = 0; i < winningNumbers.length; i++) {
			for (int j = 0; j < guessingNumbers.length; j++) {
				if (guessingNumbers[j] == winningNumbers[i]) {
					points++;
				}
			}
		}
		return points;
	}
	
	private static int parsePoints(String line, boolean test) {
		int matches = parseMatches(line, test);
		return (matches == 0) ? 0 : (int) Math.pow(2, matches);
	}
	
	private static String parseContent(String line) {
		return line.split(":", 2)[1];
	}
	
	private static int[] parseNumbers(String content, int limit) {
		int[] numbers = new int[limit];
		content = content.trim();
		String[] numberStrings = content.replace("  ", " ").split(" ", limit);
		for (int i = 0; i < limit; i++) {
			numbers[i] = Integer.valueOf(numberStrings[i]);
		}
		return numbers;
	}
	
}
