package day13;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;


public class Day13Part1Test {

	Day13 day13;
	
	@Test
	void getInputDots() throws Exception {
		day13 = new Day13();
		URL fileName = getClass().getResource("SampleInput.txt");
		day13.setFileToUse(new File(fileName.getPath()));
		
		ArrayList<Dot> expectedDots = new ArrayList<Dot>();
		expectedDots.add(new Dot(6,10));
		expectedDots.add(new Dot(0,14));
		expectedDots.add(new Dot(9,10));
		expectedDots.add(new Dot(0,3));
		expectedDots.add(new Dot(10,4));
		expectedDots.add(new Dot(4,11));
		expectedDots.add(new Dot(6,0));
		expectedDots.add(new Dot(6,12));
		expectedDots.add(new Dot(4,1));
		expectedDots.add(new Dot(0,13));
		expectedDots.add(new Dot(10,12));
		expectedDots.add(new Dot(3,4));
		expectedDots.add(new Dot(3,0));
		expectedDots.add(new Dot(8,4));
		expectedDots.add(new Dot(1,10));
		expectedDots.add(new Dot(2,14));
		expectedDots.add(new Dot(8,10));
		expectedDots.add(new Dot(9,0));
		
		day13.getInputs();
		
		assertEquals(expectedDots, day13.getDots());
	}
	
	@Test
	void getInputFolds() throws Exception {
		day13 = new Day13();
		URL fileName = getClass().getResource("SampleInput.txt");
		day13.setFileToUse(new File(fileName.getPath()));
		URL foldFileName = getClass().getResource("SampleInputFolds.txt");
		day13.setFoldFileToUse(new File(foldFileName.getPath()));
		
		ArrayList<String> expectedFolds = new ArrayList<String>();
		expectedFolds.add("y=7");
		expectedFolds.add("x=5");
		
		day13.getInputs();
		
		assertEquals(expectedFolds, day13.getFolds());
	}
	
	@Test
	void can_apply_dots_to_paper() throws Exception {
		day13 = new Day13();
		URL fileName = getClass().getResource("SampleInput.txt");
		day13.setFileToUse(new File(fileName.getPath()));
		day13.getInputs();
		String expectedPaperGrid = "" +
				"...#..#..#.\n" +
				"....#......\n" +
				"...........\n" +
				"#..........\n" +
				"...#....#.#\n" +
				"...........\n" +
				"...........\n" +
				"...........\n" +
				"...........\n" +
				"...........\n" +
				".#....#.##.\n" +
				"....#......\n" +
				"......#...#\n" +
				"#..........\n" +
				"#.#........\n";
		
		day13.applyDotsToPaper();
		
		assertEquals(expectedPaperGrid, day13.getPaper().toString());
		assertEquals(18, day13.getPaper().getTotalDots());
	}
	
	@Test
	void performYFold() throws Exception {
		day13 = new Day13();
		URL fileName = getClass().getResource("SampleInput.txt");
		day13.setFileToUse(new File(fileName.getPath()));
		day13.getInputs();
		String expectedPaperGrid = "" +
				"#.##..#..#.\n" +
				"#...#......\n" +
				"......#...#\n" +
				"#...#......\n" +
				".#.#..#.###\n" +
				"...........\n" +
				"...........\n";
		
		day13.applyDotsToPaper();
		day13.applyFoldToPaper("y=7");
		
		assertEquals(expectedPaperGrid, day13.getPaper().toString());
		assertEquals(17, day13.getPaper().getTotalDots());
	}
	
	@Test
	void performXFold() throws Exception {
		day13 = new Day13();
		URL fileName = getClass().getResource("SampleInput.txt");
		day13.setFileToUse(new File(fileName.getPath()));
		day13.getInputs();
		String expectedPaperGrid = "" +
				"#####\n" +
				"#...#\n" +
				"#...#\n" +
				"#...#\n" +
				"#####\n" +
				".....\n" +
				".....\n";
		
		day13.applyDotsToPaper();
		day13.applyFoldToPaper("y=7");
		day13.applyFoldToPaper("x=5");
		
		assertEquals(expectedPaperGrid, day13.getPaper().toString());
		assertEquals(16, day13.getPaper().getTotalDots());
	}
	
	@Test
	void part1_get_total_dots() throws Exception {
		day13 = new Day13();
		day13.getInputs();
		
		day13.applyDotsToPaper();
		day13.applyFoldToPaper(day13.getFolds().get(0));
		
		System.out.println("Total dots after first fold: " + day13.getPaper().getTotalDots());
		//Add assert after known
		assertEquals(747, day13.getPaper().getTotalDots());
	}
	
	@Test
	void part2() throws Exception {
		day13 = new Day13();
		day13.getInputs();
		
		day13.applyDotsToPaper();
		for (String fold : day13.getFolds()) {
			day13.applyFoldToPaper(fold);
		}
		System.out.println(day13.getPaper());
		//Add assert after known
		String expected="" +
		".##..###..#..#.####.###...##..#..#.#..#.\n" +
		"#..#.#..#.#..#....#.#..#.#..#.#..#.#..#.\n" +
		"#..#.#..#.####...#..#..#.#....#..#.####.\n" +
		"####.###..#..#..#...###..#....#..#.#..#.\n" +
		"#..#.#.#..#..#.#....#....#..#.#..#.#..#.\n" +
		"#..#.#..#.#..#.####.#.....##...##..#..#.\n";
		assertEquals(expected, day13.getPaper().toString());
	}
}
