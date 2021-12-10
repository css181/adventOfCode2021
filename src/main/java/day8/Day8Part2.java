package day8;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import day1.FileUtility;

public class Day8Part2 {

	ArrayList<ArrayList<String>> inputDisplayedNumbers;
	ArrayList<ArrayList<String>> inputWireGroups;
	ArrayList<ArrayList<String[]>> correctMappings;
	HashMap<String, String> curLetterMappings;
	ArrayList<String> curDisplayNumberOutputs;
	
	private static File file;
	int dayCount = 0;
	
	public Day8Part2() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		
		populateWiresAndDisplayNumbers();
	}

	protected void setFileToUse(File file) {
		Day8Part2.file = file;
	}

	public void populateWiresAndDisplayNumbers() {
		inputDisplayedNumbers = new ArrayList<ArrayList<String>>();
		inputWireGroups = new ArrayList<ArrayList<String>>();
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		int index = 0;
		for (String input : inputs) {
			ArrayList<String> wireGroupline = new ArrayList<String>();
			String beforePipe = input.substring(0, input.indexOf("|") - 1);
			String[] curWireGroups = beforePipe.split(" ");
			for (String wireGroup : curWireGroups) {
				wireGroupline.add(wireGroup);
			}
			inputWireGroups.add(wireGroupline);
			
			ArrayList<String> displayNumberline = new ArrayList<String>();
			String afterPipe = input.substring(input.indexOf("|") + 2);
			String[] curNumberGroups = afterPipe.split(" ");
			for (String numberGroup : curNumberGroups) {
				displayNumberline.add(numberGroup);
			}
			inputDisplayedNumbers.add(displayNumberline);
		}
		correctMappings = new ArrayList<ArrayList<String[]>>();
		String[] array;
		ArrayList<String[]> list; 
		for(int x=0; x<inputWireGroups.size(); x++) {
			list = new ArrayList<String[]>();
			for(int y=0; y<10; y++) {
				array = new String[0];
				list.add(array);				
			}
			correctMappings.add(list);
		}
	}
	

	public ArrayList<ArrayList<String>> getInputDisplayedNumbers() {
		return inputDisplayedNumbers;
	}
	public ArrayList<ArrayList<String>> getWireGroups() {
		return inputWireGroups;
	}
	public ArrayList<ArrayList<String[]>> getCorrectMappings() {
		return correctMappings;
	}
	public HashMap<String, String> getCurLetterMappings() {
		return curLetterMappings;
	}
	public ArrayList<String> getCurDisplayNumberOutputs() {
		return curDisplayNumberOutputs;
	}

	public int getTotalNumberGroupsOfUniuqueLengths() {
		int total = 0;
		for(int outer=0; outer<inputDisplayedNumbers.size(); outer++) {
			for(int inner=0; inner<inputDisplayedNumbers.get(outer).size(); inner++) {
				int curLength = inputDisplayedNumbers.get(outer).get(inner).length(); 
				if( (curLength==2) || (curLength==3) || (curLength==4) || (curLength==7) ) {
					total+=1;
				}
			}
		}
		return total;
	}

	public void populateEasyMappings(int index, ArrayList<String> wireGroups) {
		for (String wireGroup : wireGroups) {
			ArrayList<String> wireChars = new ArrayList<String>();
			for(int x=0; x<wireGroup.length(); x++) {
				wireChars.add(String.valueOf(wireGroup.charAt(x)));
			}
			Collections.sort(wireChars);
			String[] charList = new String[wireChars.size()];
			charList = wireChars.toArray(charList);
			if(wireGroup.length()==2) {
				correctMappings.get(index).set(1, charList);
			} else if(wireGroup.length()==3) {
				correctMappings.get(index).set(7, charList);
			} else if(wireGroup.length()==4) {
				correctMappings.get(index).set(4, charList);
			} else if(wireGroup.length()==7) {
				correctMappings.get(index).set(8, charList);
			}
		}
	}
	public void populateDifficultMappings(int index) {
		//Still need: 0,2,3,5,9
		ArrayList<String> wireGroups = inputWireGroups.get(index);
		for (int groupIndex=0; groupIndex<10; groupIndex++) {
			String wireGroup = wireGroups.get(groupIndex);
			ArrayList<String> wireChars = new ArrayList<String>();
			for(int x=0; x<wireGroup.length(); x++) {
				wireChars.add(String.valueOf(wireGroup.charAt(x)));
			}
			Collections.sort(wireChars);
			String[] charList = new String[wireChars.size()];
			charList = wireChars.toArray(charList);
			if(containsAllZeroWires(wireChars)) {
				correctMappings.get(index).set(0, charList);
			}
			if(containsAllTwoWires(wireChars)) {
				correctMappings.get(index).set(2, charList);
			}
			if(containsAllThreeWires(wireChars)) {
				correctMappings.get(index).set(3, charList);
			}
			if(containsAllFiveWires(wireChars)) {
				correctMappings.get(index).set(5, charList);
			}
			if(containsAllNineWires(wireChars)) {
				correctMappings.get(index).set(9, charList);
			}
		}
	}

	private boolean containsAllZeroWires(ArrayList<String> wireChars) {
		boolean isZero = wireChars.contains(curLetterMappings.get("a")) &&
				wireChars.contains(curLetterMappings.get("b")) &&
				wireChars.contains(curLetterMappings.get("c")) &&
				wireChars.contains(curLetterMappings.get("e")) &&
				wireChars.contains(curLetterMappings.get("f")) &&
				wireChars.contains(curLetterMappings.get("g")) &&
				wireChars.size()==6;
		return isZero;
	}
	private boolean containsAllTwoWires(ArrayList<String> wireChars) {
		boolean isZero = wireChars.contains(curLetterMappings.get("a")) &&
				wireChars.contains(curLetterMappings.get("c")) &&
				wireChars.contains(curLetterMappings.get("d")) &&
				wireChars.contains(curLetterMappings.get("e")) &&
				wireChars.contains(curLetterMappings.get("g")) &&
				wireChars.size()==5;
		return isZero;
	}
	private boolean containsAllThreeWires(ArrayList<String> wireChars) {
		boolean isZero = wireChars.contains(curLetterMappings.get("a")) &&
				wireChars.contains(curLetterMappings.get("c")) &&
				wireChars.contains(curLetterMappings.get("d")) &&
				wireChars.contains(curLetterMappings.get("f")) &&
				wireChars.contains(curLetterMappings.get("g")) &&
				wireChars.size()==5;
		return isZero;
	}
	private boolean containsAllFiveWires(ArrayList<String> wireChars) {
		boolean isZero = wireChars.contains(curLetterMappings.get("a")) &&
				wireChars.contains(curLetterMappings.get("b")) &&
				wireChars.contains(curLetterMappings.get("d")) &&
				wireChars.contains(curLetterMappings.get("f")) &&
				wireChars.contains(curLetterMappings.get("g")) &&
				wireChars.size()==5;
		return isZero;
	}
	private boolean containsAllNineWires(ArrayList<String> wireChars) {
		boolean isZero = wireChars.contains(curLetterMappings.get("a")) &&
				wireChars.contains(curLetterMappings.get("b")) &&
				wireChars.contains(curLetterMappings.get("c")) &&
				wireChars.contains(curLetterMappings.get("d")) &&
				wireChars.contains(curLetterMappings.get("f")) &&
				wireChars.contains(curLetterMappings.get("g")) &&
				wireChars.size()==6;
		return isZero;
	}

	public void populateCurLetterMappings(int index) {
		curLetterMappings = new HashMap<String, String>();
		curDisplayNumberOutputs = new ArrayList<String>();
		ArrayList<String> wireGroups = inputWireGroups.get(index);
		populateEasyMappings(index, wireGroups);
		curLetterMappings.put("a",  get_a_mapping(index));
		curLetterMappings.put("b",  get_b_mapping(index));
		curLetterMappings.put("c",  get_c_mapping(index));
		curLetterMappings.put("e",  get_e_mapping(index));
		curLetterMappings.put("f",  get_f_mapping(index));
		curLetterMappings.put("d",  get_d_mapping(index));
		curLetterMappings.put("g",  get_g_mapping());
	}

	private String get_a_mapping(int index) {
		//The difference between 7 and size 1 is the "a" wire
		String a = "";
		boolean found = false;
		for (String sevenWireLetter : correctMappings.get(index).get(7)) {
			for (String oneWireLetter : correctMappings.get(index).get(1)) {
				if(oneWireLetter.equals(sevenWireLetter)) {
					found=true;
					break;
				}
			}
			if(!found) {
				a = sevenWireLetter;
				break;
			} 
			found=false;
			//loop again
		}
		return a;
	}


	private String get_c_mapping(int index) {
		//Loop through length=6 groups, the one missing a value from 1 mapping is the "c"
		ArrayList<String> wireGroups = inputWireGroups.get(index);
		ArrayList<String> wiresWith6Len = new ArrayList<String>();
		for (String wireGroup : wireGroups) {
			if(wireGroup.length()==6) {
				wiresWith6Len.add(wireGroup);
			}
		}
		String c = "";
		String otherLetter = "";
		int numFoundInOne = 0;
		
		for (String curWireWith6Len : wiresWith6Len) {
			ArrayList<String> wireCharsWith6Len = new ArrayList<String>();
			for(int x=0; x<curWireWith6Len.length(); x++) {
				wireCharsWith6Len.add(String.valueOf(curWireWith6Len.charAt(x)));
			}
			numFoundInOne = 0;
			for (String curWireChar : wireCharsWith6Len) {
				if(numFoundInOne==2) {
					break;
				}
				if(correctMappings.get(index).get(1)[0].equals(curWireChar)) {
					numFoundInOne++;
					otherLetter = correctMappings.get(index).get(1)[1];
				} else if(correctMappings.get(index).get(1)[1].equals(curWireChar)) {
					numFoundInOne++;
					otherLetter = correctMappings.get(index).get(1)[0];
				}
			}
			if(numFoundInOne==1) {
				//we found the 6
				Collections.sort(wireCharsWith6Len);
				String[] charList = new String[6];
				charList = wireCharsWith6Len.toArray(charList);
				correctMappings.get(index).set(6, charList);
				break;
			}
		}
		return otherLetter;
	}

	private String get_f_mapping(int index) {
		//Since we know mapping of c and 1, we can get f
		String cMapping = curLetterMappings.get("c");
		if(correctMappings.get(index).get(1)[0].equals(cMapping)) {
			return correctMappings.get(index).get(1)[1];
		} else {
			return correctMappings.get(index).get(1)[0];
		}
	}

	public WireCounts getNumOfOccurrenceForEachLetter(int index) {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int g = 0;
		ArrayList<String> wireGroups = inputWireGroups.get(index);
		for (String wireGroup : wireGroups) {
			if(wireGroup.contains("a"))
				a++;
			if(wireGroup.contains("b"))
				b++;
			if(wireGroup.contains("c"))
				c++;
			if(wireGroup.contains("d"))
				d++;
			if(wireGroup.contains("e"))
				e++;
			if(wireGroup.contains("f"))
				f++;
			if(wireGroup.contains("g"))
				g++;
		}
		return new WireCounts(a, b, c, d, e, f, g);
	}
	
	private String get_b_mapping(int index) {
		//b happens 6 times
		WireCounts counts = getNumOfOccurrenceForEachLetter(index);
		return counts.getLetterWithCount(6);
	}
	private String get_e_mapping(int index) {
		//e happens 4 times
		WireCounts counts = getNumOfOccurrenceForEachLetter(index);
		return counts.getLetterWithCount(4);
	}

	private String get_d_mapping(int index) {
		//d is the last one in the 4 we don't have mapped
		String dMapping = "";
		String[] fourGroup = correctMappings.get(index).get(4);
		for (String fourLetter : fourGroup) {
			Collection<String> mappingValues = curLetterMappings.values();
			if(!mappingValues.contains(fourLetter)) {
				dMapping = fourLetter;
			}
		}
		return dMapping;
	}
	private String get_g_mapping() {
		//g is the last thing that doesn't have a value
		String gMapping = "";
		Collection<String> allValues = curLetterMappings.values();
		if(!allValues.contains("a")) {
			gMapping = "a";
		} else if(!allValues.contains("b")) {
			gMapping = "b";
		} else if(!allValues.contains("c")) {
			gMapping = "c";
		} else if(!allValues.contains("d")) {
			gMapping = "d";
		} else if(!allValues.contains("e")) {
			gMapping = "e";
		} else if(!allValues.contains("f")) {
			gMapping = "f";
		} else if(!allValues.contains("g")) {
			gMapping = "g";
		} else {
			throw new RuntimeException("Can't fingure out gMapping");
		}
		return gMapping;
	}

	public void populateDisplayNumberOutputs(int index) {
		for (int displayNumberIndex = 0; displayNumberIndex<inputDisplayedNumbers.get(index).size(); displayNumberIndex++) {
			String displayNumberString = inputDisplayedNumbers.get(index).get(displayNumberIndex);
			ArrayList<String> displayNumberChars = new ArrayList<String>();
			for(int x=0; x<displayNumberString.length(); x++) {
				displayNumberChars.add(String.valueOf(displayNumberString.charAt(x)));
			}
			Collections.sort(displayNumberChars);
			String[] charList = new String[displayNumberChars.size()];
			charList = displayNumberChars.toArray(charList);
			boolean found = true;
			for(int numToCheck=0; numToCheck<10; numToCheck++) {
				found = true;
				if(correctMappings.get(index).get(numToCheck).length != charList.length) {
					found = false;
				} else {
					for(int charIndexForNumToCheck=0; charIndexForNumToCheck<correctMappings.get(index).get(numToCheck).length; charIndexForNumToCheck++) {
						String curCharForNumToCheck = correctMappings.get(index).get(numToCheck)[charIndexForNumToCheck];
						if(charIndexForNumToCheck==6 && charList.length==6) {
							System.out.println("error here");
						}
						if(!charList[charIndexForNumToCheck].equals(curCharForNumToCheck)) {
							found = false;
							break;
						}
					}
				}
				if(found) {
					curDisplayNumberOutputs.add(String.valueOf(numToCheck));
					break;
				}
			}
		}
	}

	public Integer get4digitOutputNumber() {
		int value = 0;
		value+=Integer.valueOf(curDisplayNumberOutputs.get(0)) * 1000;
		value+=Integer.valueOf(curDisplayNumberOutputs.get(1)) * 100;
		value+=Integer.valueOf(curDisplayNumberOutputs.get(2)) * 10;
		value+=Integer.valueOf(curDisplayNumberOutputs.get(3));
		return value;
	}
}
