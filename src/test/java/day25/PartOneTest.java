package day25;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import day1.FileUtility;
import day25.spots.EastCucumber;


public class PartOneTest {

	private SeaMapFromInput mapFromInput;
	
	@BeforeEach
	public void setup() {
		mapFromInput = new SeaMapFromInput();
	}
	
	@Test 
	void convertFileToCharArrayOfArraysTest() {
		ArrayList<ArrayList<Character>> expected = new ArrayList<ArrayList<Character>>();
		expected.add((new ArrayList<>(Arrays.asList('v','.','.','.','>','>','.','v','v','>'))));
		expected.add((new ArrayList<>(Arrays.asList('.','v','v','>','>','.','v','v','.','.'))));
		expected.add((new ArrayList<>(Arrays.asList('>','>','.','>','v','>','.','.','.','v'))));
		expected.add((new ArrayList<>(Arrays.asList('>','>','v','>','>','.','>','.','v','.'))));
		expected.add((new ArrayList<>(Arrays.asList('v','>','v','.','v','v','.','v','.','.'))));
		expected.add((new ArrayList<>(Arrays.asList('>','.','>','>','.','.','v','.','.','.'))));
		expected.add((new ArrayList<>(Arrays.asList('.','v','v','.','.','>','.','>','v','.'))));
		expected.add((new ArrayList<>(Arrays.asList('v','.','v','.','.','>','>','v','.','v'))));
		expected.add((new ArrayList<>(Arrays.asList('.','.','.','.','v','.','.','v','.','>'))));
		
		URL fileName = getClass().getResource("SampleInput.txt");
		ArrayList<ArrayList<Character>> actual = FileUtility.convertFileToCharacterArray(new File(fileName.getPath()));
		assertEquals(expected, actual);
	}
	
	@Test
	void getting_input_into_map() throws Exception {
		ArrayList<ArrayList<Character>> input = new ArrayList<ArrayList<Character>>();
		input.add((new ArrayList<>(Arrays.asList('v','.','.','.','>','>','.','v','v','>'))));
		input.add((new ArrayList<>(Arrays.asList('.','v','v','>','>','.','v','v','.','.'))));
		input.add((new ArrayList<>(Arrays.asList('>','>','.','>','v','>','.','.','.','v'))));
		input.add((new ArrayList<>(Arrays.asList('>','>','v','>','>','.','>','.','v','.'))));
		input.add((new ArrayList<>(Arrays.asList('v','>','v','.','v','v','.','v','.','.'))));
		input.add((new ArrayList<>(Arrays.asList('>','.','>','>','.','.','v','.','.','.'))));
		input.add((new ArrayList<>(Arrays.asList('.','v','v','.','.','>','.','>','v','.'))));
		input.add((new ArrayList<>(Arrays.asList('v','.','v','.','.','>','>','v','.','v'))));
		input.add((new ArrayList<>(Arrays.asList('.','.','.','.','v','.','.','v','.','>'))));
		SeaMap expectedSeaMap = new SeaMap(input);
		
		URL fileName = getClass().getResource("SampleInput.txt");
		mapFromInput.setFileToUse(new File(fileName.getPath()));
		mapFromInput.populateSeaMap();
		assertEquals(expectedSeaMap.toString(), mapFromInput.getSeaMap().toString());
	}
	
	@Test
	void seaMap_can_be_printed() throws Exception {
		String expected = "v...>>.vv>\n"
				+ ".vv>>.vv..\n"
				+ ">>.>v>...v\n"
				+ ">>v>>.>.v.\n"
				+ "v>v.vv.v..\n"
				+ ">.>>..v...\n"
				+ ".vv..>.>v.\n"
				+ "v.v..>>v.v\n"
				+ "....v..v.>\n";
		URL fileName = getClass().getResource("SampleInput.txt");
		mapFromInput.setFileToUse(new File(fileName.getPath()));
		mapFromInput.populateSeaMap();
		assertEquals(expected, mapFromInput.getSeaMap().toString());
//		System.out.println(mapFromInput.getSeaMap().toString());
	}

	@Test
	void each_spot_on_the_map_can_determine_if_isEmpty() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		mapFromInput.setFileToUse(new File(fileName.getPath()));
		mapFromInput.populateSeaMap();
		assertEquals(false, mapFromInput.getSeaMap().getSpot(0,0).isEmpty());
		assertEquals(true, mapFromInput.getSeaMap().getSpot(0,1).isEmpty());
		assertEquals(true, mapFromInput.getSeaMap().getSpot(0,2).isEmpty());
		assertEquals(true, mapFromInput.getSeaMap().getSpot(0,3).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(0,4).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(0,5).isEmpty());
		assertEquals(true, mapFromInput.getSeaMap().getSpot(0,6).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(0,7).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(0,8).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(0,9).isEmpty());
		
		assertEquals(false, mapFromInput.getSeaMap().getSpot(8,9).isEmpty());
	}
	
	@Test
	void each_cucumber_has_a_next_move_and_can_check_if_it_can_move() throws Exception {
		EastCucumber cuc1 = new EastCucumber(new Coordinate(0, 0));
		
	}
}
