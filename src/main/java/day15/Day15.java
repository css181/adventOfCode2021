package day15;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import day1.FileUtility;

public class Day15 {
	
	private static File file;
	private int[][] grid;
	private boolean[][] visited;
	private int total = 0;
	private static final int SOMETHING_STUPIDLY_LARGE = Integer.MAX_VALUE-100;
	
	public Day15() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}

	protected void getInputs() {
		grid = FileUtility.convertFileToIntArrayOfArrays(file);
		visited = new boolean[grid.length][grid.length];
	}
	protected void setFileToUse(File file) {
		Day15.file = file;
	}

	public int[][] getGrid() {
		return grid;
	}

	public int getTotalOfBestPathFrom(int row, int col) {
		if(row==grid.length) {
			return SOMETHING_STUPIDLY_LARGE;
		}
		if(col==grid[0].length) {
			return SOMETHING_STUPIDLY_LARGE;
		}
		if(row<0) {
			return SOMETHING_STUPIDLY_LARGE;
		}
		if(col<0) {
			return SOMETHING_STUPIDLY_LARGE;
		}
		if(visited[row][col]) {
			return SOMETHING_STUPIDLY_LARGE;
		}
		int curTotal = 0;
		if(row!=0 || col!=0) {
			curTotal+=grid[row][col];
			visited[row][col] = true;
		}
		if(row==grid.length-1 && col==grid[grid.length-1].length-1) {
			return curTotal;//got to end
		}
		int byGoingRight, byGoingDown, byGoingLeft, byGoingUp;
		if(getValue(row,col+1)<getValue(row+1,col)) {
			byGoingRight = curTotal+getTotalOfBestPathFrom(row, col+1);
			byGoingDown = curTotal+getTotalOfBestPathFrom(row+1, col);
		} else {
			byGoingDown = curTotal+getTotalOfBestPathFrom(row+1, col);
			byGoingRight = curTotal+getTotalOfBestPathFrom(row, col+1);
		}
		byGoingLeft = curTotal+getTotalOfBestPathFrom(row, col-1);
		byGoingUp = curTotal+getTotalOfBestPathFrom(row-1, col);
		return getMin(byGoingRight, byGoingDown, byGoingLeft, byGoingUp);
	}

	private int getMin(int byGoingRight, int byGoingDown, int byGoingLeft, int byGoingUp) {
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(byGoingRight, byGoingDown, byGoingLeft, byGoingUp));
		Collections.sort(list);
		return list.get(0);
	}
	
	private int getValue(int row, int col) {
		if(row>0 && row<grid.length && col>0 && col<grid[0].length) {
			return grid[row][col];
		}
		return SOMETHING_STUPIDLY_LARGE;
	}

}
