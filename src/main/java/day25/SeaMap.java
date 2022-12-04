package day25;

import java.util.ArrayList;

import day25.spots.EastCucumber;
import day25.spots.EmptySpot;
import day25.spots.SouthCucumber;
import day25.spots.ISpot;

public class SeaMap {

	private ArrayList<ArrayList<ISpot>> map;
	public SeaMap() {
		map = new ArrayList<ArrayList<ISpot>>();
	}
	
	public SeaMap(ArrayList<ArrayList<Character>> input) {
		map = new ArrayList<ArrayList<ISpot>>();
		for (int row=0; row<input.size(); row++) {
    		ArrayList<Character> curRow = input.get(row);
    		map.add(new ArrayList<ISpot>());
			for (int col=0; col<curRow.size(); col++) {
				ISpot spot = convertCharToSpot(input.get(row).get(col), new Coordinate(row, col));
				map.get(row).add(spot);
			}
		}
	}

	private ISpot convertCharToSpot(Character character, Coordinate current) {
		switch (character) {
		case '.':
			return new EmptySpot(current);
		case 'v':
			return new SouthCucumber(current);
		case '>':
			return new EastCucumber(current);
		default:
			throw new RuntimeException("Invalid map character: " + character);
		}
	}
	
	public ISpot getSpot(int x, int y) {
		return map.get(x).get(y);
	}

	@Override
    public String toString() {
    	String print = "";
    	for (ArrayList<ISpot> row : map) {
			for (ISpot spot : row) {
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
