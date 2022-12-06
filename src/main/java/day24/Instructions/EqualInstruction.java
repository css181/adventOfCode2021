package day24.Instructions;

import day24.GlobalVars;

public class EqualInstruction extends Instruction {

	public EqualInstruction(String toDo) {
		super(toDo);
	}

	@Override
	public void process() {
		int numToMultiply = getSecondInputValue();
		
		switch (toDo.substring(0, 1)) {
		case "w":
			GlobalVars.w = GlobalVars.w == numToMultiply ? 1:0;
			break;
		case "x":
			GlobalVars.x = GlobalVars.x == numToMultiply ? 1:0;
			break;
		case "y":
			GlobalVars.y = GlobalVars.y == numToMultiply ? 1:0;
			break;
		case "z":
			GlobalVars.z = GlobalVars.z == numToMultiply ? 1:0;
			break;
		default:
			throw new RuntimeException("Unknown toDo for Add: " + toDo);
		}
	}

}
