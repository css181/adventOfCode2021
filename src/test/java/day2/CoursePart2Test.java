package day2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class CoursePart2Test {

	Course coarse = new Course();
	
	@Test
	void new_positionPlot_starts_at_0_0_0() throws Exception {
		PositionPlot positionPlot = new PositionPlot();
		assertEquals(0, positionPlot.getDepth());
		assertEquals(0, positionPlot.getHorizontal());
		assertEquals(0, positionPlot.getAim());
	}
	
	@Test
	void up_will_lower_aim_only() throws Exception {
		URL fileName = getClass().getResource("UpInput.txt");
		coarse.setFileToUse(new File(fileName.getPath()));
		PositionPlot expected = new PositionPlot(0, 0, -2);
		
		PositionPlot actual = coarse.travel();
		assertEquals(expected, actual);
	}
	
	@Test
	void down_will_increase_aim_only() throws Exception {
		URL fileName = getClass().getResource("DownInput.txt");
		coarse.setFileToUse(new File(fileName.getPath()));
		PositionPlot expected = new PositionPlot(0, 0, 9);
		
		PositionPlot actual = coarse.travel();
		assertEquals(expected, actual);
	}

	@Test
	void forward_will_increase_horizontal() throws Exception {
		URL fileName = getClass().getResource("ForwardInput.txt");
		coarse.setFileToUse(new File(fileName.getPath()));
		PositionPlot expected = new PositionPlot(9, 0, 0);
		
		PositionPlot actual = coarse.travel();
		assertEquals(expected, actual);
	}

	@Test
	void forward_will_increase_horizontal_and_add_to_depth_magnitude_times_aim() throws Exception {
		URL fileName = getClass().getResource("DownForwardInput.txt");
		coarse.setFileToUse(new File(fileName.getPath()));
		PositionPlot expected = new PositionPlot(9, 81, 9);
		
		PositionPlot actual = coarse.travel();
		assertEquals(expected, actual);
	}
	
	@Test
	void get_actual_position() throws Exception {
		coarse = new Course();
		PositionPlot actual = coarse.travel();
		System.out.println(actual);
		System.out.println("multiplied together: " + actual.getDepth() * actual.getHorizontal());
		//double check after knowing
		assertEquals(2199, actual.getHorizontal());
		assertEquals(802965, actual.getDepth());
	}
}
