package day10;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Day10 {
	
	protected ArrayList<String> exceptionPops;
	private static File file;
	private ArrayList<ArrayList<String>> openCloseInputs;
	private MyStack myStack = new MyStack();
	
	public Day10() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
		getInputs();
		exceptionPops = new ArrayList<String>();
	}

	protected void getInputs() {
		openCloseInputs = new ArrayList<ArrayList<String>>();
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		ArrayList<String> curLine;
		for (String inputLine : inputs) {
			curLine = new ArrayList<String>();
			for(int pos=0; pos<inputLine.length(); pos++) {
				curLine.add(String.valueOf(inputLine.charAt(pos)));
			}
			openCloseInputs.add(curLine);
		}
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
	
	
}
