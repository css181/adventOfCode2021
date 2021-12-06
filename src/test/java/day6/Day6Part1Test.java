package day6;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class Day6Part1Test {

	Day6 day6 = new Day6();
	
	
	@Test
	void use_different_input_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day6.setFileToUse(new File(fileName.getPath()));
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(3);
		expected.add(4);
		expected.add(3);
		expected.add(1);
		expected.add(2);
		
		day6.populateFishArray();
		assertEquals(expected, day6.getSimpleFishList());
	}
	
	@Test
	void processDay_will_lower_each_count_by1() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day6.setFileToUse(new File(fileName.getPath()));
		day6.populateFishArray();
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(2);
		expected.add(3);
		expected.add(2);
		expected.add(0);
		expected.add(1);
		
		day6.processDay();
		assertEquals(expected, day6.getSimpleFishList());
	}
	
	@Test
	void processDay_will_also_reset_any_0_to_6_and_add_an_8_to_list() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day6.setFileToUse(new File(fileName.getPath()));
		day6.populateFishArray();
		//After 2 days
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(1);
		expected.add(6);
		expected.add(0);
		expected.add(8);
		
		day6.processDay();
		day6.processDay();
		assertEquals(expected, day6.getSimpleFishList());
	}
	
	@Test
	void fullSampleTest() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day6.setFileToUse(new File(fileName.getPath()));
		day6.populateFishArray();
		int expectedAfter18Days = 26;
		for(int x=0; x<18; x++) {
			day6.processDay();
		}
		assertEquals(expectedAfter18Days, day6.getSimpleFishList().size());
		
		day6.populateFishArray();
		int expectedAfter80Days = 5934;
		for(int x=0; x<80; x++) {
			day6.processDay();
		}
		assertEquals(expectedAfter80Days, day6.getSimpleFishList().size());
	}
	
	@Test
	void part1_realInput_test() throws Exception {
		day6 = new Day6();
		day6.populateFishArray();
		for(int x=0; x<80; x++) {
			day6.processDay();
		}
		System.out.println("After 80 days: " + day6.getTotalFishCount() + " fish.");
		//After Known
		assertEquals(380612, day6.getTotalFishCount());
	}
	
	@Test
	void after_10000_fish_we_will_add_to_next_array() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day6.setFileToUse(new File(fileName.getPath()));
		day6.populateFishArray();
		long expectedAfter90Days = 14003;
		for(int x=0; x<90; x++) {
			day6.processDay();
		}
		assertEquals(10000, day6.getFullFishList().get(0).size());
		assertEquals(4003, day6.getFullFishList().get(1).size());
		assertEquals(expectedAfter90Days, day6.getTotalFishCount());
	}
	
	@Test
	@Disabled("This test takes DAYS to run.")
	void part2_sample_after_250_days() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day6.setFileToUse(new File(fileName.getPath()));
		day6.populateFishArray();
		long expectedAfter250Days = 26984457539l;
		for(int x=0; x<250; x++) {
			day6.processDay();
		}
		assertEquals(expectedAfter250Days, day6.getTotalFishCount());
	}
}
