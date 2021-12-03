package day2;


public class PositionPlot {
	private int horizontal;
	private int depth;
	
	public PositionPlot() {
		super();
		horizontal = 0;
		depth = 0;
	}

	protected PositionPlot(int horizontal, int depth) {
		this.horizontal = horizontal;
		this.depth = depth;
	}

	public void addHorizontal(int add) {
		horizontal+=add;
	}
	public int getHorizontal() {
		return horizontal;
	}

	public void addDepth(int add) {
		depth+=add;
	}
	public int getDepth() {
		return depth;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof PositionPlot)) { return false; }
        PositionPlot other = (PositionPlot) obj;
        
        if(this.horizontal != other.horizontal) { return false; }
        if(this.depth != other.depth) { return false; }
        
        return true;
    }
    
    @Override
    public String toString() { 
        return "Horizontal: " + this.horizontal + "\nDepth: " + this.depth;
    } 
}
