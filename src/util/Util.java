package util;

import java.util.ArrayList;

public class Util {
	public static String[] toArray(ArrayList<String> arrayList) {
		String[] array = new String[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}
}
