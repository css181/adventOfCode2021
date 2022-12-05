package day25;

import java.util.ArrayList;

import day25.spots.EastCucumber;
import day25.spots.EmptySpot;
import day25.spots.SouthCucumber;
import day25.spots.ISpot;

public class SeaMap {

	private ISpot[][] map;
	
	public SeaMap(ArrayList<ArrayList<Character>> input) {
		map = new ISpot[input.size()][input.get(0).size()];
		for (int row=0; row<input.size(); row++) {
    		ArrayList<Character> curRow = input.get(row);
			for (int col=0; col<curRow.size(); col++) {
				ISpot spot = convertCharToSpot(input.get(row).get(col), new Coordinate(col, row));
				map[row][col] = spot;
			}
		}
	}

	private ISpot convertCharToSpot(Character character, Coordinate spotPosition) {
		switch (character) {
		case '.':
			return new EmptySpot(spotPosition);
		case 'v':
			return new SouthCucumber(spotPosition);
		case '>':
			return new EastCucumber(spotPosition);
		default:
			throw new RuntimeException("Invalid map character: " + character);
		}
	}
	
	public ISpot[][] getMap() {
		return map;
	}
	public void setMap(ISpot[][] map) {
		this.map = map;
	}

	public ISpot getSpot(int x, int y) {
		return map[y][x];
	}

	@Override
    public String toString() {
    	String print = "";
    	for (ISpot[] row : map) {
			for (ISpot spot : row) {
				print+=spot.displayValue();
			}
			print+="\n";
		}
		return print;
    } 
}
