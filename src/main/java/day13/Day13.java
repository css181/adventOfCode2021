package day13;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day13 {
	
	private static File file;
	private static File foldFile;
	private Paper paper;
	private ArrayList<Dot> dots;
	private ArrayList<String> folds;
	
	
	public Day13() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		URL foldFileName = getClass().getResource("InputFolds.txt");
		foldFile = new File(foldFileName.getPath());
	}

	protected void getInputs() {
		ArrayList<String> inputLines = FileUtility.convertFileToStringArray(file);
		dots = new ArrayList<Dot>();
		int maxX=0;
		int maxY=0;
		for (String inputLine : inputLines) {
			int commaPos=inputLine.indexOf(",");
			int xVal = Integer.valueOf(inputLine.substring(0, commaPos));
			int yVal = Integer.valueOf(inputLine.substring(commaPos+1));
			dots.add(new Dot(xVal,yVal));
			if(xVal>maxX) { maxX=xVal; }
			if(yVal>maxY) { maxY=yVal; }
		}
		inputLines = FileUtility.convertFileToStringArray(foldFile);
		folds = inputLines;
		paper = new Paper(maxX+1, maxY+1);
	}
	protected void setFileToUse(File file) {
		Day13.file = file;
	}
	public void setFoldFileToUse(File foldFile) {
		Day13.foldFile = foldFile;
	}

	public ArrayList<Dot> getDots() {
		return dots;
	}

	public ArrayList<String> getFolds() {
		return folds;
	}

	public Paper getPaper() {
		return paper;
	}

	public void applyDotsToPaper() {
		for (Dot dot : dots) {
			paper.addDot(dot);
		}
	}

	public void applyFoldToPaper(String fold) {
		if(fold.toLowerCase().startsWith("y")) {
			int yVal = Integer.valueOf(fold.substring(fold.indexOf("=")+1));
			paper.horizontalFoldAtY(yVal);
		} else {
			int xVal = Integer.valueOf(fold.substring(fold.indexOf("=")+1));
			paper.verticalFoldAtX(xVal);
		}
	}

}
