package day3;


public class Rates {
	private int gamma;
	private int epsilon;
	
	public Rates() {
		super();
		gamma = 0;
		epsilon = 0;
	}

	protected Rates(int horizontal, int depth, int aim) {
		this.gamma = horizontal;
		this.epsilon = depth;
	}

	public void setGamma(int gamma) {
		this.gamma = gamma;
	}
	public int getGamma() {
		return gamma;
	}

	public void setEpsilon(int epsilon) {
		this.epsilon = epsilon;
	}
	public int getEpsilon() {
		return epsilon;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Rates)) { return false; }
        Rates other = (Rates) obj;
        
        if(this.gamma != other.gamma) { return false; }
        if(this.epsilon != other.epsilon) { return false; }
        
        return true;
    }
    
    @Override
    public String toString() { 
        return "Gamma: " + this.gamma + "\nEpsilon: " + this.epsilon;
    } 
}
