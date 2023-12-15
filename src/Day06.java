import util.InputProvider;

public class Day06 {
	static boolean test = false;
	static String[] lines = InputProvider.getInputLines(6, test);

	public static void main(String[] args) {
		partOne();
		partTwo();
	}
	
	private static void partOne() {
		int prod = 1;
		for (Race race: parseRaces()) {
			long ways = wayFinder(race);
			System.out.println(ways);
			prod *= ways;
		}
		
		System.out.println(prod);
	}
	
	private static void partTwo() {
		Race race = parseRace();
		long ways = wayFinder(race);
		System.out.println(ways);
	}
	
	private static long wayFinder(Race race) {
		long limit = (int) Math.sqrt(race.distance) + 1;
		long time = limit;
		long speed = race.time - time;
		
		while ((time - 1) * (speed + 1) > race.distance) {
			time--;
			speed++;
		}
		return speed - time + 1;
	}
	
	private static Race[] parseRaces() {
		String timesRaw = lines[0].split(":", 2)[1].trim();
		String distancesRaw = lines[1].split(":", 2)[1].trim();
		while (timesRaw.indexOf("  ") != -1) {
			timesRaw = timesRaw.replace("  ", " ");
		}
		while (distancesRaw.indexOf("  ") != -1) {
			distancesRaw = distancesRaw.replace("  ", " ");
		}
		String[] ts = timesRaw.split(" ");
		String[] ds = distancesRaw.split(" ");
		Race[] races = new Race[ts.length];
		
		for (int i = 0; i < ts.length; i++) {
			long time = Long.valueOf(ts[i]);
			long distance = Long.valueOf(ds[i]);
			races[i] = new Race(time, distance);
		}
		return races;
	}
	
	private static Race parseRace() {
		StringBuilder timeBuilder = new StringBuilder();
		StringBuilder distanceBuilder = new StringBuilder();
		for (int i = 0; i < lines[0].length(); i++) {
			char t = lines[0].charAt(i);
			char d = lines[1].charAt(i);
			if (Character.isDigit(t)) {
				timeBuilder.append(t);
			}
			if (Character.isDigit(d)) {
				distanceBuilder.append(d);
			}
		}
		long time = Long.valueOf(timeBuilder.toString());
		long distance = Long.valueOf(distanceBuilder.toString());
		return new Race(time, distance);
	}
	
	static class Race {
		long time;
		long distance;
		
		Race(long time, long distance) {
			this.time = time;
			this.distance = distance;
		}
		
		public String toString() {
			return String.format("Race(%d, %d)", time, distance);
		}
	}
}
