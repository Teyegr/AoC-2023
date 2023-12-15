import util.InputProvider;

public class Day05 {
	
	private static boolean test = false;
	private static String input = InputProvider.getInput(5, test);
	private static long[] seeds = parseSeeds();
	private static long[][] seedSoilMap = parseMap(parseSeedSoilRaw());
	private static long[][] soilFertilizerMap = parseMap(parseSoilFertilizerRaw());
	private static long[][] fertilizerWaterMap = parseMap(parseFertilizerWaterRaw());
	private static long[][] waterLightMap = parseMap(parseWaterLightRaw());
	private static long[][] lightTemperatureMap = parseMap(parseLightTemperatureRaw());
	private static long[][] temperatureHumidityMap = parseMap(parseTemperatureHumidityRaw());
	private static long[][] humidityLocationMap = parseMap(parseHumidityLocationRaw());
	private static long[][][] chain = {seedSoilMap, soilFertilizerMap, fertilizerWaterMap, waterLightMap, lightTemperatureMap, temperatureHumidityMap, humidityLocationMap};

	//map -> {destination range start, source range start, range length};
	public static void main(String[] args) {
		partOne();
		partTwo();
	}
	
	private static void partOne() {
		long min = Long.MAX_VALUE;
		for (long seed: seeds) {
			long value = chainTranslate(chain, seed);
			if (value < min) {
				min = value;
			}
		}
		System.out.println(min);
	}
	
	private static void partTwo() {
		long min = Long.MAX_VALUE;
		for (int i = 0; i < seeds.length; i+=  2) {
			System.out.println(((double) i / seeds.length) * 100 + "%");
			for (long j = 0; j < seeds[i + 1]; j++) {
				long value = chainTranslate(chain, seeds[i] + j);
				if (value < min) {
					min = value;
				}
			}
		}
		System.out.println(min);
	}
	
	private static long translate(long[][] map, long source) {
		for (long[] values : map) {
			if (source >= values[1] && source < values[1] + values[2]) {
				return source + values[0] - values[1];
			}
		}
		return source;
	}
	
	private static long chainTranslate(long[][][] maps,long source) {
		for (long[][] map : maps) {
			source = translate(map, source);
		}
		return source;
	}

	private static String[] parseScopeRaw(String scope) {
		return input.split(scope, 2)[1].split("\n\n")[0].trim().split("\n");
	}
	
	private static String parseSeedsRaw() {
		return parseScopeRaw("seeds:")[0];
	}
	
	private static String[] parseSeedSoilRaw() {
		return parseScopeRaw("seed-to-soil map:");
	}
	
	private static String[] parseSoilFertilizerRaw() {
		return parseScopeRaw("soil-to-fertilizer map:");
	}
	
	private static String[] parseFertilizerWaterRaw() {
		return parseScopeRaw("fertilizer-to-water map:");
	}
	
	private static String[] parseWaterLightRaw() {
		return parseScopeRaw("water-to-light map:");
	}
	
	private static String[] parseLightTemperatureRaw() {
		return parseScopeRaw("light-to-temperature map:");
	}
	
	private static String[] parseTemperatureHumidityRaw() {
		return parseScopeRaw("temperature-to-humidity map:");
	}
	
	private static String[] parseHumidityLocationRaw() {
		return parseScopeRaw("humidity-to-location map:");
	}
	
	private static long[] parseNumbers(String line) {
		String[] strings = line.split(" ");
		long[] numbers = new long[strings.length];
		for (int i = 0; i < strings.length; i++) {
			numbers[i] = Long.valueOf(strings[i]);
		}
		return numbers;
	}
	
	private static long[] parseSeeds() {
		return parseNumbers(parseSeedsRaw());
	}
	
	private static long[][] parseMap(String[] lines) {
		long[][] map = new long[lines.length][3];
		for (int i = 0; i < lines.length; i++) {
			long[] numbers = parseNumbers(lines[i]);
			map[i] = numbers;
		}
		return map;
	}
	
}
