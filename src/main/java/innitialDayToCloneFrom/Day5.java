package innitialDayToCloneFrom;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day5 {

	private static File file;
	
	public Day5() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}
	
	protected void setFileToUse(File file) {
		Day5.file = file;
	}


}
