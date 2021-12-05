package day5;

import java.util.ArrayList;

public class Grid {

	private ArrayList<ArrayList<String>> spots;
	private int maxSpotSize;

	public Grid(int maxSpotSize) {
		this.maxSpotSize = maxSpotSize;
		spots = new ArrayList<ArrayList<String>>();
		for(int row=0; row<maxSpotSize; row++) {
			ArrayList<String> fullLine = new ArrayList<String>();
			for(int col=0; col<maxSpotSize; col++) {
				fullLine.add(".");
			}
			spots.add(fullLine);
		}
	}
	
	//Assumption, line must be straight down either the x or y axis
	public void addLine(Line line) {
		int x1 = line.getStart().getX();
		int x2 = line.getEnd().getX();
		int y1 = line.getStart().getY();
		int y2 = line.getEnd().getY();
		//NOTE:Remember y is the row and x is the column
			//So [x,y] coordinate is spots.get(y).get(x);
		
		if(x1==x2) {
			//the line is straight on the x axis, changing y's
			if(y1<y2) {
				//the line goes up
				for(int ySpot=y1; ySpot<=y2; ySpot++) {
					String curValue = spots.get(ySpot).get(x1);
					if(curValue.equals(".")) {
						spots.get(ySpot).set(x1, "1");
					} else {
						int curIntValue = Integer.valueOf(curValue);
						spots.get(ySpot).set(x1, String.valueOf(curIntValue+1));
					}
				}
			} else {
				//the line goes down
				for(int ySpot=y2; ySpot<=y1; ySpot++) {
					String curValue = spots.get(ySpot).get(x1);
					if(curValue.equals(".")) {
						spots.get(ySpot).set(x1, "1");
					} else {
						int curIntValue = Integer.valueOf(curValue);
						spots.get(ySpot).set(x1, String.valueOf(curIntValue+1));
					}
				}
			}
		}
		else {
			//the line is straight on the Y axis, changing x's
			if(x1<x2) {
				//the line goes right
				for(int xSpot=x1; xSpot<=x2; xSpot++) {
					String curValue = spots.get(y1).get(xSpot);
					if(curValue.equals(".")) {
						spots.get(y1).set(xSpot, "1");
					} else {
						int curIntValue = Integer.valueOf(curValue);
						spots.get(y1).set(xSpot, String.valueOf(curIntValue+1));
					}
				}
			} else {
				//the line goes left
				for(int xSpot=x2; xSpot<=x1; xSpot++) {
					String curValue = spots.get(y1).get(xSpot);
					if(curValue.equals(".")) {
						spots.get(y1).set(xSpot, "1");
					} else {
						int curIntValue = Integer.valueOf(curValue);
						spots.get(y1).set(xSpot, String.valueOf(curIntValue+1));
					}
				}
			}
		}
	} 

	@Override
    public String toString() {
		String print = "";
		for(int row=0; row<maxSpotSize; row++) {
			for(int col=0; col<maxSpotSize; col++) {
				print+=spots.get(row).get(col) + " ";
			}
			print+="\n";
		}
		return print;
    }

	public int findAllInstancesAbove(int highNumberToLookFor) {
		int totalInstances = 0;
		for(int row=0; row<maxSpotSize; row++) {
			for(int col=0; col<maxSpotSize; col++) {
				String curValue = spots.get(row).get(col);
				int curIntValue = 0;
				if(!curValue.equals(".")) {
					curIntValue = Integer.valueOf(curValue);
				}
				if(curIntValue>=highNumberToLookFor) {
					totalInstances++;
				}
			}
		}
		return totalInstances;
	}
}
