package util;
import java.io.BufferedReader;
import java.io.FileReader;


public class InputProvider {

	private static String getFileContent(String filename) {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(String.format("ressources/%s.txt", filename)));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static String getInput(int day, boolean test) {
		String file = test ? "test/%d" : "task/%d";
		return getFileContent(String.format(file, day));
	}
	
	public static String[] getInputLines(int day, boolean test) {
		return getInput(day, test).split("\n");
	}
}