package day9;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;


public class Day9Part1Test {

	Day9 day9 = new Day9();
	
	@Test
	void smokeHeights_are_populated() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day9.setFileToUse(new File(fileName.getPath()));
		day9.setNumOfCols(10);
		day9.setNumOfRows(5);
		day9.getInputs();
		System.out.println(day9.getHeights());
		SmokeHeights expected = new SmokeHeights(5, 10);
		expected.populateRow(0, new ArrayList<>(Arrays.asList(2,1,9,9,9,4,3,2,1,0)));
		expected.populateRow(1, new ArrayList<>(Arrays.asList(3,9,8,7,8,9,4,9,2,1)));
		expected.populateRow(2, new ArrayList<>(Arrays.asList(9,8,5,6,7,8,9,8,9,2)));
		expected.populateRow(3, new ArrayList<>(Arrays.asList(8,7,6,7,8,9,6,7,8,9)));
		expected.populateRow(4, new ArrayList<>(Arrays.asList(9,8,9,9,9,6,5,6,7,8)));
		assertEquals(expected, day9.getHeights());
	}
	
	
	@Test
	void calculateLowPoints_will_give_true_if_no_lower_number_is_adjacent() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day9.setFileToUse(new File(fileName.getPath()));
		day9.setNumOfCols(10);
		day9.setNumOfRows(5);
		day9.getInputs();
		ArrayList<Integer> expectedLowsInRow0 = new ArrayList<>(Arrays.asList(1,0));
		ArrayList<Integer> expectedLowsInRow1 = new ArrayList<>(Arrays.asList());
		ArrayList<Integer> expectedLowsInRow2 = new ArrayList<>(Arrays.asList(5));
		ArrayList<Integer> expectedLowsInRow3 = new ArrayList<>(Arrays.asList());
		ArrayList<Integer> expectedLowsInRow4 = new ArrayList<>(Arrays.asList(5));
		
		assertEquals(expectedLowsInRow0, day9.getHeights().getListOfLowPointValuesInRow(0));
		assertEquals(expectedLowsInRow1, day9.getHeights().getListOfLowPointValuesInRow(1));
		assertEquals(expectedLowsInRow2, day9.getHeights().getListOfLowPointValuesInRow(2));
		assertEquals(expectedLowsInRow3, day9.getHeights().getListOfLowPointValuesInRow(3));
		assertEquals(expectedLowsInRow4, day9.getHeights().getListOfLowPointValuesInRow(4));
	}
	
	@Test
	void part1_sum_of_all_risk_level_is_15() throws Exception {
		//Note: Risk Level is (each lowPoint + 1) totaled
		URL fileName = getClass().getResource("SampleInput.txt");
		day9.setFileToUse(new File(fileName.getPath()));
		day9.setNumOfCols(10);
		day9.setNumOfRows(5);
		day9.getInputs();
		int expected = 15;
		
		assertEquals(expected, day9.getRiskTotal());
	}
	
	@Test
	void part1_full_test() throws Exception {
		day9 = new Day9();
		day9.getInputs();
		System.out.println(day9.getHeights());
		System.out.println("Total risk: " + day9.getRiskTotal());
	}
	
	@Test
	void basin_size_starts_at_lowPoint_and_goes_in_all_directions_until_a_9() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day9.setFileToUse(new File(fileName.getPath()));
		day9.setNumOfCols(10);
		day9.setNumOfRows(5);
		day9.getInputs();
		int expectedBasinOfTopLeft = 3;
		int expectedBasinOfTopRight = 9;
		int expectedBasinOfMiddle = 14;
		int expectedBasinOfBottomRight = 9;
		
		//We can get the basin lowPoint coordinates by looping through the isLowPointGrid
		ArrayList<Integer> basinSizes = new ArrayList<Integer>();
   		for(int row=0; row<day9.getHeights().getNumOfRows(); row++) {
   			for(int col=0; col<day9.getHeights().getNumOfColsForRow(row); col++) {
   				if(day9.getHeights().isLowPoint(row,col)) {
   					day9.getNewGridTrack();
   					basinSizes.add(day9.calculateBasinSizeAtLowPoint(row, col));
   				}
    		}
    	}
		assertEquals(expectedBasinOfTopLeft, basinSizes.get(0));
		assertEquals(expectedBasinOfTopRight, basinSizes.get(1));
		assertEquals(expectedBasinOfMiddle, basinSizes.get(2));
		assertEquals(expectedBasinOfBottomRight, basinSizes.get(3));
	}
	
	@Test
	void part2_full_test() throws Exception {
		day9 = new Day9();
		day9.getInputs();
		System.out.println(day9.getHeights());
		//We can get the basin lowPoint coordinates by looping through the isLowPointGrid
		ArrayList<Integer> basinSizes = new ArrayList<Integer>();
   		for(int row=0; row<day9.getHeights().getNumOfRows(); row++) {
   			for(int col=0; col<day9.getHeights().getNumOfColsForRow(row); col++) {
   				if(day9.getHeights().isLowPoint(row,col)) {
   					day9.getNewGridTrack();
   					basinSizes.add(day9.calculateBasinSizeAtLowPoint(row, col));
   				}
    		}
    	}
   		
   		//get the 3 largest:
   		Collections.sort(basinSizes);
   		int largest1 = basinSizes.get(basinSizes.size() - 1);
   		int largest2 = basinSizes.get(basinSizes.size() - 2);
   		int largest3 = basinSizes.get(basinSizes.size() - 3);
   		System.out.println("All basin sizes: " + basinSizes);
   		System.out.println("3 largest are: " + largest1 + ", " + largest2 + ", " + largest3);
   		System.out.println("Multiplied top 3 = " + (largest1*largest2*largest3));
   		//After knowing
   		assertEquals(1600104, (largest1*largest2*largest3));
	}
}
