package day24;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;
import day24.Instructions.I_Instruction;
import day24.Instructions.Inst_Factory;

public class Day24 {

	private static File file;
	ArrayList<String> instructionStrings;
	ArrayList<I_Instruction> instructions;
	public GlobalVars globalVars;
	
	public Day24() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		instructionStrings = new ArrayList<String>();
		instructions = new ArrayList<I_Instruction>();
		globalVars = new GlobalVars();
	}
	protected void setFileToUse(File file) {
		Day24.file = file;
	}
	
	public void setupInstructionsFromInputFile() {
		instructionStrings = FileUtility.convertFileToStringArray(file);
		for (String instruction : instructionStrings) {
			instructions.add(Inst_Factory.create(instruction));
		}
	}

	public ArrayList<String> getInstructionStrings() {
		return instructionStrings;
	}
	//Used Only for tests
	protected void setInstructions(ArrayList<I_Instruction> instructions) {
		this.instructions = instructions;
	}

	public GlobalVars getGlobalVars() {
		return globalVars;
	}
	
	
	public void processInstructions() {
		for (I_Instruction instruction : instructions) {
			instruction.process();
		}
		System.out.println(GlobalVars.print());
	}
	public void calculateLargestMONAD_resulting_in_z_equal_0() {
		do {
			processInstructions();
			if(GlobalVars.z == 0) { return; } //we're done
			else {
				decrementMONAD();
				GlobalVars.MONAD_index = 0;
			}
		} while (true);
	}
	private void decrementMONAD() {
		for(int pos=13; pos>=0; pos--) {
			if(GlobalVars.MONAD_input.get(pos) > 1) {
				GlobalVars.MONAD_input.set(pos, GlobalVars.MONAD_input.get(pos)-1);
				for(int higherPos=pos+1; higherPos<=13; higherPos++) {
					GlobalVars.MONAD_input.set(higherPos, 9);
				}
				return;
			}
		}
	}
}
