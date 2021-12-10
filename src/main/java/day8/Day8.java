package day8;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day8 {

	ArrayList<ArrayList<String>> inputNumberGroups;

	private static File file;
	int dayCount = 0;
	
	public Day8() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		
		populateWiresAndSignals();
	}

	protected void setFileToUse(File file) {
		Day8.file = file;
	}

	public void populateWiresAndSignals() {
		inputNumberGroups = new ArrayList<ArrayList<String>>();
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		for (String input : inputs) {
			ArrayList<String> line = new ArrayList<String>();
			String afterPipe = input.substring(input.indexOf("|") + 2);
			String[] curNumberGroups = afterPipe.split(" ");
			for (String numberGroup : curNumberGroups) {
				line.add(numberGroup);
			}
			inputNumberGroups.add(line);
		}
	}
	

	public ArrayList<ArrayList<String>> getInputNumberGroups() {
		return inputNumberGroups;
	}

	public int getTotalNumberGroupsOfUniuqueLengths() {
		int total = 0;
		for(int outer=0; outer<inputNumberGroups.size(); outer++) {
			for(int inner=0; inner<inputNumberGroups.get(outer).size(); inner++) {
				int curLength = inputNumberGroups.get(outer).get(inner).length(); 
				if( (curLength==2) || (curLength==3) || (curLength==4) || (curLength==7) ) {
					total+=1;
				}
			}
		}
		return total;
	}

	
}
