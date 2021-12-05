package day5;

public class Line {
	private Coordinate start;
	private Coordinate end;
	public Line(Coordinate start, Coordinate end) {
		super();
		this.start = start;
		this.end = end;
	}
	public Coordinate getStart() {
		return start;
	}
	public void setStart(Coordinate start) {
		this.start = start;
	}
	public Coordinate getEnd() {
		return end;
	}
	public void setEnd(Coordinate end) {
		this.end = end;
	}
	
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Line)) { return false; }
        Line other = (Line) obj;
        
        if(!this.start.equals(other.start)) { return false; }
        if(!this.end.equals(other.end)) { return false; }
        
        return true;
    }
    
	@Override
    public String toString() {
		String print = "(" + start.getX() + "," + start.getY() +
				")->(" + end.getX() + "," + end.getY() + ")";
		return print;
    }
}
