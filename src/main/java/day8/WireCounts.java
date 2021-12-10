package day8;

public class WireCounts {
	private int a;
	private int b;
	private int c;
	private int d;
	private int e;
	private int f;
	private int g;
	
	public WireCounts() {
		super();
	}
	
    public WireCounts(int a, int b, int c, int d, int e, int f, int g) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
	}


	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof WireCounts)) { return false; }
        WireCounts other = (WireCounts) obj;
        
        if(this.a!=(other.a)) { return false; }
        if(this.b!=(other.b)) { return false; }
        if(this.c!=(other.c)) { return false; }
        if(this.d!=(other.d)) { return false; }
        if(this.e!=(other.e)) { return false; }
        if(this.f!=(other.f)) { return false; }
        if(this.g!=(other.g)) { return false; }
        
        return true;
    }
    
	@Override
    public String toString() {
		String print = "a:" + this.a + "\n" +
				"b:" + this.b + "\n" +
				"c:" + this.c + "\n" +
				"d:" + this.d + "\n" +
				"e:" + this.e + "\n" +
				"f:" + this.f + "\n" +
				"g:" + this.g;
		return print;
    }

	public String getLetterWithCount(int count) {
		String letter = "";
		if(a==count) {
			letter = "a";
		}else if (b==count) {
			letter = "b";
		}else if (c==count) {
			letter = "c";
		}else if (d==count) {
			letter = "d";
		}else if (e==count) {
			letter = "e";
		}else if (f==count) {
			letter = "f";
		}else if (g==count) {
			letter = "g";
		} else {
			throw new RuntimeException("No letter has the count: " + count);
		}
		return letter;
	}
}
