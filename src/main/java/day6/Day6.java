package day6;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day6 {

	private static File file;
	ArrayList<Integer> fishList;
	int dayCount = 0;
	
	public Day6() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		
		populateFishArray();
	}

	public void populateFishArray() {
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		//This input only has 1 element that's a comma delimited list
		String[] fishStrings = inputs.get(0).split(",");
		fishList = new ArrayList<Integer>();
		for (String fishString : fishStrings) {
			fishList.add(Integer.valueOf(fishString));
		}
	}
	
	public void processDay() {
		int numOfFishToAdd = 0;
		for(int counter=0; counter<fishList.size(); counter++) {
			int curFishValue = fishList.get(counter);
			if(curFishValue==0) {
				fishList.set(counter, 6);
				numOfFishToAdd++;
			} else {
				fishList.set(counter, curFishValue -1);
			}
		}
		for(int add=0; add<numOfFishToAdd; add++) {
			fishList.add(8);
		}
		System.out.println("After Processing Day: " + dayCount++);
		System.out.println(fishList);
	}
	
	protected void setFileToUse(File file) {
		Day6.file = file;
	}


	public ArrayList<Integer> getFishList() {
		return fishList;
	}

	public long getTotalFishCount() {
		return fishList.size();
	}

	public long getNumberOfReproductionsForStartOverXDays(int fishValue, int days) {
		long total = 0;
		for(int curDay=1; curDay<=days; curDay++) {
			fishValue--;
			if(fishValue<0) {
				total=total+1+getNumberOfReproductionsForStartOverXDays(8, days-curDay);
				fishValue=6;
			}
		}
		return total;
	}
	
}