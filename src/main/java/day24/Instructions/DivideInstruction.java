package day24.Instructions;

import day24.GlobalVars;

public class DivideInstruction extends Instruction {

	public DivideInstruction(String toDo) {
		super(toDo);
	}

	@Override
	public void process() {
		int numToDivideBy = getSecondInputValue();
		
		switch (toDo.substring(0, 1)) {
		case "w":
			GlobalVars.w = GlobalVars.w / numToDivideBy;
			break;
		case "x":
			GlobalVars.x = GlobalVars.x / numToDivideBy;
			break;
		case "y":
			GlobalVars.y = GlobalVars.y / numToDivideBy;
			break;
		case "z":
			GlobalVars.z = GlobalVars.z / numToDivideBy;
			break;
		default:
			throw new RuntimeException("Unknown toDo for Add: " + toDo);
		}
	}

}
