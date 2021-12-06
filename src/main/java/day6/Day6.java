package day6;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day6 {

	private static File file;
	ArrayList<ArrayList<Integer>> fishList;
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
		fishList = new ArrayList<ArrayList<Integer>>();
		fishList.add(new ArrayList<Integer>());
		for (String fishString : fishStrings) {
			fishList.get(0).add(Integer.valueOf(fishString));
		}
	}
	
	protected void setFileToUse(File file) {
		Day6.file = file;
	}


	public ArrayList<Integer> getSimpleFishList() {
		return fishList.get(0);
	}
	public ArrayList<ArrayList<Integer>> getFullFishList() {
		return fishList;
	}

	public void processDay() {
		int numOfFishToAdd = 0;
		for(int outerCounter=0; outerCounter<fishList.size(); outerCounter++) {
			for(int counter=0; counter<fishList.get(outerCounter).size(); counter++) {
				int curFishValue = fishList.get(outerCounter).get(counter);
				if(curFishValue==0) {
					fishList.get(outerCounter).set(counter, 6);
					numOfFishToAdd++;
				} else {
					fishList.get(outerCounter).set(counter, curFishValue -1);
				}
			}
		}
		
		//Now add fish where they need to go
		for(int add=0; add<numOfFishToAdd; add++) {
			int outerCounter=0;
			if(fishList.get(outerCounter).size()<10000) {
				fishList.get(outerCounter).add(8);					
			} else {
				boolean fishNeedsToBeAdded = true;
				do {				
					if(outerCounter==fishList.size()-1) {
						outerCounter++;
						fishList.add(new ArrayList<Integer>());
						fishList.get(outerCounter).add(8);
						fishNeedsToBeAdded = false;
					} else {
						outerCounter++;
						if(fishList.get(outerCounter).size()<10000) {
							fishList.get(outerCounter).add(8);
							fishNeedsToBeAdded = false;
						}
					}
				}while(fishNeedsToBeAdded);
			}
		}
		System.out.println("After Processing Day: " + dayCount++);
	}
	
	public long getTotalFishCount() {
		long total = 0;
		for(int outerCounter=0; outerCounter<fishList.size(); outerCounter++) {
			total+=fishList.get(outerCounter).size();
		}
		return total;
	}
}
