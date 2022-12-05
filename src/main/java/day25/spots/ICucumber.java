package day25.spots;

import day25.Coordinate;

public interface ICucumber {

	public Coordinate getMoveToCoordinate(ISpot[][] map);
	public boolean isAbleToMove(ISpot[][] map);
	public void performMove(ISpot[][] map);
}
