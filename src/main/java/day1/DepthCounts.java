package day1;

public class DepthCounts {
	private int increases = 0;
	private int decreases = 0;
	private int sames = 0;
	
	public int getIncreases() {
		return increases;
	}
	public void setIncreases(int increases) {
		this.increases = increases;
	}
	public void addIncrease() {
		this.increases++;
	}
	public int getDecreases() {
		return decreases;
	}
	public void setDecreases(int decreases) {
		this.decreases = decreases;
	}
	public void addDecrease() {
		this.decreases++;
	}
	public int getSames() {
		return sames;
	}
	public void setSames(int sames) {
		this.sames = sames;
	}
	public void addSame() {
		this.sames++;
	}
	
	public DepthCounts() {
		super();
	}
	
	protected  DepthCounts(int increases, int decreases, int sames) {
		super();
		this.increases = increases;
		this.decreases = decreases;
		this.sames = sames;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof DepthCounts)) { return false; }
        DepthCounts other = (DepthCounts) obj;
        
        if(this.increases != other.increases) { return false; }
        if(this.decreases != other.decreases) { return false; }
        if(this.sames != other.sames) { return false; }
        
        return true;
    }
    
    @Override
    public String toString() { 
        return "Increases: " + this.increases + "\nDecreases: " + this.decreases + "\nSames: " + this.sames;
    } 
}
