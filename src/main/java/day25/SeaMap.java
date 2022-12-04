package day25;

import java.util.ArrayList;

import day25.spots.EastCucumber;
import day25.spots.EmptySpot;
import day25.spots.SouthCucumber;
import day25.spots.Spot;

public class SeaMap {

	private ArrayList<ArrayList<Spot>> map;
	public SeaMap() {
		map = new ArrayList<ArrayList<Spot>>();
	}
	
	public SeaMap(ArrayList<ArrayList<Character>> input) {
		map = new ArrayList<ArrayList<Spot>>();
		for (int row=0; row<input.size(); row++) {
    		ArrayList<Character> curRow = input.get(row);
    		map.add(new ArrayList<Spot>());
			for (int col=0; col<curRow.size(); col++) {
				Spot spot = convertCharToSpot(input.get(row).get(col));
				map.get(row).add(spot);
			}
		}
	}

	private Spot convertCharToSpot(Character character) {
		switch (character) {
		case '.':
			return new EmptySpot();
		case 'v':
			return new SouthCucumber();
		case '>':
			return new EastCucumber();
		default:
			throw new RuntimeException("Invalid map character: " + character);
		}
	}
	
	public Spot getSpot(int x, int y) {
		return map.get(x).get(y);
	}

	@Override
    public String toString() {
    	String print = "";
    	for (ArrayList<Spot> row : map) {
			for (Spot spot : row) {
				print+=spot.displayValue();
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

        if(!(obj instanceof SeaMap)) { return false; }
        SeaMap other = (SeaMap) obj;

        if(!this.map.equals(other.map)) { return false; }
        
        return true;
    }
}
