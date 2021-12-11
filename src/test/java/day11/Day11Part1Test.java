package day11;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class Day11Part1Test {

	Day11 day11 = new Day11();
	
	@Test
	void use_different_input_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day11.setFileToUse(new File(fileName.getPath()));
		day11.getInputs();
		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> expectedLine1 = new ArrayList<>(Arrays.asList(5,4,8,3,1,4,3,2,2,3));
		expected.add(expectedLine1);
		ArrayList<Integer> expectedLine2 = new ArrayList<>(Arrays.asList(2,7,4,5,8,5,4,7,1,1));
		expected.add(expectedLine2);
		ArrayList<Integer> expectedLine3 = new ArrayList<>(Arrays.asList(5,2,6,4,5,5,6,1,7,3));
		expected.add(expectedLine3);
		ArrayList<Integer> expectedLine4 = new ArrayList<>(Arrays.asList(6,1,4,1,3,3,6,1,4,6));
		expected.add(expectedLine4);
		ArrayList<Integer> expectedLine5 = new ArrayList<>(Arrays.asList(6,3,5,7,3,8,5,4,7,8));
		expected.add(expectedLine5);
		ArrayList<Integer> expectedLine6 = new ArrayList<>(Arrays.asList(4,1,6,7,5,2,4,6,4,5));
		expected.add(expectedLine6);
		ArrayList<Integer> expectedLine7 = new ArrayList<>(Arrays.asList(2,1,7,6,8,4,1,7,2,1));
		expected.add(expectedLine7);
		ArrayList<Integer> expectedLine8 = new ArrayList<>(Arrays.asList(6,8,8,2,8,8,1,1,3,4));
		expected.add(expectedLine8);
		ArrayList<Integer> expectedLine9 = new ArrayList<>(Arrays.asList(4,8,4,6,8,4,8,5,5,4));
		expected.add(expectedLine9);
		ArrayList<Integer> expectedLine10 = new ArrayList<>(Arrays.asList(5,2,8,3,7,5,1,5,2,6));
		expected.add(expectedLine10);

		assertEquals(expected, day11.getOctopusValues());
	}
	
	@Test
	void steps_will_add_1_to_each_value_if_nothing_flashes() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day11.setFileToUse(new File(fileName.getPath()));
		day11.getInputs();
		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> expectedLine1 = new ArrayList<>(Arrays.asList(6,5,9,4,2,5,4,3,3,4));
		expected.add(expectedLine1);
		ArrayList<Integer> expectedLine2 = new ArrayList<>(Arrays.asList(3,8,5,6,9,6,5,8,2,2));
		expected.add(expectedLine2);
		ArrayList<Integer> expectedLine3 = new ArrayList<>(Arrays.asList(6,3,7,5,6,6,7,2,8,4));
		expected.add(expectedLine3);
		ArrayList<Integer> expectedLine4 = new ArrayList<>(Arrays.asList(7,2,5,2,4,4,7,2,5,7));
		expected.add(expectedLine4);
		ArrayList<Integer> expectedLine5 = new ArrayList<>(Arrays.asList(7,4,6,8,4,9,6,5,8,9));
		expected.add(expectedLine5);
		ArrayList<Integer> expectedLine6 = new ArrayList<>(Arrays.asList(5,2,7,8,6,3,5,7,5,6));
		expected.add(expectedLine6);
		ArrayList<Integer> expectedLine7 = new ArrayList<>(Arrays.asList(3,2,8,7,9,5,2,8,3,2));
		expected.add(expectedLine7);
		ArrayList<Integer> expectedLine8 = new ArrayList<>(Arrays.asList(7,9,9,3,9,9,2,2,4,5));
		expected.add(expectedLine8);
		ArrayList<Integer> expectedLine9 = new ArrayList<>(Arrays.asList(5,9,5,7,9,5,9,6,6,5));
		expected.add(expectedLine9);
		ArrayList<Integer> expectedLine10 = new ArrayList<>(Arrays.asList(6,3,9,4,8,6,2,6,3,7));
		expected.add(expectedLine10);

		day11.processStep();
		
		assertEquals(expected, day11.getOctopusValues());
	}
	
	@Test
	void flashes_give_all_adjacent_an_extra_energy_and_go_to_0() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day11.setFileToUse(new File(fileName.getPath()));
		day11.getInputs();
		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> expectedLine1 = new ArrayList<>(Arrays.asList(8,8,0,7,4,7,6,5,5,5));
		expected.add(expectedLine1);
		ArrayList<Integer> expectedLine2 = new ArrayList<>(Arrays.asList(5,0,8,9,0,8,7,0,5,4));
		expected.add(expectedLine2);
		ArrayList<Integer> expectedLine3 = new ArrayList<>(Arrays.asList(8,5,9,7,8,8,9,6,0,8));
		expected.add(expectedLine3);
		ArrayList<Integer> expectedLine4 = new ArrayList<>(Arrays.asList(8,4,8,5,7,6,9,6,0,0));
		expected.add(expectedLine4);
		ArrayList<Integer> expectedLine5 = new ArrayList<>(Arrays.asList(8,7,0,0,9,0,8,8,0,0));
		expected.add(expectedLine5);
		ArrayList<Integer> expectedLine6 = new ArrayList<>(Arrays.asList(6,6,0,0,0,8,8,9,8,9));
		expected.add(expectedLine6);
		ArrayList<Integer> expectedLine7 = new ArrayList<>(Arrays.asList(6,8,0,0,0,0,5,9,4,3));
		expected.add(expectedLine7);
		ArrayList<Integer> expectedLine8 = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,7,4,5,6));
		expected.add(expectedLine8);
		ArrayList<Integer> expectedLine9 = new ArrayList<>(Arrays.asList(9,0,0,0,0,0,0,8,7,6));
		expected.add(expectedLine9);
		ArrayList<Integer> expectedLine10 = new ArrayList<>(Arrays.asList(8,7,0,0,0,0,6,8,4,8));
		expected.add(expectedLine10);

		day11.processStep();
		day11.processStep();
		
		assertEquals(expected, day11.getOctopusValues());
	}
	
	@Test
	void test_sample_ten_steps() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day11.setFileToUse(new File(fileName.getPath()));
		day11.getInputs();
		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> expectedLine1 = new ArrayList<>(Arrays.asList(0,4,8,1,1,1,2,9,7,6));
		expected.add(expectedLine1);
		ArrayList<Integer> expectedLine2 = new ArrayList<>(Arrays.asList(0,0,3,1,1,1,2,0,0,9));
		expected.add(expectedLine2);
		ArrayList<Integer> expectedLine3 = new ArrayList<>(Arrays.asList(0,0,4,1,1,1,2,5,0,4));
		expected.add(expectedLine3);
		ArrayList<Integer> expectedLine4 = new ArrayList<>(Arrays.asList(0,0,8,1,1,1,1,4,0,6));
		expected.add(expectedLine4);
		ArrayList<Integer> expectedLine5 = new ArrayList<>(Arrays.asList(0,0,9,9,1,1,1,3,0,6));
		expected.add(expectedLine5);
		ArrayList<Integer> expectedLine6 = new ArrayList<>(Arrays.asList(0,0,9,3,5,1,1,2,3,3));
		expected.add(expectedLine6);
		ArrayList<Integer> expectedLine7 = new ArrayList<>(Arrays.asList(0,4,4,2,3,6,1,1,3,0));
		expected.add(expectedLine7);
		ArrayList<Integer> expectedLine8 = new ArrayList<>(Arrays.asList(5,5,3,2,2,5,2,3,5,0));
		expected.add(expectedLine8);
		ArrayList<Integer> expectedLine9 = new ArrayList<>(Arrays.asList(0,5,3,2,2,5,0,6,0,0));
		expected.add(expectedLine9);
		ArrayList<Integer> expectedLine10 = new ArrayList<>(Arrays.asList(0,0,3,2,2,4,0,0,0,0));
		expected.add(expectedLine10);

		for(int steps=0; steps<10; steps++) {
			day11.processStep();
		}
		
		assertEquals(expected, day11.getOctopusValues());
	}
	
	@Test
	void samples_100steps_produces_1656_flashes() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day11.setFileToUse(new File(fileName.getPath()));
		day11.getInputs();
		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> expectedLine1 = new ArrayList<>(Arrays.asList(0,3,9,7,6,6,6,8,6,6));
		expected.add(expectedLine1);
		ArrayList<Integer> expectedLine2 = new ArrayList<>(Arrays.asList(0,7,4,9,7,6,6,9,1,8));
		expected.add(expectedLine2);
		ArrayList<Integer> expectedLine3 = new ArrayList<>(Arrays.asList(0,0,5,3,9,7,6,9,3,3));
		expected.add(expectedLine3);
		ArrayList<Integer> expectedLine4 = new ArrayList<>(Arrays.asList(0,0,0,4,2,9,7,8,2,2));
		expected.add(expectedLine4);
		ArrayList<Integer> expectedLine5 = new ArrayList<>(Arrays.asList(0,0,0,4,2,2,9,8,9,2));
		expected.add(expectedLine5);
		ArrayList<Integer> expectedLine6 = new ArrayList<>(Arrays.asList(0,0,5,3,2,2,2,8,7,7));
		expected.add(expectedLine6);
		ArrayList<Integer> expectedLine7 = new ArrayList<>(Arrays.asList(0,5,3,2,2,2,2,9,6,6));
		expected.add(expectedLine7);
		ArrayList<Integer> expectedLine8 = new ArrayList<>(Arrays.asList(9,3,2,2,2,2,8,9,6,6));
		expected.add(expectedLine8);
		ArrayList<Integer> expectedLine9 = new ArrayList<>(Arrays.asList(7,9,2,2,2,8,6,8,6,6));
		expected.add(expectedLine9);
		ArrayList<Integer> expectedLine10 = new ArrayList<>(Arrays.asList(6,7,8,9,9,9,8,7,6,6));
		expected.add(expectedLine10);

		for(int steps=0; steps<100; steps++) {
			day11.processStep();
		}
		
		assertEquals(expected, day11.getOctopusValues());
		assertEquals(1656, day11.getTotalFlashes());
	}
	
	@Test
	void part1_real_run() throws Exception {
		day11 = new Day11();
		day11.getInputs();
		for(int steps=0; steps<100; steps++) {
			day11.processStep();
		}
		System.out.println("Total Flashes: " + day11.getTotalFlashes());
		//Add assert after known
		assertEquals(1620, day11.getTotalFlashes());
	}
	
	@Test
	void get_first_time_all_flash_sample_should_be_step_195() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day11.setFileToUse(new File(fileName.getPath()));
		day11.getInputs();

		int step=0;
		do {
			step++;
			day11.processStep();
			if(day11.areAllFlashing()) {
				break;
			}
		}while(step<200);
		
		assertEquals(195, step);
	}
	

	@Test
	void part2_full_run() throws Exception {
		day11 = new Day11();
		day11.getInputs();

		int step=0;
		do {
			step++;
			day11.processStep();
			if(day11.areAllFlashing()) {
				break;
			}
		}while(step<500);
		
		System.out.println("First time all Flashing: " + step);
		//Add assert after known
		assertEquals(371, step);
	}
}
