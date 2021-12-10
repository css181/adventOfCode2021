package day9;

public class GridToTrack {
	public boolean [][] isTracked;
	public GridToTrack (int rows, int cols) {
		isTracked = new boolean[rows][cols];
		for(int rowIndex=0; rowIndex<rows; rowIndex++) {
			for(int colIndex=0; colIndex<rows; colIndex++) {
				isTracked[rowIndex][colIndex] = false;
			}
		}
	}
}
