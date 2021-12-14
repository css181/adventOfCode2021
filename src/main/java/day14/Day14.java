package day14;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import day1.FileUtility;

public class Day14 {
	
	private static File file;
	private char[] polymer;
	private ArrayList<Rule> rules;
	private HashMap<CharPair, Long> charPairs;
	
	public Day14() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		polymer = new char[] {'V','H','C','K','B','F','O','V','C','H','H','K','O','H','B','P','N','C','K','O'};
		rules = new ArrayList<Rule>();
		charPairs = new HashMap<CharPair, Long>();
	}

	protected void getInputs() {
		ArrayList<String> inputLines = FileUtility.convertFileToStringArray(file);
		for (String inputLine : inputLines) {
			rules.add(new Rule(inputLine.charAt(0),inputLine.charAt(1),inputLine.charAt(3)));
		}
		for(int pos=0; pos<polymer.length-1; pos++) {
			CharPair curCharPair = new CharPair(polymer[pos], polymer[pos+1]);
			Long curValue = charPairs.get(curCharPair);
			if(curValue==null) {
				charPairs.put(curCharPair, 1l);
			} else {
				charPairs.put(curCharPair, curValue+1);
			}
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
	public HashMap<CharPair, Long> getCharPairs() {
		return charPairs;
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

	
	public void processRulesOnCharPairs() {
		HashMap<CharPair, Long> newCharPairs = new HashMap<CharPair, Long>();
		boolean addedCurCharPair;
		for (CharPair charPair : charPairs.keySet()) {
			addedCurCharPair = false;
			for (Rule rule : rules) {
				if(rule.doesApply(charPair.getFirstChar(), charPair.getSecondChar())) {
					addToNewCharPairs(newCharPairs, charPair.getFirstChar(), rule.getInsertChar(), charPairs.get(charPair));
					addToNewCharPairs(newCharPairs, rule.getInsertChar(), charPair.getSecondChar(), charPairs.get(charPair));
					addedCurCharPair = true;
					break;
				}
			}
			if(!addedCurCharPair) {
				addToNewCharPairs(newCharPairs, charPair.getFirstChar(), charPair.getSecondChar(), charPairs.get(charPair));
			}
		}
		charPairs = newCharPairs;
	}

	private void addToNewCharPairs(HashMap<CharPair, Long> newCharPairs, Character firstChar, Character secondChar, Long count) {
		CharPair charPair = new CharPair(firstChar, secondChar);
		Long preExistingValue = newCharPairs.get(charPair);
		if(preExistingValue==null) {
			newCharPairs.put(charPair, count);
		} else {
			newCharPairs.put(charPair, preExistingValue+count);
		}
	}
	
	public long getCountOfCharFromCharPairs(char toCount) {
		long total = 0;
		for (CharPair charPair : charPairs.keySet()) {
			if(charPair.getFirstChar()==toCount || charPair.getSecondChar()==toCount) {
				total+=charPairs.get(charPair);
			}
			if(charPair.getFirstChar()==toCount && charPair.getSecondChar()==toCount) {
				total+=charPairs.get(charPair);
			}
		}
		return Math.round(total/2.0);
	}
}
