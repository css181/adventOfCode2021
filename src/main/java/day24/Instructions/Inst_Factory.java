package day24.Instructions;

public class Inst_Factory {

	public static I_Instruction create(String instruction) {
		String type = instruction.substring(0, 3).toLowerCase();
		switch (type) {
		case "inp":
			return new InpInstruction(instruction.substring(4));
		case "add":
			return new AddInstruction(instruction.substring(4));
		case "mul":
			return new MultiplyInstruction(instruction.substring(4));
		case "div":
			return new DivideInstruction(instruction.substring(4));
		case "mod":
			return new ModInstruction(instruction.substring(4));
		case "eql":
			return new EqualInstruction(instruction.substring(4));
		default:
			throw new RuntimeException("Unknown Instruction type: " + type);
		}
	}

}
