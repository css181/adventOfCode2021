package day11;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day11 {
	
	private static File file;
	private ArrayList<ArrayList<Integer>> octopusValues;
	private ArrayList<ArrayList<Boolean>> didFlash;
	private int totalFlashes = 0;
	
	public Day11() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		getInputs();
	}

	protected void getInputs() {
		octopusValues = FileUtility.convertFileToIntListOfLists(file);
		didFlash = new ArrayList<ArrayList<Boolean>>();
		for(int row=0; row<octopusValues.size(); row++) {
			ArrayList<Boolean> boolRow = new ArrayList<Boolean>();
			for(int col=0; col<octopusValues.get(row).size(); col++) {
				boolRow.add(false);
			}
			didFlash.add(boolRow);
		}
	}

	private void resetDidFlash() {
		for(int row=0; row<octopusValues.size(); row++) {
			for(int col=0; col<octopusValues.get(row).size(); col++) {
				didFlash.get(row).set(col, false);
			}
		}
	}
	
	protected void setFileToUse(File file) {
		Day11.file = file;
	}

	public ArrayList<ArrayList<Integer>> getOctopusValues() {
		return octopusValues;
	}

	public void processStep() {
		resetDidFlash();
		for(int row=0; row<octopusValues.size(); row++) {
			for(int col=0; col<octopusValues.get(row).size(); col++) {
				increaseEnergy(row, col);
			}
		}
	}

	private void increaseEnergy(int row, int col) {
		if((row<0)||(col<0)||(row>=octopusValues.size())||(col>=octopusValues.get(row).size())) {
			//out of bounds, do nothing
			return;
		}
		int curValue = octopusValues.get(row).get(col);
		if(didFlash.get(row).get(col)) {
			return;//Nothing to do, already flashed
		}
		if(curValue!=9) {
			octopusValues.get(row).set(col, curValue+1);
		} else {
			//flash
			octopusValues.get(row).set(col, 0);
			didFlash.get(row).set(col, true);
			totalFlashes++;
			
			increaseEnergy(row-1, col-1);
			increaseEnergy(row-1, col);
			increaseEnergy(row-1, col+1);
			increaseEnergy(row, col-1);
			increaseEnergy(row, col+1);
			increaseEnergy(row+1, col-1);
			increaseEnergy(row+1, col);
			increaseEnergy(row+1, col+1);
		}
	}

	public int getTotalFlashes() {
		return totalFlashes;
	}
	
	public boolean areAllFlashing() {
		for(int row=0; row<octopusValues.size(); row++) {
			for(int col=0; col<octopusValues.get(row).size(); col++) {
				if(octopusValues.get(row).get(col)!=0) {
					return false;
				}
			}
		}
		return true;
	}
	
}
