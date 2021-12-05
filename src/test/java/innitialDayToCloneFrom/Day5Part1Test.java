package innitialDayToCloneFrom;


import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.Test;


public class Day5Part1Test {

	Day5 day5 = new Day5();
	
	@Test
	void pojo_test() throws Exception {
		POJO pojo = new POJO();
	}
	
	
	@Test
	void use_different_input_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day5.setFileToUse(new File(fileName.getPath()));
	}
	
}
