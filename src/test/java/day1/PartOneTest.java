package day1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PartOneTest {

	private static GetIncreaseDecreaseCounts getIncreaseDecreaseCounts;
	@BeforeAll
	public static void setup() {
		getIncreaseDecreaseCounts = new GetIncreaseDecreaseCounts();
	}
	@Test
	void convertFileToArrayTest() {
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(4582);
		expected.add(4582);
		URL fileName = getClass().getResource("SingleSame.txt");
		ArrayList<Integer> actual = FileUtility.convertFileToIntArray(new File(fileName.getPath()));
		assertEquals(expected, actual);
	}

	@Test
	void countsObj_starts_at_all_0s() throws Exception {
		DepthCounts counts = new DepthCounts();
		assertEquals(0, counts.getIncreases());
		assertEquals(0, counts.getDecreases());
		assertEquals(0, counts.getSames());
	}
	
	@Test
	void calculate_With_SingleIncrease_will_result_in_1_Increases() throws Exception {
		URL fileName = getClass().getResource("SingleIncrease.txt");
		getIncreaseDecreaseCounts.setFileToUse(new File(fileName.getPath()));
		DepthCounts expected = new DepthCounts(1, 0, 0);
		DepthCounts actual = getIncreaseDecreaseCounts.calculateDepthCounts(1);
		assertEquals(expected, actual);
	}

	@Test
	void calculate_With_SingleDecrease_will_result_in_1_Decreases() throws Exception {
		URL fileName = getClass().getResource("SingleDecrease.txt");
		getIncreaseDecreaseCounts.setFileToUse(new File(fileName.getPath()));
		DepthCounts expected = new DepthCounts(0, 1, 0);
		DepthCounts actual = getIncreaseDecreaseCounts.calculateDepthCounts(1);
		assertEquals(expected, actual);
	}

	@Test
	void calculate_With_SingleDame_will_result_in_1_Sames() throws Exception {
		URL fileName = getClass().getResource("SingleSame.txt");
		getIncreaseDecreaseCounts.setFileToUse(new File(fileName.getPath()));
		DepthCounts expected = new DepthCounts(0, 0, 1);
		DepthCounts actual = getIncreaseDecreaseCounts.calculateDepthCounts(1);
		assertEquals(expected, actual);
	}
	
	@Test
	void get_actual_numbers() throws Exception {
		//Actually find the numbers
		getIncreaseDecreaseCounts = new GetIncreaseDecreaseCounts();
		DepthCounts actual = getIncreaseDecreaseCounts.calculateDepthCounts(1);
		System.out.println(actual); //Print them to the screen
		//double check after knowing:
		assertEquals(1316, actual.getIncreases());
		assertEquals(683, actual.getDecreases());
	}
	
}
