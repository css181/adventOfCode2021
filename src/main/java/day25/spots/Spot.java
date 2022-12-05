package day25.spots;

import day25.Coordinate;

public abstract class Spot implements ISpot {

	protected Coordinate coordinate;
	
	Spot(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
}
