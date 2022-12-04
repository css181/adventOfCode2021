package day25.spots;

import day25.Coordinate;

public class EastCucumber extends Cucumber{

	public EastCucumber(Coordinate coordinate) {
		super(coordinate);
	}

	@Override
	public String displayValue() {
		return ">";
	}

}
