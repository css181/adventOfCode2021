package day13;


public class Dot {

	private int x;
	private int y;
	
	public Dot() {
	}

    public Dot(int x, int y) {
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

        if(!(obj instanceof Dot)) { return false; }
        Dot other = (Dot) obj;
        
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
