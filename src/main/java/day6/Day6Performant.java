package day6;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day6Performant {

	ArrayList<Long> fishCountByDay;

	private static File file;
	int dayCount = 0;
	
	public Day6Performant() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		
		populateFishArray();
	}

	public void populateFishArray() {
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		//This input only has 1 element that's a comma delimited list
		String[] fishStrings = inputs.get(0).split(",");
		fishCountByDay = new ArrayList<Long>();
		fishCountByDay.add(0l);
		fishCountByDay.add(0l);
		fishCountByDay.add(0l);
		fishCountByDay.add(0l);
		fishCountByDay.add(0l);
		fishCountByDay.add(0l);
		fishCountByDay.add(0l);
		fishCountByDay.add(0l);
		fishCountByDay.add(0l);
		fishCountByDay.add(0l);
		for (String fishString : fishStrings) {
			switch (fishString) {
			case "0":
				fishCountByDay.set(0, fishCountByDay.get(0)+1);
				break;
			case "1":
				fishCountByDay.set(1, fishCountByDay.get(1)+1);
				break;
			case "2":
				fishCountByDay.set(2, fishCountByDay.get(2)+1);
				break;
			case "3":
				fishCountByDay.set(3, fishCountByDay.get(3)+1);
				break;
			case "4":
				fishCountByDay.set(4, fishCountByDay.get(4)+1);
				break;
			case "5":
				fishCountByDay.set(5, fishCountByDay.get(5)+1);
				break;
			case "6":
				fishCountByDay.set(6, fishCountByDay.get(6)+1);
				break;
			default:
				break;
			}
		}
	}
	
	public void processDay() {
		System.out.println("Day start:" + fishCountByDay);
		long addedFishes = fishCountByDay.get(0);
		fishCountByDay.set(0, fishCountByDay.get(1));
		fishCountByDay.set(1, fishCountByDay.get(2));
		fishCountByDay.set(2, fishCountByDay.get(3));
		fishCountByDay.set(3, fishCountByDay.get(4));
		fishCountByDay.set(4, fishCountByDay.get(5));
		fishCountByDay.set(5, fishCountByDay.get(6));
		fishCountByDay.set(6, fishCountByDay.get(7)+addedFishes);
		fishCountByDay.set(7, fishCountByDay.get(8));
		fishCountByDay.set(8, addedFishes);
		System.out.println("Day end  :" + fishCountByDay);
		System.out.println("After Processing Day: " + dayCount++);
		System.out.println("Total: " + getTotalFishCount());
	}
	
	protected void setFileToUse(File file) {
		Day6Performant.file = file;
	}


	public ArrayList<Long> getFishList() {
		return fishCountByDay;
	}

	public long getTotalFishCount() {
		long total = 0l;
		for (Long fishGroup : fishCountByDay) {
			total+=fishGroup;
		}
		return total;
	}

	
}
