package day14;


public class Rule {

	private Character firstChar;
	private Character secondChar;
	private Character insertChar;
	
	public Rule() {
	}

	public Rule(Character firstChar, Character secondChar, Character insertChar) {
		super();
		this.firstChar = firstChar;
		this.secondChar = secondChar;
		this.insertChar = insertChar;
	}

	public Character getFirstChar() {
		return firstChar;
	}

	public Character getSecondChar() {
		return secondChar;
	}

	public Character getInsertChar() {
		return insertChar;
	}
	
	public boolean doesApply(Character first, Character second) {
		if(first.equals(firstChar) && second.equals(secondChar)) {
			return true;
		}
		return false;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Rule)) { return false; }
        Rule other = (Rule) obj;
        
        if(this.firstChar!=(other.firstChar)) { return false; }
        if(this.secondChar!=(other.secondChar)) { return false; }
        if(this.insertChar!=(other.insertChar)) { return false; }
        
        return true;
    }

	@Override
    public String toString() {
		String print = firstChar.toString() + secondChar.toString() + " -> " +
				insertChar.toString();
		return print;
    }
}
