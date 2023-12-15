import util.InputProvider;

public class Day01 {

	public static void main(String[] args) {
		String[] lines = InputProvider.getInputLines(1, true);
		
		int sum = 0;
		
		for (String line: lines) {
			sum += findCalibrationValue(line); 
		}
		
		System.out.println(sum);
	}
	

	private static int findCalibrationValue(String line) {
		int first = 0;
		int last = 0;

		for (int i = 0; i < line.length(); i++) {

			int temp = line.charAt(i) - 48;
			
			if (temp < 1 || temp > 9) {
				temp = extendedCalibrationValue(line, i);
			}
			
			if (temp < 1 || temp > 9) {
				continue;
			}
			
			if (first == 0) {
				first = temp;
			}
			last = temp;
		}

		return first * 10 + last;
	}
	
	private static int extendedCalibrationValue(String line, int index) {
		char temp = line.charAt(index);
		String[] values = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		int i = 0;
	
		while (values[i].charAt(0) != temp) {
			i++;
			if (i == values.length) {
				return 0;
			}
		}
		
		for (i = 0; i < values.length; i++) {
			int endIndex = index + values[i].length();
			if (endIndex > line.length()) {
				continue;
			}
			
			if (line.substring(index, endIndex).equals(values[i])) {
				return i + 1;
			}
		}
		
		return 0;
	}

}
