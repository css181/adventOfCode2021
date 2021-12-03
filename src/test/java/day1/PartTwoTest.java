package day1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PartTwoTest {

	private static GetIncreaseDecreaseCounts getIncreaseDecreaseCounts;
	@BeforeAll
	public static void setup() {
		getIncreaseDecreaseCounts = new GetIncreaseDecreaseCounts();
	}

	
	@Test
	void calculate_With_TripleIncrease_will_result_in_1_Increases() throws Exception {
		URL fileName = getClass().getResource("TripleIncrease.txt");
		getIncreaseDecreaseCounts.setFileToUse(new File(fileName.getPath()));
		DepthCounts expected = new DepthCounts(1, 0, 0);
		DepthCounts actual = getIncreaseDecreaseCounts.calculateDepthCounts(3);
		assertEquals(expected, actual);
	}

	@Test
	void calculate_With_TripleDecrease_will_result_in_1_Decreases() throws Exception {
		URL fileName = getClass().getResource("TripleDecrease.txt");
		getIncreaseDecreaseCounts.setFileToUse(new File(fileName.getPath()));
		DepthCounts expected = new DepthCounts(0, 1, 0);
		DepthCounts actual = getIncreaseDecreaseCounts.calculateDepthCounts(3);
		assertEquals(expected, actual);
	}

	@Test
	void calculate_With_TripleSame_will_result_in_1_Sames() throws Exception {
		URL fileName = getClass().getResource("TripleSame.txt");
		getIncreaseDecreaseCounts.setFileToUse(new File(fileName.getPath()));
		DepthCounts expected = new DepthCounts(0, 0, 1);
		DepthCounts actual = getIncreaseDecreaseCounts.calculateDepthCounts(3);
		assertEquals(expected, actual);
	}
	
	@Test
	void get_actual_numbers() throws Exception {
		//Actually find the numbers
		getIncreaseDecreaseCounts = new GetIncreaseDecreaseCounts();
		DepthCounts actual = getIncreaseDecreaseCounts.calculateDepthCounts(3);
		System.out.println(actual); //Print them to the screen
		//double check after knowing:
		assertEquals(1344, actual.getIncreases());
		assertEquals(607, actual.getDecreases());
		assertEquals(46, actual.getSames());
	}
	
}
