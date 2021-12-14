package day14;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;


public class Day14Part1Test {

	Day14 day14;
	
	@Test
	void can_update_startingPolymer() throws Exception {
		day14 = new Day14();
		URL fileName = getClass().getResource("SampleInput.txt");
		day14.setFileToUse(new File(fileName.getPath()));
		
		char[] expectedPolymer = new char[] {'N','N','C','B'};
		day14.setPolymer(new char[] {'N','N','C','B'});
		assertTrue(Arrays.equals(expectedPolymer, day14.getPoylmer()));
	}
	
	@Test
	void getInputRules() throws Exception {
		day14 = new Day14();
		URL fileName = getClass().getResource("SampleInput.txt");
		day14.setFileToUse(new File(fileName.getPath()));
		
		ArrayList<Rule> expectedRules = new ArrayList<Rule>();
		expectedRules.add(new Rule('C','H','B'));
		expectedRules.add(new Rule('H','H','N'));
		expectedRules.add(new Rule('C','B','H'));
		expectedRules.add(new Rule('N','H','C'));
		expectedRules.add(new Rule('H','B','C'));
		expectedRules.add(new Rule('H','C','B'));
		expectedRules.add(new Rule('H','N','C'));
		expectedRules.add(new Rule('N','N','C'));
		expectedRules.add(new Rule('B','H','H'));
		expectedRules.add(new Rule('N','C','B'));
		expectedRules.add(new Rule('N','B','B'));
		expectedRules.add(new Rule('B','N','B'));
		expectedRules.add(new Rule('B','B','N'));
		expectedRules.add(new Rule('B','C','B'));
		expectedRules.add(new Rule('C','C','N'));
		expectedRules.add(new Rule('C','N','C'));
		
		day14.getInputs();
		
		assertEquals(expectedRules, day14.getRules());
	}
	
	@Test
	void canApplyRulesToCurrentPolymer() throws Exception {
		day14 = new Day14();
		day14.setPolymer(new char[] {'N','N','C','B'});
		URL fileName = getClass().getResource("SampleInput.txt");
		day14.setFileToUse(new File(fileName.getPath()));
		day14.getInputs();
		
		char[] expectedPolymer = new char[] {'N','C','N','B','C','H','B'};
		day14.processRulesOnPolymer();
		
		System.out.println(day14.getPoylmer());
		assertTrue(Arrays.equals(expectedPolymer, day14.getPoylmer()));
	}
	
	@Test
	void canApplyRulesMultipleTimes() throws Exception {
		day14 = new Day14();
		day14.setPolymer(new char[] {'N','N','C','B'});
		URL fileName = getClass().getResource("SampleInput.txt");
		day14.setFileToUse(new File(fileName.getPath()));
		day14.getInputs();
		
		char[] expectedPolymer = new char[] {'N','B','C','C','N','B','B','B','C','B','H','C','B'};
		day14.processRulesOnPolymer();
		day14.processRulesOnPolymer();
		
		System.out.println("After 2: ");
		System.out.println(day14.getPoylmer());
		assertTrue(Arrays.equals(expectedPolymer, day14.getPoylmer()));
		
		expectedPolymer = new char[] {'N','B','B','B','C','N','C','C','N','B','B','N','B','N','B','B','C','H','B','H','H','B','C','H','B'};
		day14.processRulesOnPolymer();
		
		System.out.println("After 3: ");
		System.out.println(day14.getPoylmer());
		assertTrue(Arrays.equals(expectedPolymer, day14.getPoylmer()));
		
		expectedPolymer = new char[] {'N','B','B','N','B','N','B','B','C','C','N','B','C','N','C','C','N','B','B','N','B','B','N','B','B','B','N','B','B','N','B','B','C','B','H','C','B','H','H','N','H','C','B','B','C','B','H','C','B'};
		day14.processRulesOnPolymer();
		
		System.out.println("After 4: ");
		System.out.println(day14.getPoylmer());
		assertTrue(Arrays.equals(expectedPolymer, day14.getPoylmer()));
	}
	
	@Test
	void part1_verify_rest_after_10_steps() throws Exception {
		day14 = new Day14();
		day14.setPolymer(new char[] {'N','N','C','B'});
		URL fileName = getClass().getResource("SampleInput.txt");
		day14.setFileToUse(new File(fileName.getPath()));
		day14.getInputs();
		
		for(int step=0; step<10; step++) {
			day14.processRulesOnPolymer();
		}
		
		System.out.println("After 10: ");
		System.out.println(day14.getPoylmer());
		assertEquals(3073, day14.getPoylmer().length);
		assertEquals(1749, day14.getCountOfChar('B'));
		assertEquals(298, day14.getCountOfChar('C'));
		assertEquals(161, day14.getCountOfChar('H'));
		assertEquals(865, day14.getCountOfChar('N'));
	}
	
	@Test
	void part1_full() throws Exception {
		day14 = new Day14();
		day14.getInputs();
		
		for(int step=0; step<10; step++) {
			day14.processRulesOnPolymer();
		}
		
		System.out.println("After 10 full: ");
		System.out.println(day14.getPoylmer());
		System.out.println("Total Len after 10: " + day14.getPoylmer().length);
		long total = 0;
		System.out.println("V: " + day14.getCountOfChar('V'));
		System.out.println("H: " + day14.getCountOfChar('H'));
		System.out.println("C: " + day14.getCountOfChar('C'));
		System.out.println("K: " + day14.getCountOfChar('K'));
		System.out.println("B: " + day14.getCountOfChar('B'));
		System.out.println("F: " + day14.getCountOfChar('F'));
		System.out.println("O: " + day14.getCountOfChar('O'));
		System.out.println("P: " + day14.getCountOfChar('P'));
		System.out.println("S: " + day14.getCountOfChar('S'));
		System.out.println("N: " + day14.getCountOfChar('N'));
		total+=day14.getCountOfChar('V')+day14.getCountOfChar('H')+day14.getCountOfChar('C')
			+day14.getCountOfChar('K')+day14.getCountOfChar('B')+day14.getCountOfChar('F')
			+day14.getCountOfChar('O')+day14.getCountOfChar('P')+day14.getCountOfChar('S')
			+day14.getCountOfChar('N');
		System.out.println("Total found: " + total);
		//Add asserts after known
		assertEquals(2775, day14.getCountOfChar('V'));
		assertEquals(991, day14.getCountOfChar('H'));
		assertEquals(1690, day14.getCountOfChar('C'));
		assertEquals(1804, day14.getCountOfChar('K'));
		assertEquals(2564, day14.getCountOfChar('B'));
		assertEquals(1387, day14.getCountOfChar('F'));
		assertEquals(3578, day14.getCountOfChar('O'));
		assertEquals(997, day14.getCountOfChar('P'));
		assertEquals(2242, day14.getCountOfChar('S'));
		assertEquals(1429, day14.getCountOfChar('N'));
	}
	
	@Test
	void can_get_value_of_charPairs_from_new_CharPair() throws Exception {
		HashMap<CharPair, Long> charPairs = new HashMap<CharPair, Long>();
		long expected = 57l;
		charPairs.put(new CharPair('A', 'B'), expected);
		assertEquals(expected, charPairs.get(new CharPair('A', 'B')));
	}
	
	@Test
	void can_count_pairs() throws Exception {
		day14 = new Day14();
		day14.setPolymer(new char[] {'N','N','C','B'});
		URL fileName = getClass().getResource("SampleInput.txt");
		day14.setFileToUse(new File(fileName.getPath()));
		day14.getInputs();
		
		day14.processRulesOnCharPairs();
		assertEquals(2, day14.getCountOfCharFromCharPairs('B'));
		assertEquals(2, day14.getCountOfCharFromCharPairs('C'));
		assertEquals(1, day14.getCountOfCharFromCharPairs('H'));
		assertEquals(2, day14.getCountOfCharFromCharPairs('N'));

		day14.processRulesOnCharPairs();
		assertEquals(6, day14.getCountOfCharFromCharPairs('B'));
		assertEquals(4, day14.getCountOfCharFromCharPairs('C'));
		assertEquals(1, day14.getCountOfCharFromCharPairs('H'));
		assertEquals(2, day14.getCountOfCharFromCharPairs('N'));
		
		day14.processRulesOnCharPairs();
		assertEquals(11, day14.getCountOfCharFromCharPairs('B'));
		assertEquals(5, day14.getCountOfCharFromCharPairs('C'));
		assertEquals(4, day14.getCountOfCharFromCharPairs('H'));
		assertEquals(5, day14.getCountOfCharFromCharPairs('N'));
		
		day14.processRulesOnCharPairs();
		day14.processRulesOnCharPairs();
		day14.processRulesOnCharPairs();
		day14.processRulesOnCharPairs();
		day14.processRulesOnCharPairs();
		day14.processRulesOnCharPairs();
		day14.processRulesOnCharPairs();
		
		//after 10:
		assertEquals(1749, day14.getCountOfCharFromCharPairs('B'));
		assertEquals(298, day14.getCountOfCharFromCharPairs('C'));
		assertEquals(161, day14.getCountOfCharFromCharPairs('H'));
		assertEquals(865, day14.getCountOfCharFromCharPairs('N'));
		
		for(int x=0; x<30; x++)
			day14.processRulesOnCharPairs();
		
		//after 40:
		assertEquals(2192039569602l, day14.getCountOfCharFromCharPairs('B'));
		assertEquals(3849876073l, day14.getCountOfCharFromCharPairs('H'));
	}
	

	@Test
	void part2_full() throws Exception {
		day14 = new Day14();
		day14.getInputs();
		
		for(int step=0; step<40; step++) {
			day14.processRulesOnCharPairs();
		}
		
		System.out.println("Total 40 steps:");
		ArrayList<Long> totals = new ArrayList<Long>();
		long total = 0;
		System.out.println("V: " + day14.getCountOfCharFromCharPairs('V'));
		System.out.println("H: " + day14.getCountOfCharFromCharPairs('H'));
		System.out.println("C: " + day14.getCountOfCharFromCharPairs('C'));
		System.out.println("K: " + day14.getCountOfCharFromCharPairs('K'));
		System.out.println("B: " + day14.getCountOfCharFromCharPairs('B'));
		System.out.println("F: " + day14.getCountOfCharFromCharPairs('F'));
		System.out.println("O: " + day14.getCountOfCharFromCharPairs('O'));
		System.out.println("P: " + day14.getCountOfCharFromCharPairs('P'));
		System.out.println("S: " + day14.getCountOfCharFromCharPairs('S'));
		System.out.println("N: " + day14.getCountOfCharFromCharPairs('N'));
		total+=day14.getCountOfCharFromCharPairs('V')+day14.getCountOfCharFromCharPairs('H')+day14.getCountOfCharFromCharPairs('C')
			+day14.getCountOfCharFromCharPairs('K')+day14.getCountOfCharFromCharPairs('B')+day14.getCountOfCharFromCharPairs('F')
			+day14.getCountOfCharFromCharPairs('O')+day14.getCountOfCharFromCharPairs('P')+day14.getCountOfCharFromCharPairs('S')
			+day14.getCountOfCharFromCharPairs('N');
		System.out.println("Total found: " + total);
		totals.add(day14.getCountOfCharFromCharPairs('V'));
		totals.add(day14.getCountOfCharFromCharPairs('H'));
		totals.add(day14.getCountOfCharFromCharPairs('C'));
		totals.add(day14.getCountOfCharFromCharPairs('K'));
		totals.add(day14.getCountOfCharFromCharPairs('B'));
		totals.add(day14.getCountOfCharFromCharPairs('F'));
		totals.add(day14.getCountOfCharFromCharPairs('O'));
		totals.add(day14.getCountOfCharFromCharPairs('P'));
		totals.add(day14.getCountOfCharFromCharPairs('S'));
		totals.add(day14.getCountOfCharFromCharPairs('N'));
		Collections.sort(totals);
		System.out.println("Least=" + totals.get(0));
		System.out.println("Most=" + totals.get(totals.size()-1));
		System.out.println("Answer=" + (totals.get(totals.size()-1) - totals.get(0)));
		//Add asserts after known
		assertEquals(806833108703l, totals.get(0));
		assertEquals(4125670671826l, totals.get(totals.size()-1));
		assertEquals(20890720927745l, total);
	}
}
