package day2;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Course {

	private static File file;
	
	public Course() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}

	public PositionPlot travel() {
		PositionPlot positionPlot = new PositionPlot();
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		for (String input : inputs) {
			String direction = input.substring(0, input.indexOf(" "));
			int magnitude = Integer.valueOf(input.substring(input.indexOf(" ") + 1));
			switch (direction) {
			case "up":
				positionPlot.addAim(magnitude * -1);
				break;
			case "down":
				positionPlot.addAim(magnitude);
				break;
			case "forward":
				positionPlot.addHorizontal(magnitude);
				positionPlot.addDepth(magnitude* positionPlot.getAim());
				break;
			default:
				throw new RuntimeException("Invalid direction: " + direction);
			}
		}
		return positionPlot;
	}
	
	protected void setFileToUse(File file) {
		Course.file = file;
	}

}
