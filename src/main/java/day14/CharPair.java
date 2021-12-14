package day14;

public class CharPair {

	private Character firstChar;
	private Character secondChar;
	
	public CharPair() {
	}

	public CharPair(Character firstChar, Character secondChar) {
		super();
		this.firstChar = firstChar;
		this.secondChar = secondChar;
	}

	public Character getFirstChar() {
		return firstChar;
	}

	public Character getSecondChar() {
		return secondChar;
	}
	
	@Override
	public int hashCode()
	{
	    return firstChar.hashCode() + secondChar.hashCode();
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof CharPair)) { return false; }
        CharPair other = (CharPair) obj;
        
        if(this.firstChar!=(other.firstChar)) { return false; }
        if(this.secondChar!=(other.secondChar)) { return false; }
        
        return true;
    }

	@Override
    public String toString() {
		String print = "[" + firstChar.toString() + secondChar.toString() + "]";
		return print;
    }
}
