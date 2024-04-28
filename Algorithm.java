package doomsday;

import java.util.HashMap;
import java.util.Map;

public class Algorithm {
	
	public static int getDoomsday(int year) {
		int century = getCenturyCode(year);
		int yearInCent = year % 100;
		int sig1 = yearInCent / 12;
		int sig2 = yearInCent % 12;
		int sig3 = sig2 / 4;
		int doomsday = (century + sig1 + sig2 + sig3) % 7;
		return doomsday;
	}
	
	public static int getCenturyCode(int year) {
		switch ((year / 100) % 4) {
		case 0: return 2;
		case 1: return 0;
		case 2: return 5;
		}
		return 3;
	}
	
	public static int dayOfWeek(int year, int month, int day) {
		Map<Integer, Integer> anchorDays = new HashMap<>();
		anchorDays.put(3, 14);
		anchorDays.put(4, 4);
		anchorDays.put(5, 9);
		anchorDays.put(6, 6);
		anchorDays.put(7, 11);
		anchorDays.put(8, 8);
		anchorDays.put(9, 5);
		anchorDays.put(10, 10);
		anchorDays.put(11, 7);
		anchorDays.put(12, 12);
		
		int doomsday = getDoomsday(year);
		Integer anchor;
		
		if (anchorDays.containsKey(month)) {
			anchor = anchorDays.get(month);
		}
		else if (year % 4 != 0) {
			if (month == 1) {
				anchor = 3;
			}
			else {
				anchor = 28;
			}
		}
		else {
			if (month == 1) {
				anchor = 4;
			}
			else {
				anchor = 29;
			}
		}
		
		
		if (day < anchor) {
			return doomsday - ((anchor - day) % 7);
		}
		else if (day > anchor) {
			return (doomsday + ((day - anchor) % 7)) % 7;
		}
		return doomsday;
		
	}
	
	public static String verbose(int day) {
		switch (day) {
		case 0: return "Sunday";
		case 1: return "Monday";
		case 2: return "Tuesday";
		case 3: return "Wednesday";
		case 4: return "Thursday";
		case 5: return "Friday";
		case 6: return "Saturday";
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(verbose(dayOfWeek(2024, 2, 28)));
	}

}
