package day5;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class Day5Part1Test {

	Day5 day5 = new Day5();
	
	@Test
	void can_print_grid_of_10x10_dots() throws Exception {
		Grid grid = new Grid(10);
		System.out.println(grid);
		String expected = ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n";
		assertEquals(expected, grid.toString());
	}
	
	@Test
	void can_read_in_Lines_from_input() throws Exception {
		URL fileName = getClass().getResource("testInput.txt");
		day5.setFileToUse(new File(fileName.getPath()));
		ArrayList<Line> expected = new ArrayList<Line>();
		Line line = new Line(new Coordinate(0,9), new Coordinate(5,9));
		expected.add(line);
		line = new Line(new Coordinate(8,0), new Coordinate(0,8));
		expected.add(line);
		line = new Line(new Coordinate(9,4), new Coordinate(3,4));
		expected.add(line);
		line = new Line(new Coordinate(2,2), new Coordinate(2,1));
		expected.add(line);
		line = new Line(new Coordinate(7,0), new Coordinate(7,4));
		expected.add(line);
		line = new Line(new Coordinate(6,4), new Coordinate(2,0));
		expected.add(line);
		line = new Line(new Coordinate(0,9), new Coordinate(2,9));
		expected.add(line);
		line = new Line(new Coordinate(3,4), new Coordinate(1,4));
		expected.add(line);
		line = new Line(new Coordinate(0,0), new Coordinate(8,8));
		expected.add(line);
		line = new Line(new Coordinate(5,5), new Coordinate(8,2));
		expected.add(line);
		
		ArrayList<Line> actual = day5.readInLines();
		assertEquals(expected, actual);
	}
	
	@Test
	void can_filter_only_x_or_y_same_lines() throws Exception {
		URL fileName = getClass().getResource("testInput.txt");
		day5.setFileToUse(new File(fileName.getPath()));
		ArrayList<Line> expected = new ArrayList<Line>();
		Line line = new Line(new Coordinate(0,9), new Coordinate(5,9));
		expected.add(line);
		line = new Line(new Coordinate(9,4), new Coordinate(3,4));
		expected.add(line);
		line = new Line(new Coordinate(2,2), new Coordinate(2,1));
		expected.add(line);
		line = new Line(new Coordinate(7,0), new Coordinate(7,4));
		expected.add(line);
		line = new Line(new Coordinate(0,9), new Coordinate(2,9));
		expected.add(line);
		line = new Line(new Coordinate(3,4), new Coordinate(1,4));
		expected.add(line);
		
		ArrayList<Line> allLines = day5.readInLines();
		
		ArrayList<Line> actual = day5.filterForSameXorY(allLines);
		assertEquals(expected, actual);
	}
	
	@Test
	void addLines_to_Grid() throws Exception {
		URL fileName = getClass().getResource("testInput.txt");
		day5.setFileToUse(new File(fileName.getPath()));
		day5.setGrid(10);
		String expected = ". . . . . . . 1 . . \n"
				+ ". . 1 . . . . 1 . . \n"
				+ ". . 1 . . . . 1 . . \n"
				+ ". . . . . . . 1 . . \n"
				+ ". 1 1 2 1 1 1 2 1 1 \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ "2 2 2 1 1 1 . . . . \n";
		
		ArrayList<Line> allLines = day5.readInLines();
		ArrayList<Line> filteredLines = day5.filterForSameXorY(allLines);
		
		day5.addLinesToGrid(filteredLines);
		
		assertEquals(expected, day5.getGrid().toString());
	}
	
	@Test
	void count_all_high_items_in_grid() throws Exception {
		URL fileName = getClass().getResource("testInput.txt");
		day5.setFileToUse(new File(fileName.getPath()));
		day5.setGrid(10);
		ArrayList<Line> allLines = day5.readInLines();
		ArrayList<Line> filteredLines = day5.filterForSameXorY(allLines);
		day5.addLinesToGrid(filteredLines);
		int highNumberToLookFor = 2;
		int expected = 5;
		
		int actual = day5.getGrid().findAllInstancesAbove(highNumberToLookFor);
		assertEquals(expected, actual);
	}
	
//	@Test
	void do_full_input() throws Exception {
		day5 = new Day5();
		ArrayList<Line> allLines = day5.readInLines();
		ArrayList<Line> filteredLines = day5.filterForSameXorY(allLines);
		
		day5.addLinesToGrid(filteredLines);
		int actual = day5.getGrid().findAllInstancesAbove(2);
		System.out.println("Number of 2's or greater: " + actual);
	}
	
	//******************************* PART 2 **********************//
	@Test
	void can_recognize_diagonal_line() throws Exception {
		URL fileName = getClass().getResource("testInput.txt");
		day5.setFileToUse(new File(fileName.getPath()));
		ArrayList<Line> expected = new ArrayList<Line>();
		Line line = new Line(new Coordinate(8,0), new Coordinate(0,8));
		expected.add(line);
		line = new Line(new Coordinate(6,4), new Coordinate(2,0));
		expected.add(line);
		line = new Line(new Coordinate(0,0), new Coordinate(8,8));
		expected.add(line);
		line = new Line(new Coordinate(5,5), new Coordinate(8,2));
		expected.add(line);
		
		ArrayList<Line> allInputs = day5.readInLines();
		ArrayList<Line> actual = day5.filterOnlyDiagonalLines(allInputs);
		assertEquals(expected, actual);
	}
	
	@Test
	void addLines_including_diagonal_to_Grid() throws Exception {
		URL fileName = getClass().getResource("testInput.txt");
		day5.setFileToUse(new File(fileName.getPath()));
		day5.setGrid(10);
		ArrayList<Line> allLines = day5.readInLines();
		ArrayList<Line> filteredLines = day5.filterForSameXorY(allLines);
		day5.addLinesToGrid(filteredLines);
		String firstExpected = 
				  ". . . . . . . 1 . . \n"
				+ ". . 1 . . . . 1 . . \n"
				+ ". . 1 . . . . 1 . . \n"
				+ ". . . . . . . 1 . . \n"
				+ ". 1 1 2 1 1 1 2 1 1 \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ ". . . . . . . . . . \n"
				+ "2 2 2 1 1 1 . . . . \n";
		assertEquals(firstExpected, day5.getGrid().toString());
		
		
		String expected = 
				  "1 . 1 . . . . 1 1 . \n"
				+ ". 1 1 1 . . . 2 . . \n"
				+ ". . 2 . 1 . 1 1 1 . \n"
				+ ". . . 1 . 2 . 2 . . \n"
				+ ". 1 1 2 3 1 3 2 1 1 \n"
				+ ". . . 1 . 2 . . . . \n"
				+ ". . 1 . . . 1 . . . \n"
				+ ". 1 . . . . . 1 . . \n"
				+ "1 . . . . . . . 1 . \n"
				+ "2 2 2 1 1 1 . . . . \n";
		ArrayList<Line> diagonalLines = day5.filterOnlyDiagonalLines(allLines);
		day5.addLinesToGrid(diagonalLines);
		
		assertEquals(expected, day5.getGrid().toString());
	}
	
	@Test
	void do_full_input_with_diagonal() throws Exception {
		day5 = new Day5();
		ArrayList<Line> allLines = day5.readInLines();
		ArrayList<Line> filteredLines = day5.filterForSameXorY(allLines);
		day5.addLinesToGrid(filteredLines);
		ArrayList<Line> diagonalLines = day5.filterOnlyDiagonalLines(allLines);
		day5.addLinesToGrid(diagonalLines);
		int actual = day5.getGrid().findAllInstancesAbove(2);
		System.out.println("Number of 2's or greater: " + actual);
	}
}
