package day17;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import day3.DecimalUtil;


public class Day17Part1Test {

	Day17 day17;
	
	@Test
	void can_create_new_ProbeFireArea_with_ship_in_0_0() throws Exception {
		day17 = new Day17();
		String expectedPrint = "" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"S..............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n";
		day17.defineArea(31, 11, -3);
		ProbeFireArea actual = day17.getArea();
		assertEquals(expectedPrint, actual.toString());
	}
	
	@Test
	void can_add_target_area_to_ProbeFireArea() throws Exception {
		day17 = new Day17();
		String expectedPrint = "" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"S..............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"...............................\n" +
				"....................TTTTTTTTTTT\n" +
				"....................TTTTTTTTTTT\n" +
				"....................TTTTTTTTTTT\n" +
				"....................TTTTTTTTTTT\n" +
				"....................TTTTTTTTTTT\n" +
				"....................TTTTTTTTTTT\n";
		day17.targetTopLeft = new Coordinate(20, -10);
		day17.targetBottomRight = new Coordinate(30, -5);
		day17.defineArea(31, 11, -3);
		day17.applyTargetToArea();
		assertEquals(expectedPrint, day17.getArea().toString());
	}
	
	@Test
	void can_process_fire_with_x_and_y_velocity_until_in_target() throws Exception {
		day17 = new Day17();
		String expectedPrint = "" +
				".............#....#............\n" +
				".......#..............#........\n" +
				"...............................\n" +
				"S........................#.....\n" +
				"...............................\n" +
				"...............................\n" +
				"...........................#...\n" +
				"...............................\n" +
				"....................TTTTTTTTTTT\n" +
				"....................TTTTTTTTTTT\n" +
				"....................TTTTTTTT#TT\n" +
				"....................TTTTTTTTTTT\n" +
				"....................TTTTTTTTTTT\n" +
				"....................TTTTTTTTTTT\n";
		day17.targetTopLeft = new Coordinate(20, -10);
		day17.targetBottomRight = new Coordinate(30, -5);
		day17.defineArea(31, 11, -3);
		day17.applyTargetToArea();
		day17.fireProbeWithVelocity(new Coordinate(7,2));
		assertEquals(expectedPrint, day17.getArea().toString());
	}
	
	@Test
	void verify_velocity_6_3_also_works() throws Exception {
		day17 = new Day17();
		String expectedPrint = "" +
				  "...............#..#............\n"
				+ "...........#........#..........\n"
				+ "...............................\n"
				+ "......#..............#.........\n"
				+ "...............................\n"
				+ "...............................\n"
				+ "S....................#.........\n"
				+ "...............................\n"
				+ "...............................\n"
				+ "...............................\n"
				+ ".....................#.........\n"
				+ "....................TTTTTTTTTTT\n"
				+ "....................TTTTTTTTTTT\n"
				+ "....................TTTTTTTTTTT\n"
				+ "....................TTTTTTTTTTT\n"
				+ "....................T#TTTTTTTTT\n"
				+ "....................TTTTTTTTTTT\n";
		day17.targetTopLeft = new Coordinate(20, -10);
		day17.targetBottomRight = new Coordinate(30, -5);
		day17.defineArea(31, 11, -6);
		day17.applyTargetToArea();
		int actual = day17.fireProbeWithVelocity(new Coordinate(6,3));
		assertEquals(expectedPrint, day17.getArea().toString());
		assertEquals(6, actual);
	}

	@Test
	void verify_velocity_9_0_also_works() throws Exception {
		day17 = new Day17();
		String expectedPrint = "" +
				  	"S........#.....................\n"
				  + ".................#.............\n"
				  + "...............................\n"
				  + "........................#......\n"
				  + "...............................\n"
				  + "....................TTTTTTTTTTT\n"
				  + "....................TTTTTTTTTT#\n"
				  + "....................TTTTTTTTTTT\n"
				  + "....................TTTTTTTTTTT\n"
				  + "....................TTTTTTTTTTT\n"
				  + "....................TTTTTTTTTTT\n";
		day17.targetTopLeft = new Coordinate(20, -10);
		day17.targetBottomRight = new Coordinate(30, -5);
		day17.defineArea(31, 11, 0);
		day17.applyTargetToArea();
		int actual = day17.fireProbeWithVelocity(new Coordinate(9,0));
		assertEquals(expectedPrint, day17.getArea().toString());
		assertEquals(0, actual);
	}
	
	@Test
	void will_stop_processing_steps_if_we_miss_before_minX_then_after_maxX() throws Exception {
		day17 = new Day17();
		day17.targetTopLeft = new Coordinate(20, -10);
		day17.targetBottomRight = new Coordinate(30, -5);
		day17.defineArea(31, 11, 0);
		day17.applyTargetToArea();
		int actual = day17.fireProbeWithVelocity(new Coordinate(17,-4));
		assertEquals(Integer.MIN_VALUE, actual);
	}

	@Test
	void will_stop_processing_steps_if_we_miss_before_minY_then_after_maxY() throws Exception {
		day17 = new Day17();
		day17.targetTopLeft = new Coordinate(20, -10);
		day17.targetBottomRight = new Coordinate(30, -5);
		day17.defineArea(31, 11, 0);
		day17.applyTargetToArea();
		int actual = day17.fireProbeWithVelocity(new Coordinate(11,-5));
		assertEquals(Integer.MIN_VALUE, actual);
	}
	
	@Test
	void verify_6_9_is_highest_ever_y_value_that_can_hit_target() throws Exception {
		day17 = new Day17();
		day17.targetTopLeft = new Coordinate(20, -10);
		day17.targetBottomRight = new Coordinate(30, -5);
		day17.defineArea();
		day17.applyTargetToArea();
		Coordinate expectedVelocity = new Coordinate(6, 11);
		
//		assertEquals(expectedVelocity, day17.getVelocityOfHighestYValueToHitTarget());
		day17.fireProbeWithVelocity(expectedVelocity);
		System.out.println(day17.getArea());
	}
}
