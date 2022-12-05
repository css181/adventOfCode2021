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
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Spot)) { return false; }
        Spot other = (Spot) obj;
        
        if(!this.coordinate.equals(other.coordinate)) { return false; }
        //TODO: Figure out a better way to do this that's OpenClose
        if(this instanceof EmptySpot && !(other instanceof EmptySpot)) { return false; }
        if(this instanceof EastCucumber && !(other instanceof EastCucumber)) { return false; }
        if(this instanceof SouthCucumber && !(other instanceof SouthCucumber)) { return false; }
        
        return true;
    }
}
