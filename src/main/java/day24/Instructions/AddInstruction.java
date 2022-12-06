package day24.Instructions;

import day24.GlobalVars;

public class AddInstruction extends Instruction {

	public AddInstruction(String toDo) {
		super(toDo);
	}

	@Override
	public void process() {
		int numToAdd = getSecondInputValue();
		
		switch (toDo.substring(0, 1)) {
		case "w":
			GlobalVars.w = GlobalVars.w + numToAdd;
			break;
		case "x":
			GlobalVars.x = GlobalVars.x + numToAdd;
			break;
		case "y":
			GlobalVars.y = GlobalVars.y + numToAdd;
			break;
		case "z":
			GlobalVars.z = GlobalVars.z + numToAdd;
			break;
		default:
			throw new RuntimeException("Unknown toDo for Add: " + toDo);
		}
	}

}
