package day25.spots;

import day25.Coordinate;

public abstract class Cucumber extends Spot implements ISpot, ICucumber {

	private Coordinate moveToCoordinate;
	
	@Override
	public boolean isEmpty() {
		return false;
	}

	public Coordinate getMoveToCoordinate() {
		return moveToCoordinate;
	}
	
	public void updateMoveToCoordinate(Coordinate newCoordinate) {
		this.moveToCoordinate = newCoordinate;
	}
	
	Cucumber(Coordinate coordinate) {
		super(coordinate);
	}
}
