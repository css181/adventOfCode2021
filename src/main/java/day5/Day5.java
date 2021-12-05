package day5;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day5 {

	private static File file;
	private Grid grid;
	
	public Day5() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		grid = new Grid(1000);
	}
	
	protected void setFileToUse(File file) {
		Day5.file = file;
	}

	public ArrayList<Line> readInLines() {
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		ArrayList<Line> lines = new ArrayList<Line>();
		int startPosition = -1;
		int endPosition = -1;
		for (String input : inputs) {
			int x1, x2, y1, y2;
			startPosition = 0;
			endPosition = input.indexOf(",");
			x1 = Integer.valueOf(input.substring(startPosition,endPosition));
			startPosition = endPosition+1;
			endPosition = input.indexOf("-") - 1;
			y1 = Integer.valueOf(input.substring(startPosition,endPosition));
			startPosition = endPosition+4;
			endPosition = input.indexOf(",", startPosition);
			x2 = Integer.valueOf(input.substring(startPosition,endPosition));
			startPosition = endPosition+1;
			y2 = Integer.valueOf(input.substring(startPosition));
			Line line = new Line(new Coordinate(x1, y1), new Coordinate(x2, y2));
			lines.add(line);
		}
		return lines;
	}

	public ArrayList<Line> filterForSameXorY(ArrayList<Line> allLines) {
		ArrayList<Line> filteredLines = new ArrayList<Line>();
		for (Line line : allLines) {
			if(line.getStart().getX()==line.getEnd().getX() ||
					line.getStart().getY()==line.getEnd().getY()) {
				filteredLines.add(line);
			}
		}
		return filteredLines;
	}
	public ArrayList<Line> filterOnlyDiagonalLines(ArrayList<Line> allLines) {
		ArrayList<Line> filteredLines = new ArrayList<Line>();
		int x1, x2, y1, y2;
		for (Line line : allLines) {
			x1 = line.getStart().getX();
			y1 = line.getStart().getY();
			x2 = line.getEnd().getX();
			y2 = line.getEnd().getY();
			if(isLineDiagonal(x1, x2, y1, y2)) {
				filteredLines.add(line);
			}
		}
		return filteredLines;
	}

	public static boolean isLineDiagonal(int x1, int x2, int y1, int y2) {
		boolean answer = false;
		if((x1 - x2) == (y1 - y2)) {
			answer = true;
		} else if((x1 - x2) == (y2 - y1)) {
			answer = true;
		} else if((x2 - x1) == (y1 - y2)) {
			answer = true;
		} else if((x2 - x1) == (y2 - y1)) {
			answer = true;
		}
		return answer;
	}

	public Grid getGrid() {
		return grid;
	}
	protected void setGrid(int size) {
		grid = new Grid(size);
	}

	public void addLinesToGrid(ArrayList<Line> filteredLines) {
		int curLine = 0;
		System.out.println("Adding [" + filteredLines.size() + "] lines");
		for (Line line : filteredLines) {
			System.out.println("Adding Line: " + line + " #" + ++curLine);
			grid.addLine(line);
		}
	}


	
}
