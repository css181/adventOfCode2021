package day24.Instructions;

import day24.GlobalVars;

public abstract class Instruction implements I_Instruction {

	abstract public void process();
	
	protected String toDo;

	protected String getToDo() {
		return toDo;
	}

	protected void setToDo(String toDo) {
		this.toDo = toDo;
	}

	public Instruction(String toDo) {
		super();
		this.toDo = toDo;
	}

	protected int getSecondInputValue() {
		switch (toDo.substring(toDo.lastIndexOf(" ")+1)) {
		case "w":
			return GlobalVars.w;
		case "x":
			return GlobalVars.x;
		case "y":
			return GlobalVars.y;
		case "z":
			return GlobalVars.z;
		default:
			return Integer.valueOf(toDo.substring(toDo.lastIndexOf(" ")+1));
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " ~ " + toDo;
	}
}
