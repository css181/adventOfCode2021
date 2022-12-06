package day24.Instructions;

import day24.GlobalVars;

public class MultiplyInstruction extends Instruction {

	public MultiplyInstruction(String toDo) {
		super(toDo);
	}

	@Override
	public void process() {
		int numToMultiply = getSecondInputValue();
		
		switch (toDo.substring(0, 1)) {
		case "w":
			GlobalVars.w = GlobalVars.w * numToMultiply;
			break;
		case "x":
			GlobalVars.x = GlobalVars.x * numToMultiply;
			break;
		case "y":
			GlobalVars.y = GlobalVars.y * numToMultiply;
			break;
		case "z":
			GlobalVars.z = GlobalVars.z * numToMultiply;
			break;
		default:
			throw new RuntimeException("Unknown toDo for Add: " + toDo);
		}
	}

}
