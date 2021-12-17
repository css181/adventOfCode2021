package day17;


public class Coordinate {

	private int x;
	private int y;
	
	public Coordinate() {
	}

    public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Coordinate)) { return false; }
        Coordinate other = (Coordinate) obj;
        
        if(this.x!=(other.x)) { return false; }
        if(this.y!=(other.y)) { return false; }
        
        return true;
    }

	@Override
    public String toString() {
		String print = "(" + x + "," + y + ")";
		return print;
    }
}
