package day25.spots;

import day25.Coordinate;

public abstract class Spot implements ISpot {

	protected Coordinate coordinate;
	
	Spot(Coordinate coordinate) {
		this.coordinate = coordinate;
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
        if(!this.getClass().equals(other.getClass())) { return false; }
        
        return true;
    }
}
