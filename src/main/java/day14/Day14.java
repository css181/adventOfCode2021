package day14;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day14 {
	
	private static File file;
	private char[] polymer;
	private ArrayList<Rule> rules;
	
	
	public Day14() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		polymer = new char[] {'V','H','C','K','B','F','O','V','C','H','H','K','O','H','B','P','N','C','K','O'};
		rules = new ArrayList<Rule>();
	}

	protected void getInputs() {
		ArrayList<String> inputLines = FileUtility.convertFileToStringArray(file);
		for (String inputLine : inputLines) {
			rules.add(new Rule(inputLine.charAt(0),inputLine.charAt(1),inputLine.charAt(3)));
		}
	}
	protected void setFileToUse(File file) {
		Day14.file = file;
	}

	public char[] getPoylmer() {
		return polymer;
	}
	protected void setPolymer(char[] testPolymer) {
		this.polymer = testPolymer;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void processRulesOnPolymer() {
		ArrayList<Character> newList = new ArrayList<Character>();
		for(int pos=0; pos<polymer.length-1; pos++) {
			newList.add(polymer[pos]);
			for (Rule rule : rules) {
				if(rule.doesApply(polymer[pos], polymer[pos+1])) {
					newList.add(rule.getInsertChar());
				}
			}
		}
		newList.add(polymer[polymer.length-1]);
		reAssignPolymerToNewList(newList);
	}

	private void reAssignPolymerToNewList(ArrayList<Character> newList) {
		char[] arr = new char[newList.size()];
		for(int x=0; x<arr.length; x++) {
			arr[x] = newList.get(x);
		}
		polymer = arr;
	}
	
	public long getCountOfChar(char toCount) {
		long total = 0;
		for (char curChar : polymer) {
			if(curChar==toCount) {
				total++;
			}
		}
		return total;
	}

}
