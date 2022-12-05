package day25.spots;

import java.lang.reflect.InvocationTargetException;

import day25.Coordinate;

public abstract class Cucumber extends Spot implements ICucumber {

	@Override
	public boolean isEmpty() {
		return false;
	}

	abstract public Coordinate getMoveToCoordinate(ISpot[][] map);
	
	Cucumber(Coordinate coordinate) {
		super(coordinate);
	}
	
	public boolean isAbleToMove(ISpot[][] map) {
		ISpot moveSpot = map[getMoveToCoordinate(map).getY()][getMoveToCoordinate(map).getX()];
		return moveSpot.isEmpty();
	}
	
	public void performMove(ISpot[][] map) {
		int currentX = getCoordinate().getX();
		int currentY = getCoordinate().getY();
		map[currentY][currentX] = new EmptySpot(new Coordinate(currentX, currentY));
		int newX = getMoveToCoordinate(map).getX();
		int newY = getMoveToCoordinate(map).getY();
		try {
			map[newY][newX] = this.getClass().getDeclaredConstructor(Coordinate.class).newInstance(new Coordinate(newX, newY));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
