package day3;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class PowerCalculation {

	private static File file;
	
	public PowerCalculation() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}

	
	protected void setFileToUse(File file) {
		PowerCalculation.file = file;
	}


	public int whichHasMore(ArrayList<String> bytes) {
		int zeroTotal = 0;
		int oneTotal = 0;
		for (String bite : bytes) {
			switch (bite) {
			case "1":
				oneTotal++;
				break;
			case "0":
				zeroTotal++;
				break;
			default:
				throw new RuntimeException("Invalid binary bit: " + bite);
			}
		}
		
		if(oneTotal>zeroTotal) {return 1;} else {return 0;}
	}


	public ArrayList<String> getAllInputs() {
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		return inputs;
	}


	public ArrayList<String> getBinaryListForByteNumber(int byteIndex, ArrayList<String> inputs) {
		ArrayList<String> binaryList = new ArrayList<String>();
		for (String input : inputs) {
			binaryList.add(String.valueOf(input.charAt(byteIndex)));
		}
		return binaryList;
	}

}
