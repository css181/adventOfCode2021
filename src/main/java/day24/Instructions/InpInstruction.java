package day24.Instructions;

import day24.GlobalVars;

public class InpInstruction extends Instruction {

	public InpInstruction(String toDo) {
		super(toDo);
	}

	@Override
	public void process() {
		switch (toDo) {
		case "w":
			GlobalVars.w = GlobalVars.MONAD_input.get(GlobalVars.MONAD_index++);
			break;
		case "x":
			GlobalVars.x = GlobalVars.MONAD_input.get(GlobalVars.MONAD_index++);
			break;
		case "y":
			GlobalVars.y = GlobalVars.MONAD_input.get(GlobalVars.MONAD_index++);
			break;
		case "z":
			GlobalVars.z = GlobalVars.MONAD_input.get(GlobalVars.MONAD_index++);
			break;
		default:
			throw new RuntimeException("Unknown toDo for Inp: " + toDo);
		}
	}

}
