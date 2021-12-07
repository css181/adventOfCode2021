package day7;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class Day7Test {

	Day7 day7 = new Day7();
	
	
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
	void get_fuel_consumpution() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day7.setFileToUse(new File(fileName.getPath()));
		day7.populateCrabPositions();
		long expected = 37;
		
		assertEquals(expected, day7.getFuelToGetAllTo(2));
	}
	
	@Test
	void find_best_fuel_consumption_value() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day7.setFileToUse(new File(fileName.getPath()));
		day7.populateCrabPositions();
		long expected = 2;
		
		assertEquals(expected, day7.getBestFuelConsumptionIndex());
	}
	
	@Test
	void part1_full_test() throws Exception {
		day7 = new Day7();
		day7.populateCrabPositions();
		System.out.println("Most common is: " + day7.getBestFuelConsumptionIndex());
		System.out.println("Total fuel: " + day7.getFuelToGetAllTo(day7.getBestFuelConsumptionIndex()));
	}
}
