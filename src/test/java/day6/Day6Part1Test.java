package day6;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

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
		assertEquals(expected, day6.getFishList());
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
		assertEquals(expected, day6.getFishList());
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
		assertEquals(expected, day6.getFishList());
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
		assertEquals(expectedAfter18Days, day6.getTotalFishCount());
		
		day6.populateFishArray();
		int expectedAfter80Days = 5934;
		for(int x=0; x<80; x++) {
			day6.processDay();
		}
		assertEquals(expectedAfter80Days, day6.getTotalFishCount());
	}
	
//	@Test
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
	void a_fish_will_reproduce_once_every_seven_days_plus_that_will_reproduce_after_8() throws Exception {
//		int original =0;
//		assertEquals(original, day6.getNumberOfReproductionsForStartOverXDays(3,1));
//		assertEquals(original, day6.getNumberOfReproductionsForStartOverXDays(3,2));
//		assertEquals(original, day6.getNumberOfReproductionsForStartOverXDays(3,3));
//		original=1;
//		int baby1_1=0;
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,4));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,5));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,6));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,7));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,8));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,9));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,10));
//		original=2;
//		int baby_2_1=0;
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,11));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,12));
//		baby1_1=1;
//		int baby1_1_1=0;
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,13));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,14));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,15));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,16));
//		assertEquals(original+baby1_1, day6.getNumberOfReproductionsForStartOverXDays(3,17));
//		original=3;
//		int baby3_1=0;
		assertEquals(6, day6.getNumberOfReproductionsForStartOverXDays(0,18));
		assertEquals(6, day6.getNumberOfReproductionsForStartOverXDays(1,18));
		assertEquals(4, day6.getNumberOfReproductionsForStartOverXDays(2,18));
		assertEquals(4, day6.getNumberOfReproductionsForStartOverXDays(3,18));
		assertEquals(3, day6.getNumberOfReproductionsForStartOverXDays(4,18));
		assertEquals(3, day6.getNumberOfReproductionsForStartOverXDays(5,18));
		assertEquals(3, day6.getNumberOfReproductionsForStartOverXDays(6,18));
		
		assertEquals(7, day6.getNumberOfReproductionsForStartOverXDays(0,19));
		assertEquals(6, day6.getNumberOfReproductionsForStartOverXDays(1,19));
		assertEquals(6, day6.getNumberOfReproductionsForStartOverXDays(2,19));
		assertEquals(4, day6.getNumberOfReproductionsForStartOverXDays(3,19));
		assertEquals(4, day6.getNumberOfReproductionsForStartOverXDays(4,19));
		assertEquals(3, day6.getNumberOfReproductionsForStartOverXDays(5,19));
		assertEquals(3, day6.getNumberOfReproductionsForStartOverXDays(6,19));
	}
	
	@Test
	void part1_answer_via_recursion() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day6.setFileToUse(new File(fileName.getPath()));
		day6.populateFishArray();
		int expectedAfter18Days = 26;
		int days = 18;
		int actualTotalFish = (int) day6.getTotalFishCount();
		for(int curFish=0; curFish<day6.getTotalFishCount(); curFish++) {
			actualTotalFish+=day6.getNumberOfReproductionsForStartOverXDays(day6.getFishList().get(curFish), days);
		}
		assertEquals(expectedAfter18Days, actualTotalFish);
		
		day6.populateFishArray();
		int expectedAfter80Days = 5934;
		days = 80;
		actualTotalFish = (int) day6.getTotalFishCount();
		for(int curFish=0; curFish<day6.getTotalFishCount(); curFish++) {
			actualTotalFish+=day6.getNumberOfReproductionsForStartOverXDays(day6.getFishList().get(curFish), days);
		}
		assertEquals(expectedAfter80Days, actualTotalFish);
	}
	
	@Test
	void part2_performant() throws Exception {
		Day6Performant day6 = new Day6Performant();
		day6.populateFishArray();
		long expected = 1710166656900l;
		for(int x=0; x<256; x++) {
			day6.processDay();
		}
		assertEquals(expected, day6.getTotalFishCount());
	}
}
