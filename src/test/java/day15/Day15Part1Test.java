package day15;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

import org.junit.jupiter.api.Test;


public class Day15Part1Test {

	Day15 day15;
	
	@Test
	void can_read_in_SampleInput() throws Exception {
		day15 = new Day15();
		URL fileName = getClass().getResource("SampleInput.txt");
		day15.setFileToUse(new File(fileName.getPath()));
		
		int[][] expectedGrid = 
			{
					{1,1,6,3,7,5,1,7,4,2},
					{1,3,8,1,3,7,3,6,7,2},
					{2,1,3,6,5,1,1,3,2,8},
					{3,6,9,4,9,3,1,5,6,9},
					{7,4,6,3,4,1,7,1,1,1},
					{1,3,1,9,1,2,8,1,3,7},
					{1,3,5,9,9,1,2,4,2,1},
					{3,1,2,5,4,2,1,6,3,9},
					{1,2,9,3,1,3,8,5,2,1},
					{2,3,1,1,9,4,4,5,8,1},
			};
		
		day15.getInputs();
		for(int row=0; row<expectedGrid.length; row++) {
			assertEquals(expectedGrid[row].length, day15.getGrid()[row].length);
			for(int col=0; col<expectedGrid[row].length; col++) {
				assertEquals(expectedGrid[row][col], day15.getGrid()[row][col]);
			}
		}
	}
	
	@Test
	void bestPath_for_sample_has_sum_of_40() throws Exception {
		day15 = new Day15();
		URL fileName = getClass().getResource("SampleInput.txt");
		day15.setFileToUse(new File(fileName.getPath()));
		day15.getInputs();
		
		int actual = day15.getTotalOfBestPathFrom(0,0);
		assertEquals(40, actual);
	}
	
}
