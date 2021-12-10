package day9;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day9 {

	private static File file;
	SmokeHeights heights;
	ArrayList<ArrayList<Integer>> inputs;
	private int numOfCols = 100;
	private int numOfRows = 100;
	private GridToTrack trackGrid = new GridToTrack(numOfRows, numOfCols);
	
	public Day9() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		getInputs();
	}
	
	public void getInputs() {
		inputs = FileUtility.convertFileToIntListOfLists(file);
		heights = new SmokeHeights(numOfRows, numOfCols);
		populateSmokeHeights(inputs);
		calculateLowPoints();
	}
	
	public void populateSmokeHeights(ArrayList<ArrayList<Integer>> inputs) {
		for (int row=0; row<inputs.size(); row++) {
			ArrayList<Integer> rowArray = inputs.get(row);
			heights.populateRow(row, rowArray);
		}
	}
	
	public void getSumOfAllLowPoints() {
		
	}
	
	protected void setFileToUse(File file) {
		Day9.file = file;
	}
	protected void setNumOfCols(int num) { 
		this.numOfCols = num;
	}
	protected void setNumOfRows(int num) { 
		this.numOfRows = num;
	}
	
	public SmokeHeights getHeights() {
		return heights;
	}

	public void calculateLowPoints() {
		for(int row=0; row<heights.getNumOfRows(); row++) {
			for(int col=0; col<heights.getNumOfColsForRow(row); col++) {
				if(isLowPoint(heights.getValue(row, col), row, col)) {
					heights.setLowPoint(row, col);
				}
			}
		}
	}

	private boolean isLowPoint(int value, int row, int col) {
		if(row > 0) {
			//check up
			if(heights.getValue(row-1, col) <= value) {
				return false;
			}
		}
		if(row < heights.getNumOfRows()-1) {
			//check down
			if(heights.getValue(row+1, col) <= value) {
				return false;
			}
		}
		if(col > 0) {
			//check left
			if(heights.getValue(row, col-1) <= value) {
				return false;
			}
		}
		if(col < heights.getNumOfColsForRow(row)-1) {
			//check right
			if(heights.getValue(row, col+1) <= value) {
				return false;
			}
		}
		return true;
	}

	public int getRiskTotal() {
		int totalRisk = 0;
		for(int row=0; row<heights.getNumOfRows(); row++) {
			for(int col=0; col<heights.getNumOfColsForRow(row); col++) {
				if(heights.isLowPoint(row, col)) {
					totalRisk+=1+heights.getValue(row, col);
				}
			}
		}
		return totalRisk;
	}
	
	public void getNewGridTrack() {
		trackGrid = new GridToTrack(numOfRows, numOfCols);
	}

	public int calculateBasinSizeAtLowPoint(int row, int col) {
		int totalBasinSize = 1;//the actual low point
		trackGrid.isTracked[row][col] = true;
		totalBasinSize = tryNext(row-1, col, totalBasinSize);
		totalBasinSize = tryNext(row+1, col, totalBasinSize);
		totalBasinSize = tryNext(row, col-1, totalBasinSize);
		totalBasinSize = tryNext(row, col+1, totalBasinSize);
		return totalBasinSize;
	}
	private int tryNext(int row, int col, int totalBasinSize) {
		if(row>=0 && row<heights.getNumOfRows() && col>=0 && 
				col<heights.getNumOfColsForRow(row) && heights.getValue(row, col)!=9 && 
				!trackGrid.isTracked[row][col]) {
			totalBasinSize+=calculateBasinSizeAtLowPoint(row, col);
		}
		return totalBasinSize;
	}

}
