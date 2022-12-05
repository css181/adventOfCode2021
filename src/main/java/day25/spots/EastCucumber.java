package day25.spots;

import day25.Coordinate;
import day25.SeaMap;

public class EastCucumber extends Cucumber{

	public EastCucumber(Coordinate coordinate) {
		super(coordinate);
	}

	@Override
	public String displayValue() {
		return ">";
	}

	@Override
	public Coordinate getMoveToCoordinate(ISpot[][] map) {
		if(getCoordinate().getX() < map[0].length - 1)
			return new Coordinate(getCoordinate().getX()+1, getCoordinate().getY());
		else
			return new Coordinate(0, getCoordinate().getY());
	}

}
