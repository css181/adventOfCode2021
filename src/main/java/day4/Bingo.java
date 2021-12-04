package day4;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;

public class Bingo {

	private static File file;
	public ArrayList<Board> boards;
	public ArrayList<Integer> ballCalls;
	public static int curBallCallIndex = 0;
	public boolean isWinner = false;
	public int indexOfLastWinningBoard = -1;
	
	public Bingo() {
		URL fileName = getClass().getResource("bingo.txt");
		file = new File(fileName.getPath());
		boards = new ArrayList<Board>();
		populateBoards();
		populateBallCalls();
	}
	
	private void populateBallCalls() {
		ArrayList<String> inputs = getAllInputs();
		String listOfCalls = inputs.get(0);
		ArrayList<Integer> calls = new ArrayList<Integer>();
		for (String ball : listOfCalls.split(",")) {
			calls.add(Integer.valueOf(ball));
		}
		ballCalls = calls;
	}

	private void populateBoards() {
		ArrayList<String> inputs = getAllInputs();
		int curRow = 0;
		Board board = new Board();
		for (int line=2; line<inputs.size(); line++) {
			String input = inputs.get(line);
			if(curRow!=5) {
				board.addRow(input);
				curRow++;
			} else {
				boards.add(board);
				curRow = 0;
				board = new Board();
			}
		}
	}

	public void printAllBoards() {
		System.out.println();
		System.out.println("Num of boards: " + boards.size());
		int boardNum = 0;
		for (Board board : boards) {
			System.out.println("Board Num [" + boardNum + "] ~ " + board.numWonOn);
			System.out.println(board);
			System.out.println();
			boardNum++;
		}
	}
	
	protected void setFileToUse(File file) {
		Bingo.file = file;
	}


	public void callABall() {
		int curBallNumber = ballCalls.get(curBallCallIndex++);
		int latestWinningBoardNum = 0;
		for (Board board : boards) {
			board.markNumberAsCalled(curBallNumber);
			isWinner = board.isAWinner(curBallNumber);
			if(isWinner) {
				indexOfLastWinningBoard = latestWinningBoardNum;
			} else {
				latestWinningBoardNum++;
			}
		}
	}
	
	public ArrayList<Integer> getBoardIndexesThatHaveNotWonYet() {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		int index = 0;
		for (Board board : boards) {
			if(board.numWonOn==-1) {
				indexes.add(index);
			}
			index++;
		}
		return indexes;
	}

	public ArrayList<String> getAllInputs() {
		ArrayList<String> inputs = FileUtility.convertFileToStringArray(file);
		return inputs;
	}

}
