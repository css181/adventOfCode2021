package day10;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import day1.FileUtility;

public class Day10 {
	
	protected ArrayList<String> exceptionPops;
	protected ArrayList<ArrayList<String>> completionStrings;
	private ArrayList<Long> completionScores;
	private static File file;
	private ArrayList<ArrayList<String>> openCloseInputs;
	private MyStack myStack = new MyStack();
	
	public Day10() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		getInputs();
		exceptionPops = new ArrayList<String>();
		completionScores = new ArrayList<Long>();
	}

	protected void getInputs() {
		openCloseInputs = FileUtility.convertFileToStringArrayOfArrays(file);
		completionStrings = new ArrayList<ArrayList<String>>();
	}
	
	protected void setFileToUse(File file) {
		Day10.file = file;
	}

	public ArrayList<ArrayList<String>> getOpenCloseInputs() {
		return openCloseInputs;
	}

	protected void setMyStack(MyStack stack) {
		myStack = stack;
	}

	public void processInput(String string) {
		String value = "";
		switch (string) {
		case "(":
		case "[":
		case "{":
		case "<":
			myStack.push(string);
			break;
		case ")":
			value = myStack.pop();
			if(!value.equals("(")) {
				exceptionPops.add(string);
			}
			break;
		case "]":
			value = myStack.pop();
			if(!value.equals("[")) {
				exceptionPops.add(string);
			}
			break;
		case "}":
			value = myStack.pop();
			if(!value.equals("{")) {
				exceptionPops.add(string);
			}
			break;
		case ">":
			value = myStack.pop();
			if(!value.equals("<")) {
				exceptionPops.add(string);
			}
			break;
		default:
			throw new RuntimeException("Don't know how to process: " + string);
		}
	}

	public ArrayList<String> getExceptionPops() {
		return exceptionPops;
	}
	public ArrayList<ArrayList<String>> getCompletionStrings() {
		return completionStrings;
	}
	public ArrayList<Long> getCompletionScores() {
		return completionScores;
	}

	public Integer getPointsForException(String string) {
		switch (string) {
		case ")":
			return 3;
		case "]":
			return 57;
		case "}":
			return 1197;
		case ">":
			return 25137;
		default:
			throw new RuntimeException("Can't get points for: " + string);
		}
	}

	public void addCompletionSequence() {
		ArrayList<String> curCompletionString = new ArrayList<String>();
		do {
			String value = myStack.pop();
			switch (value) {
			case "(":
				curCompletionString.add(")");
				break;
			case "[":
				curCompletionString.add("]");
				break;
			case "{":
				curCompletionString.add("}");
				break;
			case "<":
				curCompletionString.add(">");
				break;
			default:
				throw new RuntimeException("Can't complete sequence for: " + value);
			}
		}while(!myStack.isEmpty());
		completionStrings.add(curCompletionString);
	}

	public long calculateCompletionScore(ArrayList<String> completionList) {
		long total = 0;
		for (String closeString : completionList) {
			total*=5;
			switch (closeString) {
			case ")":
				total+=1;
				break;
			case "]":
				total+=2;
				break;
			case "}":
				total+=3;
				break;
			case ">":
				total+=4;
				break;
			default:
				throw new RuntimeException("Can't get completion score for: " + closeString);
			}
		}
		completionScores.add(total);
		return total;
	}

	public Long getMiddleCompletionScore() {
		if(completionScores.size() % 2 == 0) {
			throw new RuntimeException("There are even number of CompletionStrings, can't get mid.");
		}
		Collections.sort(completionScores);
		return completionScores.get((completionScores.size() / 2));
	}
	
	
}
