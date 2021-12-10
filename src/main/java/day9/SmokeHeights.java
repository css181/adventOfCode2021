package day9;

import java.util.ArrayList;
import java.util.Arrays;


public class SmokeHeights {

	private int [][] grid;
	private boolean [][] isLowPointValues;
	public SmokeHeights (int rows, int cols) {
		grid = new int[rows][cols];
		isLowPointValues = new boolean[rows][cols];
	}
	
	public void populateRow(int index, ArrayList<Integer> row) {
		for (int col=0; col<row.size(); col++) {
			grid[index][col] = row.get(col);
			isLowPointValues[index][col] = false;
		}
	}
	
	public void setLowPoint(int row, int col) {
		isLowPointValues[row][col] = true;
	}
	
	public int getNumOfRows() {
		return grid.length;
	}
	public int getNumOfColsForRow(int row) {
		return grid[row].length;
	}
	public int getValue(int row, int col) {
		return grid[row][col];
	}
	public boolean isLowPoint(int row, int col) {
		return isLowPointValues[row][col];
	}

	public ArrayList<Integer> getListOfLowPointValuesInRow(int row) {
		ArrayList<Integer> lowPointList = new ArrayList<Integer>();
		for(int col=0; col<grid[row].length; col++) {
			if(isLowPointValues[row][col]) {
				lowPointList.add(grid[row][col]);
			}
		}
		return lowPointList;
	}
	
    @Override
    public String toString() {
    	String print = "";
   		for(int row=0; row<grid.length; row++) {
   			for(int col=0; col<grid[row].length; col++) {
   				if(isLowPointValues[row][col]) {
   					print+="[" + String.valueOf(grid[row][col]) + "]";
   				} else {
   					print+=" " + String.valueOf(grid[row][col] + " ");
   				}
    		}
    		print+="\n";
    	}
		return print;
    }
    
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof SmokeHeights)) { return false; }
        SmokeHeights other = (SmokeHeights) obj;
        
        if(Arrays.equals(this.isLowPointValues, other.isLowPointValues)) { return false; }
        if(this.grid.length!=(other.grid.length)) { return false; }
        for(int row=0; row<grid.length; row++) {
        	if(this.grid[row].length!=(other.grid[row].length)) { return false; }
        	for(int col=0; col<grid[row].length; col++) {
        		if(this.grid[row][col]!=other.grid[row][col]) { return false; }
        	}
        }
        
        return true;
    }
}
