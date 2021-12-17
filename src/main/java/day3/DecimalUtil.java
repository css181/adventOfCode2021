package day3;

import java.util.ArrayList;

public class DecimalUtil {

	//NOTE: This assumes (1,0,0) = 1 reading left to right
	public static long getDecimal(ArrayList<Integer> bytes) {
		long total = 0;
		for (int i=0; i<bytes.size(); i++) {
			long thisByte = (long) Math.pow(2, i);
			total+=thisByte*bytes.get(i);
		}
		return total;
	}
	
	//NOTE: This assumes [1,0,0] = 4 reading right to left
	public static long getDecimal(int[] bytes) {
		long total = 0;
		for (int i=bytes.length-1; i>=0; i--) {
			long thisByte = (long) Math.pow(2, i);
			total+=thisByte*bytes[(bytes.length-1)-i];
		}
		return total;
	}
	
	//NOTE: This assumes (1,0,0) = 1 reading left to right
	public static long getDecimal(String bytes) {
		long total = 0;
		for (int i=0; i<bytes.length(); i++) {
			long thisByte = (long) Math.pow(2, i);
			total+=thisByte*Integer.valueOf(String.valueOf(bytes.charAt(bytes.length()-(i+1))));
		}
		return total;
	}
	
}
