package day12;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;


public class Day12Part1Test {

	Day12 day12;
	
	@Test
	void cave_starts_with_no_connections() throws Exception {
		String name = "ab";
		Cave cave = new Cave(name);
		
		assertEquals(name, cave.getName());
		assertEquals(0, cave.getConnections().size());
	}
	
	@Test
	void cant_add_same_cave_as_a_connection_mutliple_times() throws Exception {
		String name = "ab";
		Cave cave = new Cave(name);
		String name2 = "DC";
		Cave cave2 = new Cave(name2);
		
		cave2.addConnection(cave);
		cave2.addConnection(cave);
		
		assertEquals(1, cave2.getConnections().size());
		assertTrue(cave2.getConnections().contains(cave));
	}
	
	@Test
	void simple_sample_input_has_startCave_endCave_and_abCave_test() throws Exception {
		day12 = new Day12();
		URL fileName = getClass().getResource("SimpleSampleInput.txt");
		day12.setFileToUse(new File(fileName.getPath()));

		Cave expectedStart = new Cave("start");
		//Note: nothing can link back to start
		Cave expectedEnd = new Cave("end");
		//Note: end should never have any connections,
		//once we get to end, we're always done.
		Cave expectedAb = new Cave("ab");
		expectedStart.addConnection(expectedAb);
		expectedAb.addConnection(expectedEnd);

		day12.getInputs();
		
		assertEquals(expectedStart, day12.getStartCave());
		assertEquals(expectedEnd, day12.getEndCave());
		assertEquals(1, day12.getOtherCaves().size());
		assertEquals(expectedAb, day12.getOtherCaves().get(0));
	}
	
	@Test
	void sample_input_test() throws Exception {
		day12 = new Day12();
		URL fileName = getClass().getResource("SampleInput.txt");
		day12.setFileToUse(new File(fileName.getPath()));
		
		Cave start = new Cave("start");
		//Note: nothing can link back to start
		Cave end = new Cave("end");
		//Note: end should never have any connections,
		//once we get to end, we're always done.
		Cave dc = new Cave("dc");
		Cave HN = new Cave("HN");
		Cave kj = new Cave("kj");
		Cave LN = new Cave("LN");
		Cave sa = new Cave("sa");
		dc.addConnection(end);
		start.addConnection(HN);
		start.addConnection(kj);
		start.addConnection(dc);
		dc.addConnection(HN);
		HN.addConnection(dc);
		LN.addConnection(dc);
		dc.addConnection(LN);
		HN.addConnection(end);
		kj.addConnection(sa);
		sa.addConnection(kj);
		kj.addConnection(HN);
		HN.addConnection(kj);
		kj.addConnection(dc);
		dc.addConnection(kj);

		day12.getInputs();
		
		assertEquals(start, day12.getStartCave());
		assertEquals(end, day12.getEndCave());
		assertEquals(5, day12.getOtherCaves().size());
		assertEquals(dc, day12.getOtherCaves().get(0));
		assertEquals(HN, day12.getOtherCaves().get(1));
		assertEquals(kj, day12.getOtherCaves().get(2));
		assertEquals(LN, day12.getOtherCaves().get(3));
		assertEquals(sa, day12.getOtherCaves().get(4));
	}
	
	@Test
	void find_all_paths_sample_input_test() throws Exception {
		day12 = new Day12();
		URL fileName = getClass().getResource("SampleInput.txt");
		day12.setFileToUse(new File(fileName.getPath()));
		day12.getInputs();
		
		ArrayList<ArrayList<String>> expectedPaths = new ArrayList<ArrayList<String>>();
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","dc","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","dc","HN","kj","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","dc","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","dc","kj","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","kj","HN","dc","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","kj","HN","dc","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","kj","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","kj","dc","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","HN","kj","dc","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","dc","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","dc","HN","kj","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","dc","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","dc","kj","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","kj","HN","dc","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","kj","HN","dc","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","kj","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","kj","dc","HN","end")));
		expectedPaths.add(new ArrayList<>(Arrays.asList("start","kj","dc","end")));

		day12.goToNext(day12.getStartCave());
		
		System.out.println("\nAll Paths at end:");
		for (ArrayList<String> path : day12.getAllPaths()) {
			System.out.println(path);
		}
		assertEquals(expectedPaths.size(), day12.getAllPaths().size());
		for (ArrayList<String> expectedPath : expectedPaths) {
			assertTrue(day12.getAllPaths().contains(expectedPath));
		}
	}
	
	@Test
	void actual_part1_test() throws Exception {
		day12 = new Day12();
		day12.getInputs();
		day12.goToNext(day12.getStartCave());
		System.out.println("Total paths: " + day12.getAllPaths().size());
		//after known add assert
		assertEquals(4304, day12.getAllPaths().size());
	}
	
	@Test
	void part2_getAllPaths_when_can_go_to_single_lowerCase_twice() throws Exception {
		day12 = new Day12();
		day12.getInputs();
		day12.goToNextIfCanVisitOneLowerCaseTwice(day12.getStartCave());
		
		System.out.println("Total paths with dupe lower case: " + day12.getAllPaths().size());
		//after known add assert
	}
}
