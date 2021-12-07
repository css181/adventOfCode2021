package day7;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class Day7Part2Test {

	Day7Part2 day7 = new Day7Part2();
	
	
	@Test
	void use_different_input_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day7.setFileToUse(new File(fileName.getPath()));
		ArrayList<Long> expected = new ArrayList<Long>();
		expected.add(1l);
		expected.add(2l);
		expected.add(3l);
		expected.add(0l);
		expected.add(1l);
		expected.add(0l);
		expected.add(0l);
		expected.add(1l);
		expected.add(0l);
		expected.add(0l);
		expected.add(0l);
		expected.add(0l);
		expected.add(0l);
		expected.add(0l);
		expected.add(1l);
		expected.add(0l);
		expected.add(1l);
		
		day7.populateCrabPositions();
		assertEquals(expected, day7.getCrabPositions());
	}
	
	@Test
	void get_fuel_for_1Number() throws Exception {
		URL fileName = getClass().getResource("OneNumberInput.txt");
		day7.setFileToUse(new File(fileName.getPath()));
		day7.populateCrabPositions();
		long expected = 1;
		assertEquals(expected, day7.getFuelToGetAllTo(15));
		assertEquals(3, day7.getFuelToGetAllTo(14));
		assertEquals(6, day7.getFuelToGetAllTo(13));
		
		assertEquals(expected, day7.getFuelToGetAllTo(17));
		assertEquals(3, day7.getFuelToGetAllTo(18));
		assertEquals(6, day7.getFuelToGetAllTo(19));
	}
	
	@Test
	void get_fuel_consumpution() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day7.setFileToUse(new File(fileName.getPath()));
		day7.populateCrabPositions();
		long expected = 168;
		
		assertEquals(expected, day7.getFuelToGetAllTo(5));
	}
	
	
	@Test
	void part2_full_test() throws Exception {
		day7 = new Day7Part2();
		day7.populateCrabPositions();
		System.out.println("Most common is: " + day7.getBestFuelConsumptionIndex());
		System.out.println("Total fuel: " + day7.getFuelToGetAllTo(day7.getBestFuelConsumptionIndex()));
	}
}
