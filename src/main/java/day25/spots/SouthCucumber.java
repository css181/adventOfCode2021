package day25.spots;

import day25.Coordinate;

public class SouthCucumber extends Cucumber {

	public SouthCucumber(Coordinate coordinate) {
		super(coordinate);
	}

	@Override
	public String displayValue() {
		return "v";
	}

	@Override
	public Coordinate getMoveToCoordinate(ISpot[][] map) {
		if(getCoordinate().getY() < map.length -1)
			return new Coordinate(getCoordinate().getX(), getCoordinate().getY()+1);
		else
			return new Coordinate(getCoordinate().getX(), 0);
	}

}
