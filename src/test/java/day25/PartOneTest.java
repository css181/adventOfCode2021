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
import day25.spots.ISpot;
import day25.spots.SouthCucumber;


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
		assertEquals(true, mapFromInput.getSeaMap().getSpot(1,0).isEmpty());
		assertEquals(true, mapFromInput.getSeaMap().getSpot(2,0).isEmpty());
		assertEquals(true, mapFromInput.getSeaMap().getSpot(3,0).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(4,0).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(5,0).isEmpty());
		assertEquals(true, mapFromInput.getSeaMap().getSpot(6,0).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(7,0).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(8,0).isEmpty());
		assertEquals(false, mapFromInput.getSeaMap().getSpot(9,0).isEmpty());
		
		assertEquals(false, mapFromInput.getSeaMap().getSpot(9,8).isEmpty());
	}
	
	@Test
	void each_cucumber_has_a_next_move_and_can_check_if_it_can_move() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		mapFromInput.setFileToUse(new File(fileName.getPath()));
		mapFromInput.populateSeaMap();
		ISpot[][] map = mapFromInput.getSeaMap().getMap();
		
		SouthCucumber southCanMove = new SouthCucumber(new Coordinate(0, 0));
		assertEquals(true, southCanMove.isAbleToMove(map));
		assertEquals(new Coordinate(0, 1), southCanMove.getMoveToCoordinate(map));
		
		SouthCucumber southCanNotMove = new SouthCucumber(new Coordinate(2, 4));
		assertEquals(false, southCanNotMove.isAbleToMove(map));
		assertEquals(new Coordinate(2, 5), southCanNotMove.getMoveToCoordinate(map));
		
		EastCucumber eastCanMove = new EastCucumber(new Coordinate(5, 0));
		assertEquals(true, eastCanMove.isAbleToMove(map));
		assertEquals(new Coordinate(6, 0), eastCanMove.getMoveToCoordinate(map));
		
		EastCucumber eastCanNotMove = new EastCucumber(new Coordinate(4, 0));
		assertEquals(false, eastCanNotMove.isAbleToMove(map));
		assertEquals(new Coordinate(5, 0), eastCanNotMove.getMoveToCoordinate(map));
		
		//On Boarder test
		SouthCucumber south = new SouthCucumber(new Coordinate(4, 8));
		assertEquals(new Coordinate(4, 0), south.getMoveToCoordinate(map));
		
		EastCucumber east = new EastCucumber(new Coordinate(9, 0));
		assertEquals(new Coordinate(0, 0), east.getMoveToCoordinate(map));
	}
	
	@Test
	void verify_map_after_1_move() throws Exception {
		String expected = "....>.>v.>\n"
				+ "v.v>.>v.v.\n"
				+ ">v>>..>v..\n"
				+ ">>v>v>.>.v\n"
				+ ".>v.v...v.\n"
				+ "v>>.>vvv..\n"
				+ "..v...>>..\n"
				+ "vv...>>vv.\n"
				+ ">.v.v..v.v\n";
		URL fileName = getClass().getResource("SampleInput.txt");
		mapFromInput.setFileToUse(new File(fileName.getPath()));
		mapFromInput.populateSeaMap();
		mapFromInput.performMoveStep();
		assertEquals(expected, mapFromInput.getSeaMap().toString());
	}
	
	@Test
	void verify_map_after_58_moves() throws Exception {
		String expected = "..>>v>vv..\n"
				+ "..v.>>vv..\n"
				+ "..>>v>>vv.\n"
				+ "..>>>>>vv.\n"
				+ "v......>vv\n"
				+ "v>v....>>v\n"
				+ "vvv.....>>\n"
				+ ">vv......>\n"
				+ ".>v.vv.v..\n";
		URL fileName = getClass().getResource("SampleInput.txt");
		mapFromInput.setFileToUse(new File(fileName.getPath()));
		mapFromInput.populateSeaMap();
		for(int x=0; x<58; x++)
			mapFromInput.performMoveStep();
		assertEquals(expected, mapFromInput.getSeaMap().toString());
	}
	
	@Test
	void verify_last_move_of_sample_is_after_58_moves() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		mapFromInput.setFileToUse(new File(fileName.getPath()));
		mapFromInput.populateSeaMap();
		assertEquals(58, mapFromInput.getNumOfStepsUntilNoMovesArePossible());
	}
	
	@Test
	void part_One_Answer() throws Exception {
		mapFromInput.populateSeaMap();
//		System.out.println(mapFromInput.getNumOfStepsUntilNoMovesArePossible());
		assertEquals(337, mapFromInput.getNumOfStepsUntilNoMovesArePossible());
	}
}
