package innitialDayToCloneFrom;

import java.util.ArrayList;
import java.util.Arrays;

import day2.PositionPlot;

public class POJO {

	
	public POJO() {
	}
	
	
    @Override
    public String toString() {
    	ArrayList<String> fullRow;
    	ArrayList<ArrayList<String>> card = new ArrayList<ArrayList<String>>();
		for(int row=0; row<5; row++) {
			fullRow = new ArrayList<String>();
			for(int col=0; col<5; col++) {
				if(called.get(row).get(col)) {
					fullRow.add("[" + numbers.get(row).get(col) + "]");
				} else {
					fullRow.add(String.valueOf(numbers.get(row).get(col)));
				}
			}
			card.add(fullRow);
		}
		return card.get(0).get(0) + " " + card.get(0).get(1) + " " + card.get(0).get(2) + " " + card.get(0).get(3) + " " + card.get(0).get(4) + "\n" +
		card.get(1).get(0) + " " + card.get(1).get(1) + " " + card.get(1).get(2) + " " + card.get(1).get(3) + " " + card.get(1).get(4) + "\n" +
		card.get(2).get(0) + " " + card.get(2).get(1) + " " + card.get(2).get(2) + " " + card.get(2).get(3) + " " + card.get(2).get(4) + "\n" +
		card.get(3).get(0) + " " + card.get(3).get(1) + " " + card.get(3).get(2) + " " + card.get(3).get(3) + " " + card.get(3).get(4) + "\n" +
		card.get(4).get(0) + " " + card.get(4).get(1) + " " + card.get(4).get(2) + " " + card.get(4).get(3) + " " + card.get(4).get(4);
    } 
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof PositionPlot)) { return false; }
        PositionPlot other = (PositionPlot) obj;
        
        if(this.horizontal != other.horizontal) { return false; }
        if(this.depth != other.depth) { return false; }
        if(this.aim != other.aim) { return false; }
        
        return true;
    }
}
