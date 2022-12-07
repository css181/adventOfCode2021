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
	}
	public void calculateLargestMONAD_resulting_in_z_equal_0() {
		long lowestZ = Integer.MAX_VALUE;
		ArrayList<Integer> largestMONAD;
		do {
			processInstructions();
			if(GlobalVars.z == 0) {
				lowestZ = GlobalVars.z;
				largestMONAD = GlobalVars.MONAD_input;
				System.out.println("Lowest: " +lowestZ + " for:" + largestMONAD);
				return; 
			} //we're done
			else {
				System.out.println(GlobalVars.print());
				decrementMONAD();
				GlobalVars.MONAD_index = 0;
				GlobalVars.w = 0;
				GlobalVars.x = 0;
				GlobalVars.y = 0;
				GlobalVars.z = 0;
			}
		} while (true);
	}
	private void decrementMONAD() {
		for(int pos=GlobalVars.MONAD_input.size()-1; pos>=0; pos--) {
			if(GlobalVars.MONAD_input.get(pos) > 1) {
				GlobalVars.MONAD_input.set(pos, GlobalVars.MONAD_input.get(pos)-1);
				for(int higherPos=pos+1; higherPos<=GlobalVars.MONAD_input.size()-1; higherPos++) {
					GlobalVars.MONAD_input.set(higherPos, 9);
				}
				return;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void calculateSmallestMONAD_resulting_in_z_equal_0() {
		long lowestZ = Integer.MAX_VALUE;
		ArrayList<Integer> smallestMONAD = new ArrayList<Integer>();
		do {
			processInstructions();
			System.out.println(GlobalVars.print());
			if(GlobalVars.z == 0) {
				lowestZ = GlobalVars.z;
				smallestMONAD = (ArrayList<Integer>) GlobalVars.MONAD_input.clone();
				System.out.println("\nLowest: " +lowestZ + " for:" + smallestMONAD);
				return; 
			} //we're done
			else {
				if(GlobalVars.z < lowestZ) {					
					lowestZ = GlobalVars.z;
					smallestMONAD = (ArrayList<Integer>) GlobalVars.MONAD_input.clone();
				}
				incrementMONAD();
				GlobalVars.MONAD_index = 0;
				GlobalVars.w = 0;
				GlobalVars.x = 0;
				GlobalVars.y = 0;
				GlobalVars.z = 0;
			}
		} while (true/*GlobalVars.MONAD_input.get(0)==1*/);
//		System.out.println("\nLowest: " +lowestZ + " for:" + smallestMONAD);
	}
	private void incrementMONAD() {
		int lastIndex = GlobalVars.MONAD_input.size()-1;
		GlobalVars.MONAD_input.set(lastIndex, GlobalVars.MONAD_input.get(lastIndex) + 1);
		for(int index=GlobalVars.MONAD_input.size()-1; index>=0; index--) {
			if(GlobalVars.MONAD_input.get(index)==10) {
				GlobalVars.MONAD_input.set(index, 1);
				GlobalVars.MONAD_input.set(index-1, GlobalVars.MONAD_input.get(index-1)+1);
			}
		}
	}
//	private void incrementMONAD() {
//		int lastIndex = GlobalVars.MONAD_input.size()-8;
//		GlobalVars.MONAD_input.set(lastIndex, GlobalVars.MONAD_input.get(lastIndex) + 1);
//		for(int index=lastIndex; index>=0; index--) {
//			if(GlobalVars.MONAD_input.get(index)==10) {
//				GlobalVars.MONAD_input.set(index, 1);
//				GlobalVars.MONAD_input.set(index-1, GlobalVars.MONAD_input.get(index-1)+1);
//			}
//		}
//	}
}
