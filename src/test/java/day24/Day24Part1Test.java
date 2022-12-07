package day24;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import day24.Instructions.AddInstruction;
import day24.Instructions.DivideInstruction;
import day24.Instructions.EqualInstruction;
import day24.Instructions.I_Instruction;
import day24.Instructions.InpInstruction;
import day24.Instructions.ModInstruction;
import day24.Instructions.MultiplyInstruction;


public class Day24Part1Test {

	Day24 day24;
	
	@BeforeEach
	public void setup() {
		day24 = new Day24();
	}
	
	@AfterEach
	public void tearDown() {
		GlobalVars.w = 0;
		GlobalVars.x = 0;
		GlobalVars.y = 0;
		GlobalVars.z = 0;
		GlobalVars.MONAD_input = new ArrayList<>();
		GlobalVars.MONAD_index = 0;
	}
	
	@Test
	void instructions_are_populated() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day24.setFileToUse(new File(fileName.getPath()));
		day24.setupInstructionsFromInputFile();
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("inp w");
		expected.add("add z w");
		expected.add("mod z 2");
		expected.add("div w 2");
		expected.add("add y w");
		expected.add("mod y 2");
		expected.add("div w 2");
		expected.add("add x w");
		expected.add("mod x 2");
		expected.add("div w 2");
		expected.add("mod w 2");
		assertEquals(expected, day24.getInstructionStrings());
	}
	
	@Test
	void all_GlobalVars_start_at_0() throws Exception {
		assertEquals(0, GlobalVars.w);
		assertEquals(0, GlobalVars.x);
		assertEquals(0, GlobalVars.y);
		assertEquals(0, GlobalVars.z);
	}
	
	@Test
	void inp_instructions_can_input_values_to_w_x_y_or_z() throws Exception {
		ArrayList<Integer> MONAD_input = new ArrayList<Integer>(Arrays.asList(4,5,6,7));
		GlobalVars.MONAD_input = MONAD_input;
		day24.setInstructions(new ArrayList<I_Instruction>(
				Arrays.asList(
						new InpInstruction("w"),
						new InpInstruction("x"),
						new InpInstruction("y"),
						new InpInstruction("z")
				)
			)
		);
		
		day24.processInstructions();
		
		assertEquals(4, GlobalVars.w);
		assertEquals(5, GlobalVars.x);
		assertEquals(6, GlobalVars.y);
		assertEquals(7, GlobalVars.z);
	}

	@Test
	void add_instructions_can_add_numbers_or_other_globals_and_assign_to_a_global() throws Exception {
		day24.setInstructions(new ArrayList<I_Instruction>(
				Arrays.asList(
						new AddInstruction("w 2"),//w=2
						new AddInstruction("x 3"),//x=3
						new AddInstruction("y 4"),//y=4
						new AddInstruction("z 5"),//z=5
						new AddInstruction("w x"),//w=2+3=5
						new AddInstruction("x y"),//x=3+4=7
						new AddInstruction("y z"),//y=4+5=9
						new AddInstruction("z w")//z=5+5=10
				)
			)
		);
		
		day24.processInstructions();
		
		assertEquals(5, GlobalVars.w);
		assertEquals(7, GlobalVars.x);
		assertEquals(9, GlobalVars.y);
		assertEquals(10, GlobalVars.z);
	}
	
	@Test
	void mul_instructions_can_multiply_numbers_or_other_globals_and_assign_to_a_global() throws Exception {
		ArrayList<Integer> MONAD_input = new ArrayList<Integer>(Arrays.asList(2,3,4,5));
		GlobalVars.MONAD_input = MONAD_input;
		day24.setInstructions(new ArrayList<I_Instruction>(
				Arrays.asList(
						new InpInstruction("w"),//w=2
						new InpInstruction("x"),//w=3
						new InpInstruction("y"),//w=4
						new InpInstruction("z"),//w=5
						new MultiplyInstruction("w 2"),//w=2*2=4
						new MultiplyInstruction("x 2"),//x=3*2=6
						new MultiplyInstruction("y 2"),//y=4*2=8
						new MultiplyInstruction("z 2"),//z=5*2=10
						new MultiplyInstruction("w x"),//w=4*6=24
						new MultiplyInstruction("x y"),//x=6*8=48
						new MultiplyInstruction("y z"),//y=8*10=80
						new MultiplyInstruction("z w")//z=10*24=240
				)
			)
		);
		
		day24.processInstructions();
		
		assertEquals(24, GlobalVars.w);
		assertEquals(48, GlobalVars.x);
		assertEquals(80, GlobalVars.y);
		assertEquals(240, GlobalVars.z);
	}
	
	@Test
	void div_instructions_can_divide_numbers_or_other_globals_and_assign_to_a_global() throws Exception {
		ArrayList<Integer> MONAD_input = new ArrayList<Integer>(Arrays.asList(20,19,18,17));
		GlobalVars.MONAD_input = MONAD_input;
		day24.setInstructions(new ArrayList<I_Instruction>(
				Arrays.asList(
						new InpInstruction("w"),//w=20
						new InpInstruction("x"),//w=19
						new InpInstruction("y"),//w=18
						new InpInstruction("z"),//w=17
						new DivideInstruction("w 2"),//w=20/2=10
						new DivideInstruction("x 2"),//x=19/2=9
						new DivideInstruction("y 4"),//y=18/4=4
						new DivideInstruction("z 6"),//z=17/6=2
						new DivideInstruction("w x"),//w=10/9=1
						new DivideInstruction("x y"),//x=9/4=2
						new DivideInstruction("y z"),//y=4/2=2
						new DivideInstruction("z w")//z=2/1=2
				)
			)
		);
		
		day24.processInstructions();
		
		assertEquals(1, GlobalVars.w);
		assertEquals(2, GlobalVars.x);
		assertEquals(2, GlobalVars.y);
		assertEquals(2, GlobalVars.z);
	}
	
	@Test
	void mod_instructions_can_modulus_numbers_or_other_globals_and_assign_to_a_global() throws Exception {
		ArrayList<Integer> MONAD_input = new ArrayList<Integer>(Arrays.asList(20,19,18,17));
		GlobalVars.MONAD_input = MONAD_input;
		day24.setInstructions(new ArrayList<I_Instruction>(
				Arrays.asList(
						new InpInstruction("w"),//w=20
						new InpInstruction("x"),//w=19
						new InpInstruction("y"),//w=18
						new InpInstruction("z"),//w=17
						new ModInstruction("w 15"),//w=20%15=5
						new ModInstruction("x 12"),//x=19%12=7
						new ModInstruction("y 5"),//y=18%5=3
						new ModInstruction("z 3"),//z=17%6=2
						new ModInstruction("w x"),//w=5%7=5
						new ModInstruction("x y"),//x=7%3=1
						new ModInstruction("y z"),//y=3%2=1
						new ModInstruction("z w")//z=2%5=2
				)
			)
		);
		
		day24.processInstructions();
		
		assertEquals(5, GlobalVars.w);
		assertEquals(1, GlobalVars.x);
		assertEquals(1, GlobalVars.y);
		assertEquals(2, GlobalVars.z);
	}
	
	@Test
	void eql_instructions_assigns_1_to_global_if_equal_and_0_if_not_equal() throws Exception {
		ArrayList<Integer> MONAD_input = new ArrayList<Integer>(Arrays.asList(20,19,18,17));
		GlobalVars.MONAD_input = MONAD_input;
		day24.setInstructions(new ArrayList<I_Instruction>(
				Arrays.asList(
						new EqualInstruction("w 0"),//w=1
						new EqualInstruction("x 0"),//x=1
						new EqualInstruction("y 5"),//y=0
						new EqualInstruction("z 3"),//z=0
						new EqualInstruction("w x"),//w=1
						new EqualInstruction("x y"),//x=0
						new EqualInstruction("y z"),//y=1
						new EqualInstruction("z w")//z=0
				)
			)
		);
		
		day24.processInstructions();
		
		assertEquals(1, GlobalVars.w);
		assertEquals(0, GlobalVars.x);
		assertEquals(1, GlobalVars.y);
		assertEquals(0, GlobalVars.z);
	}
	
	@Test
	void binary_process_11_from_sampleInput() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day24.setFileToUse(new File(fileName.getPath()));
		day24.setupInstructionsFromInputFile();
		GlobalVars.MONAD_input = new ArrayList<Integer>(Arrays.asList(11));
		//11 in binary = 1011
		
		day24.processInstructions();
		assertEquals(1, GlobalVars.w);
		assertEquals(0, GlobalVars.x);
		assertEquals(1, GlobalVars.y);
		assertEquals(1, GlobalVars.z);
	}
	@Test
	void binary_process_7_from_sampleInput() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day24.setFileToUse(new File(fileName.getPath()));
		day24.setupInstructionsFromInputFile();
		GlobalVars.MONAD_input = new ArrayList<Integer>(Arrays.asList(7));
		//7 in binary = 0111
		
		day24.processInstructions();
		assertEquals(0, GlobalVars.w);
		assertEquals(1, GlobalVars.x);
		assertEquals(1, GlobalVars.y);
		assertEquals(1, GlobalVars.z);
	}
	
	@Test //Get Part One Answer from final sysout()
	void get_largest_MONAD_number_with_no_zeros_that_finishes_processing_with_z_equal_to_0() throws Exception {
		day24.setupInstructionsFromInputFile();
		//Use the input to get patterns, we can start from a known max possible state and go down from there
		GlobalVars.MONAD_input = new ArrayList<Integer>(Arrays.asList(9,9,9,9,9,7,9,5,9,2,9,4,5,6));
		
		day24.calculateLargestMONAD_resulting_in_z_equal_0();
		//From input potential non-9s are indexes: 4,5,8,9,11,12,13
		//hasNeg:             *, *,       *, *,    *, *, *
		//Index:  0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13
		//Offset: 1, 9,12, 6, 9,15, 7,12,15, 3, 6, 2,10,12
		//Answer: 9, 9, 9, 9, 9, 7, 9, 5, 9, 1, 9, 4, 5, 6
	}
	
	@Test //Get Part Two Answer from final sysout()
	void get_lowest_MONAD_number_with_no_zeros_that_finishes_processing_with_z_equal_to_0() throws Exception {
		day24.setupInstructionsFromInputFile();
		//Use the input to get patterns, we can start from a known max possible state and go down from there
		GlobalVars.MONAD_input = new ArrayList<Integer>(Arrays.asList(4,5,3,1,1,1,8,1,5,1,6,1,1,1));
		
		day24.calculateSmallestMONAD_resulting_in_z_equal_0();
		//Answer: 4, 5, 3, 1, 1, 1, 9, 1, 5, 1, 6, 1, 1, 1
	}
}
