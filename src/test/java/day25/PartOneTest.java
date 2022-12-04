package day25;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import day1.FileUtility;


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
	
//	@Test
//	//SmallerElf=>Elf with smaller range.  If both same range, then the first input Elf.
//	void make_correct_elf_smallerElf_for_each_pair() throws Exception {
//		ArrayList<ElfPair> expectedElfPairs = new ArrayList<ElfPair>();
//		expectedElfPairs.add(new ElfPair(new Elf("2-4"), new Elf("6-8")));
//		expectedElfPairs.add(new ElfPair(new Elf("2-3"), new Elf("4-5")));
//		expectedElfPairs.add(new ElfPair(new Elf("5-7"), new Elf("7-9")));
//		expectedElfPairs.add(new ElfPair(new Elf("2-8"), new Elf("3-7")));
//		expectedElfPairs.add(new ElfPair(new Elf("6-6"), new Elf("4-6")));
//		expectedElfPairs.add(new ElfPair(new Elf("2-6"), new Elf("4-8")));
//		
//		URL fileName = getClass().getResource("SampleInput.txt");
//		mapFromInput.setFileToUse(new File(fileName.getPath()));
//		mapFromInput.populateElfPairs();
//		assertEquals(new Elf("2-4"), mapFromInput.getElfPairs().get(0).getSmallerElf());
//		assertEquals(new Elf("2-3"), mapFromInput.getElfPairs().get(1).getSmallerElf());
//		assertEquals(new Elf("5-7"), mapFromInput.getElfPairs().get(2).getSmallerElf());
//		assertEquals(new Elf("3-7"), mapFromInput.getElfPairs().get(3).getSmallerElf());
//		assertEquals(new Elf("6-6"), mapFromInput.getElfPairs().get(4).getSmallerElf());
//		assertEquals(new Elf("2-6"), mapFromInput.getElfPairs().get(5).getSmallerElf());
//	}
//	
//	@Test
//	void verify_isSmallerContainedInLarger() throws Exception {
//		URL fileName = getClass().getResource("SampleInput.txt");
//		mapFromInput.setFileToUse(new File(fileName.getPath()));
//		mapFromInput.populateElfPairs();
//		assertEquals(false, mapFromInput.getElfPairs().get(0).isSmallerContainedInLarger());
//		assertEquals(false, mapFromInput.getElfPairs().get(1).isSmallerContainedInLarger());
//		assertEquals(false, mapFromInput.getElfPairs().get(2).isSmallerContainedInLarger());
//		assertEquals(true, mapFromInput.getElfPairs().get(3).isSmallerContainedInLarger());
//		assertEquals(true, mapFromInput.getElfPairs().get(4).isSmallerContainedInLarger());
//		assertEquals(false, mapFromInput.getElfPairs().get(5).isSmallerContainedInLarger());
//	}
//	
//	@Test
//	void verify_number_of_totalContainedPairs_in_elfPairs() throws Exception {
//		URL fileName = getClass().getResource("SampleInput.txt");
//		mapFromInput.setFileToUse(new File(fileName.getPath()));
//		mapFromInput.populateElfPairs();
//		assertEquals(2, mapFromInput.getTotalContainedPairs());
//	}
//	
//	@Test
//	void partOne_Answer() throws Exception {
//		mapFromInput.populateElfPairs();
////		System.out.println(elfPairsFromInput.getTotalContainedPairs());
//		assertEquals(500, mapFromInput.getTotalContainedPairs());
//	}
}
