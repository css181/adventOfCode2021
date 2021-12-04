package day3;

import java.util.ArrayList;

public class DecimalUtil {
	
	public static long getDecimal(ArrayList<Integer> bytes) {
		long total = 0;
		for (int i=0; i<bytes.size(); i++) {
			long thisByte = (long) Math.pow(2, i);
			total+=thisByte*bytes.get(i);
		}
		return total;
	}
	public static long getDecimal(String bytes) {
		long total = 0;
		for (int i=0; i<bytes.length(); i++) {
			long thisByte = (long) Math.pow(2, i);
			total+=thisByte*Integer.valueOf(String.valueOf(bytes.charAt(bytes.length()-(i+1))));
		}
		return total;
	}
}
