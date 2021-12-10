package day8;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;


public class Day8Part2Test {

	Day8Part2 day8 = new Day8Part2();
	

	@Test
	void use_different_input_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		ArrayList<ArrayList<String>> expectedDisplayNumbers = populateExpectedSampleDisplayNumbers();
		ArrayList<ArrayList<String>> expectedWireGroups = populateExpectedSampleWireGroups();
		
		day8.populateWiresAndDisplayNumbers();
		assertEquals(expectedDisplayNumbers, day8.getInputDisplayedNumbers());
		assertEquals(expectedWireGroups, day8.getWireGroups());
		assertEquals(10, day8.getCorrectMappings().size());
	}
	
	@Test
	void correctMappings_has_sorted_chars_for_1_4_7_and_8_from_populateEasyMappings() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		//assert all correctMappings are blank first
		for (int x=0; x<10; x++) {
			assertEquals(0, day8.getCorrectMappings().get(0).get(x).length);
		}
		
		ArrayList<String[]> expectedCorrectMappings = new ArrayList<String[]>();
		//Use 1st sample input to tes:
		//be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
		expectedCorrectMappings.add(new String[0]);
		String[] one = {"b","e"};
		expectedCorrectMappings.add(one);
		expectedCorrectMappings.add(new String[0]);
		expectedCorrectMappings.add(new String[0]);
		String[] four = {"b", "c", "e", "g"};
		expectedCorrectMappings.add(four);
		expectedCorrectMappings.add(new String[0]);
		expectedCorrectMappings.add(new String[0]);
		String[] seven = {"b", "d", "e"};
		expectedCorrectMappings.add(seven);
		String[] eight = {"a", "b", "c", "d", "e", "f", "g"};
		expectedCorrectMappings.add(eight);
		expectedCorrectMappings.add(new String[0]);
		
		day8.populateEasyMappings(0, day8.getWireGroups().get(0));
		ArrayList<ArrayList<String[]>> actualCorrectMappings = day8.getCorrectMappings();
		for(int x=0; x<10; x++)
			assertTrue(Arrays.equals(expectedCorrectMappings.get(x), actualCorrectMappings.get(0).get(x)));
	}
	
	@Test
	void curLetterMappings_a_to_d_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		String expected = "d";
		
		day8.populateCurLetterMappings(0);
		
		assertEquals(expected, day8.getCurLetterMappings().get("a"));
	}
	
	@Test
	void curLetterMappings_c_to_b_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		String expected = "b";
		
		day8.populateCurLetterMappings(0);
		
		assertEquals(expected, day8.getCurLetterMappings().get("c"));
	}
	
	@Test
	void can_print_out_num_of_occurences_of_each_letter_in_wireGroup() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		WireCounts expectedCounts = new WireCounts(4,8,7,8,9,7,6);
		assertEquals(expectedCounts, day8.getNumOfOccurrenceForEachLetter(0)); 
	}
	
	@Test
	void curLetterMapping_f_to_e_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		String expected = "e";
		
		day8.populateCurLetterMappings(0);
		
		assertEquals(expected, day8.getCurLetterMappings().get("f"));
	}
	
	@Test
	void curLetterMapping_b_to_g_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		String expected = "g";
		
		day8.populateCurLetterMappings(0);
		
		assertEquals(expected, day8.getCurLetterMappings().get("b"));
	}

	@Test
	void curLetterMapping_e_to_a_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		String expected = "a";
		
		day8.populateCurLetterMappings(0);
		
		assertEquals(expected, day8.getCurLetterMappings().get("e"));
	}

	@Test
	void curLetterMapping_d_to_c_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		String expected = "c";
		
		day8.populateCurLetterMappings(0);
		
		assertEquals(expected, day8.getCurLetterMappings().get("d"));
	}

	@Test
	void curLetterMapping_g_to_f_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		String expected = "f";
		
		day8.populateCurLetterMappings(0);
		
		assertEquals(expected, day8.getCurLetterMappings().get("g"));
	}
	
	@Test
	void correctMappings_has_sorted_chars_for_0_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		
		String[] expected0CorrectMapping = {"a", "b", "d", "e", "f", "g"};
		
		day8.populateCurLetterMappings(0);
		day8.populateDifficultMappings(0);
		assertTrue(Arrays.equals(expected0CorrectMapping, day8.getCorrectMappings().get(0).get(0)));
	}
	
	@Test
	void correctMappings_has_sorted_chars_for_2_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		
		String[] expected0CorrectMapping = {"a", "b", "c", "d", "f"};
		
		day8.populateCurLetterMappings(0);
		day8.populateDifficultMappings(0);
		assertTrue(Arrays.equals(expected0CorrectMapping, day8.getCorrectMappings().get(0).get(2)));
	}

	@Test
	void correctMappings_has_sorted_chars_for_3_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		
		String[] expected0CorrectMapping = {"b", "c", "d", "e", "f"};
		
		day8.populateCurLetterMappings(0);
		day8.populateDifficultMappings(0);
		assertTrue(Arrays.equals(expected0CorrectMapping, day8.getCorrectMappings().get(0).get(3)));
	}

	@Test
	void correctMappings_has_sorted_chars_for_5_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		
		String[] expected0CorrectMapping = {"c", "d", "e", "f", "g"};
		
		day8.populateCurLetterMappings(0);
		day8.populateDifficultMappings(0);
		assertTrue(Arrays.equals(expected0CorrectMapping, day8.getCorrectMappings().get(0).get(5)));
	}

	@Test
	void correctMappings_has_sorted_chars_for_9_for_first_input() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		
		String[] expected0CorrectMapping = {"b", "c", "d", "e", "f", "g"};
		
		day8.populateCurLetterMappings(0);
		day8.populateDifficultMappings(0);
		assertTrue(Arrays.equals(expected0CorrectMapping, day8.getCorrectMappings().get(0).get(9)));
	}

	@Test
	void part2_1Line_Input_has_outputNumbers_5353() throws Exception {
		URL fileName = getClass().getResource("Part2OneLineInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		
		ArrayList<String> expectedDisplayNumbers = new ArrayList<String>();
		expectedDisplayNumbers.add("5");
		expectedDisplayNumbers.add("3");
		expectedDisplayNumbers.add("5");
		expectedDisplayNumbers.add("3");
		
		day8.populateCurLetterMappings(0);
		day8.populateDifficultMappings(0);
		day8.populateDisplayNumberOutputs(0);
		assertEquals(expectedDisplayNumbers, day8.getCurDisplayNumberOutputs());
	}
	
	@Test
	void get4DigitOutputNumber() throws Exception {
		URL fileName = getClass().getResource("Part2OneLineInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		
		day8.populateCurLetterMappings(0);
		day8.populateDifficultMappings(0);
		day8.populateDisplayNumberOutputs(0);
		assertEquals(5353, day8.get4digitOutputNumber());
	}
	
	@Test
	void part2_full_sample_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndDisplayNumbers();
		
		int total = 0;
		for(int index=0; index<day8.getWireGroups().size(); index++) {
			day8.populateCurLetterMappings(index);
			day8.populateDifficultMappings(index);
			day8.populateDisplayNumberOutputs(index);
			total+=day8.get4digitOutputNumber();
		}
		assertEquals(61229, total);
	}
	
	@Test
	void part2_full_test() throws Exception {
		day8 = new Day8Part2();
		day8.populateWiresAndDisplayNumbers();
		
		int total = 0;
		for(int index=0; index<day8.getWireGroups().size(); index++) {
			day8.populateCurLetterMappings(index);
			day8.populateDifficultMappings(index);
			day8.populateDisplayNumberOutputs(index);
			total+=day8.get4digitOutputNumber();
		}
		System.out.println("Total: " + total);
		//check after known
		assertEquals(946346, total);
	}
	
	private ArrayList<ArrayList<String>> populateExpectedSampleDisplayNumbers() {
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		ArrayList<String> line1 = new ArrayList<String>();
		line1.add("fdgacbe");
		line1.add("cefdb");
		line1.add("cefbgd");
		line1.add("gcbe");
		expected.add(line1);
		ArrayList<String> line2 = new ArrayList<String>();
		line2.add("fcgedb");
		line2.add("cgb");
		line2.add("dgebacf");
		line2.add("gc");
		expected.add(line2);
		ArrayList<String> line3 = new ArrayList<String>();
		line3.add("cg");
		line3.add("cg");
		line3.add("fdcagb");
		line3.add("cbg");
		expected.add(line3);
		ArrayList<String> line4 = new ArrayList<String>();
		line4.add("efabcd");
		line4.add("cedba");
		line4.add("gadfec");
		line4.add("cb");
		expected.add(line4);
		ArrayList<String> line5 = new ArrayList<String>();
		line5.add("gecf");
		line5.add("egdcabf");
		line5.add("bgf");
		line5.add("bfgea");
		expected.add(line5);
		ArrayList<String> line6 = new ArrayList<String>();
		line6.add("gebdcfa");
		line6.add("ecba");
		line6.add("ca");
		line6.add("fadegcb");
		expected.add(line6);
		ArrayList<String> line7 = new ArrayList<String>();
		line7.add("cefg");
		line7.add("dcbef");
		line7.add("fcge");
		line7.add("gbcadfe");
		expected.add(line7);
		ArrayList<String> line8 = new ArrayList<String>();
		line8.add("ed");
		line8.add("bcgafe");
		line8.add("cdgba");
		line8.add("cbgef");
		expected.add(line8);
		ArrayList<String> line9 = new ArrayList<String>();
		line9.add("gbdfcae");
		line9.add("bgc");
		line9.add("cg");
		line9.add("cgb");
		expected.add(line9);
		ArrayList<String> line10 = new ArrayList<String>();
		line10.add("fgae");
		line10.add("cfgab");
		line10.add("fg");
		line10.add("bagce");
		expected.add(line10);
		return expected;
	}

	private ArrayList<ArrayList<String>> populateExpectedSampleWireGroups() {
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		ArrayList<String> line1 = new ArrayList<String>();
		line1.add("be");
		line1.add("cfbegad");
		line1.add("cbdgef");
		line1.add("fgaecd");
		line1.add("cgeb");
		line1.add("fdcge");
		line1.add("agebfd");
		line1.add("fecdb");
		line1.add("fabcd");
		line1.add("edb");
		expected.add(line1);
		ArrayList<String> line2 = new ArrayList<String>();
		line2.add("edbfga");
		line2.add("begcd");
		line2.add("cbg");
		line2.add("gc");
		line2.add("gcadebf");
		line2.add("fbgde");
		line2.add("acbgfd");
		line2.add("abcde");
		line2.add("gfcbed");
		line2.add("gfec");
		expected.add(line2);
		ArrayList<String> line3 = new ArrayList<String>();
		line3.add("fgaebd");
		line3.add("cg");
		line3.add("bdaec");
		line3.add("gdafb");
		line3.add("agbcfd");
		line3.add("gdcbef");
		line3.add("bgcad");
		line3.add("gfac");
		line3.add("gcb");
		line3.add("cdgabef");
		expected.add(line3);
		ArrayList<String> line4 = new ArrayList<String>();
		line4.add("fbegcd");
		line4.add("cbd");
		line4.add("adcefb");
		line4.add("dageb");
		line4.add("afcb");
		line4.add("bc");
		line4.add("aefdc");
		line4.add("ecdab");
		line4.add("fgdeca");
		line4.add("fcdbega");
		expected.add(line4);
		ArrayList<String> line5 = new ArrayList<String>();
		line5.add("aecbfdg");
		line5.add("fbg");
		line5.add("gf");
		line5.add("bafeg");
		line5.add("dbefa");
		line5.add("fcge");
		line5.add("gcbea");
		line5.add("fcaegb");
		line5.add("dgceab");
		line5.add("fcbdga");
		expected.add(line5);
		ArrayList<String> line6 = new ArrayList<String>();
		line6.add("fgeab");
		line6.add("ca");
		line6.add("afcebg");
		line6.add("bdacfeg");
		line6.add("cfaedg");
		line6.add("gcfdb");
		line6.add("baec");
		line6.add("bfadeg");
		line6.add("bafgc");
		line6.add("acf");
		expected.add(line6);
		ArrayList<String> line7 = new ArrayList<String>();
		line7.add("dbcfg");
		line7.add("fgd");
		line7.add("bdegcaf");
		line7.add("fgec");
		line7.add("aegbdf");
		line7.add("ecdfab");
		line7.add("fbedc");
		line7.add("dacgb");
		line7.add("gdcebf");
		line7.add("gf");
		expected.add(line7);
		ArrayList<String> line8 = new ArrayList<String>();
		line8.add("bdfegc");
		line8.add("cbegaf");
		line8.add("gecbf");
		line8.add("dfcage");
		line8.add("bdacg");
		line8.add("ed");
		line8.add("bedf");
		line8.add("ced");
		line8.add("adcbefg");
		line8.add("gebcd");
		expected.add(line8);
		ArrayList<String> line9 = new ArrayList<String>();
		line9.add("egadfb");
		line9.add("cdbfeg");
		line9.add("cegd");
		line9.add("fecab");
		line9.add("cgb");
		line9.add("gbdefca");
		line9.add("cg");
		line9.add("fgcdab");
		line9.add("egfdb");
		line9.add("bfceg");
		expected.add(line9);
		ArrayList<String> line10 = new ArrayList<String>();
		line10.add("gcafb");
		line10.add("gcf");
		line10.add("dcaebfg");
		line10.add("ecagb");
		line10.add("gf");
		line10.add("abcdeg");
		line10.add("gaef");
		line10.add("cafbge");
		line10.add("fdbac");
		line10.add("fegbdc");
		expected.add(line10);
		return expected;
	}
	
}