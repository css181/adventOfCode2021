package day25.spots;

import day25.Coordinate;

public class EmptySpot extends Spot {

	public EmptySpot(Coordinate coordinate) {
		super(coordinate);
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public String displayValue() {
		return ".";
	}

}
