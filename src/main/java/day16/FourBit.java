package day16;

public class FourBit {

	private int first;
	private int second;
	private int third;
	private int fourth;
	
	public FourBit() {
	}

	public FourBit(int first, int second, int third, int fourth) {
		super();
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}

	public int getFirst() {
		return first;
	}
	public int getSecond() {
		return second;
	}
	public int getThird() {
		return third;
	}
	public int getFourth() {
		return fourth;
	}
	
	@Override
	public int hashCode()
	{
	    return first+second+third+fourth;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof FourBit)) { return false; }
        FourBit other = (FourBit) obj;
        
        if(this.first!=(other.first)) { return false; }
        if(this.second!=(other.second)) { return false; }
        if(this.third!=(other.third)) { return false; }
        if(this.fourth!=(other.fourth)) { return false; }
        
        return true;
    }

	@Override
    public String toString() {
		String print = "[" + first + second + third + fourth + "]";
		return print;
    }
}
