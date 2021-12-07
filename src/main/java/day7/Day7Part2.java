package day7;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day7Part2 {

	ArrayList<Long> crabPositions;

	private static File file;
	int dayCount = 0;
	
	public Day7Part2() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		
		populateCrabPositions();
	}

	public void populateCrabPositions() {
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		//This input only has 1 element that's a comma delimited list
		String[] crabInputPositions = inputs.get(0).split(",");
		int maxValue = getMaxValue(crabInputPositions);
		crabPositions = new ArrayList<Long>();
		for(int x=0; x<=maxValue; x++) {
			crabPositions.add(0l);
		}
		for (String crabInputPosition : crabInputPositions) {
			int curPosValue = Integer.valueOf(crabInputPosition);
			crabPositions.set(curPosValue, crabPositions.get(curPosValue)+1);
		}
	}
	
	private int getMaxValue(String[] crabInputPositions) {
		int max =-1;
		for (String crabInputPosition : crabInputPositions) {
			int curPosValue = Integer.valueOf(crabInputPosition);
			if(curPosValue>max) {
				max = curPosValue;
			}
		}
		return max;
	}

	protected void setFileToUse(File file) {
		Day7Part2.file = file;
	}


	public ArrayList<Long> getCrabPositions() {
		return crabPositions;
	}

	public Long getBestFuelConsumptionIndex() {
		long bestFuelConsumption = 100000000000000l;//some stupidly large number
		long bestTargetIndex = 0;
		for(int x=0; x<crabPositions.size(); x++) {
			long curFuel = getFuelToGetAllTo(x);
			if(curFuel < bestFuelConsumption) {
				bestFuelConsumption = curFuel;
				bestTargetIndex = x;
			}
		}
		return bestTargetIndex;
	}

	public Long getFuelToGetAllTo(long target) {
		long fuelCost = 0;
		for (int index=0; index<crabPositions.size(); index++) {
			long distance = Math.abs(target - index);
			long costFor1Crab = 0;
			for(long x=distance; x>0; x--) {
				costFor1Crab+=x;
			}
			fuelCost+= costFor1Crab * crabPositions.get(index);
		}
		return fuelCost;
	}

	
}
