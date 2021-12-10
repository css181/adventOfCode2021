package day8;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class Day8Test {

	Day8 day8 = new Day8();
	
	
	@Test
	void use_different_input_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		ArrayList<ArrayList<String>> expected = populateExpectedSampleInput();
		
		day8.populateWiresAndSignals();
		assertEquals(expected, day8.getInputNumberGroups());
	}
	
	@Test
	void count_2_3_4_and_7_lengths() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		day8.setFileToUse(new File(fileName.getPath()));
		day8.populateWiresAndSignals();
		
		int expected = 26;
		int actual = day8.getTotalNumberGroupsOfUniuqueLengths();
		assertEquals(expected, actual);
	}

	@Test
	void day8Part1_forReal() throws Exception {
		day8 = new Day8();
		System.out.println(day8.getTotalNumberGroupsOfUniuqueLengths());
	}
	
	
	
	
	private ArrayList<ArrayList<String>> populateExpectedSampleInput() {
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
	
}
