import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import util.InputProvider;

public class Day07 {

	
	static final String[] lines = InputProvider.getInputLines(7, false);
	static final HashMap<Character, Integer> strengthMap = new HashMap<Character, Integer>() {{
		put('A', 12);
		put('K', 11);
		put('Q', 10);
		put('T', 8);
		put('9', 7);
		put('8', 6);
		put('7', 5);
		put('6', 4);
		put('5', 3);
		put('4', 2);
		put('3', 1);
		put('2', 0);
		put('J', -1);
	}};
	
	public static void main(String[] args) {
		partOne();
	}
	
	//0 -> High Card
	//1 -> Pair
	//2 -> 2 Pairs
	//3 -> three of a kind
	//4 -> full house
	//5 -> four of a kind
	//6 -> five of a kind
	
	static void partOne() {
		Hand[] hands = parseHands();
		Arrays.sort(hands, new Comparator<Hand>() {

			@Override
			public int compare(Hand h1, Hand h2) {
				if (h1.value > h2.value) {
					return 1;
				} 
				if (h2.value > h1.value) {
					return -1;
				}
				
				for (int i = 0; i < h1.cards.length; i++) {
					int temp1 = strengthMap.get(h1.cards[i]);
					int temp2 = strengthMap.get(h2.cards[i]);
					
					if (temp1 > temp2) {
						return 1;
					}
					
					if (temp2 > temp1) {
						return -1;
					}
				}
				return 0;
			}
		});
		
		int totalWinnings = 0;
		for (int i = 0; i < hands.length; i++) {
			totalWinnings += (i + 1) * hands[i].bid;
		}
		
		for (Hand hand: hands) {
			System.out.println(hand);
		}
		
		System.out.println(totalWinnings);
	}
	
	static int calculateBestValue(HashMap<Character, Integer> map) {
		int jokers = map.get('J');
		if (map.size() == 1 || map.size() == 2) {
			return 6;
		}
		if (map.size() == 4) {
			return 3;
		}
		if (map.size() == 5) {
			return 1;
		}
		if (jokers == 3 || jokers == 2) {
			return 5;
		}
		
		if (map.containsValue(3)) {
			return 5;
		}
		return 4;
	}
	
	static Hand[] parseHands() {
		Hand[] hands = new Hand[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			String cardsRaw = lines[i].substring(0, 5);
			String bidRaw = lines[i].substring(5);
			char[] cards = cardsRaw.toCharArray();
			int bid = Integer.valueOf(bidRaw.trim());
			hands[i] = new Hand(cards, bid);
		}
		return hands;
	}
	
	static class Hand {
		char[] cards;
		int bid;
		int value;
		
		Hand(char[] cards, int bid) {
			this.cards = cards;
			this.bid = bid;
			value = calculateValue();
		}
		
		private int calculateValue() {
			HashMap<Character, Integer> map = new HashMap<>();
			
			for (char c: cards) {
				if (map.get(c) == null) {
					map.put(c, 1);
				} else {
					map.put(c, map.get(c) + 1);
				}
			}
			if (map.containsKey('J')) {
				return calculateBestValue(map);
			}
			
			if (map.size() == 1) {
				return 6;
			}
			if (map.size() == 2) {
				if (map.containsValue(4)) {
					return 5;
				}
				
				return 4;
			}
			if (map.containsValue(3)) {
				return 3;
			}
			
			if (map.size() == 4) {
				return 1;
			}
			
			if (map.size() == 5) {
				return 0;
			}
			return 2;
		}
		
		public String toString() {
			return String.format("Hand(%s, %d, %d)", String.valueOf(cards), bid, value);
		}
	}
}
