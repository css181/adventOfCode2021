package day25;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class SeaMapFromInput {

	private static File file;
	private SeaMap seaMap;
	
	public SeaMapFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		seaMap = new SeaMap();
	}
	
	protected void setFileToUse(File file) {
		SeaMapFromInput.file = file;
	}

	public void populateSeaMap() {
		ArrayList<ArrayList<Character>> input = FileUtility.convertFileToCharacterArray(file);
		seaMap = new SeaMap(input);
	}

	public SeaMap getSeaMap() {
		return seaMap;
	}

}
