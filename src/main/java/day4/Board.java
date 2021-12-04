package day4;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	//5x5 grid
	ArrayList<ArrayList<Integer>> numbers;
	ArrayList<ArrayList<Boolean>> called;
	public int numWonOn = -1;
	
	public Board() {
		numbers = new ArrayList<ArrayList<Integer>>();
		called = new ArrayList<ArrayList<Boolean>>();
		ArrayList<Boolean> noCallRow0 = new ArrayList<Boolean>();
		noCallRow0.addAll(Arrays.asList(false, false, false, false, false));
		ArrayList<Boolean> noCallRow1 = new ArrayList<Boolean>();
		noCallRow1.addAll(Arrays.asList(false, false, false, false, false));
		ArrayList<Boolean> noCallRow2 = new ArrayList<Boolean>();
		noCallRow2.addAll(Arrays.asList(false, false, false, false, false));
		ArrayList<Boolean> noCallRow3 = new ArrayList<Boolean>();
		noCallRow3.addAll(Arrays.asList(false, false, false, false, false));
		ArrayList<Boolean> noCallRow4 = new ArrayList<Boolean>();
		noCallRow4.addAll(Arrays.asList(false, false, false, false, false));
		called.add(noCallRow0);
		called.add(noCallRow1);
		called.add(noCallRow2);
		called.add(noCallRow3);
		called.add(noCallRow4);
	}
	
	public void markNumberAsCalled(int number) {
		for(int row=0; row<5; row++) {
			ArrayList<Boolean> calledRow = called.get(row);
			for(int col=0; col<5; col++) {
				if(numbers.get(row).get(col)==number) {
					calledRow.set(col, true);
				}
			}
		}
	}
	
	//returns 0 if not a winner, 
	//or the sum of the row/col of called numbers if winner
	public boolean isAWinner(int calledNumber) {
		if(numWonOn!=-1) {
			//already won
			return true;
		}
		boolean isWinner = false;
		//row check
		for(int row=0; row<5; row++) {
			isWinner = true;//prove otherwise
			for(int col=0; col<5; col++) {
				if(called.get(row).get(col)==false) {
					isWinner = false;
					break;
				}
			}
			if(isWinner) {
				numWonOn = calledNumber;
				return true;
			}
		}
		//col check
		for(int col=0; col<5; col++) {
			isWinner = true;//prove otherwise
			for(int row=0; row<5; row++) {
				if(called.get(row).get(col)==false) {
					isWinner = false;
					break;
				} 
			}
			if(isWinner) {
				numWonOn = calledNumber;
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Integer> getRow(int rowNum) {
		ArrayList<Integer> row = new ArrayList<Integer>();
		for(int i=0; i<5; i++) {
			int number = numbers.get(rowNum).get(i);
			row.add(number);
		}
		return row;
	}
	public ArrayList<Integer> getCol(int colNum) {
		ArrayList<Integer> col = new ArrayList<Integer>();
		for(int i=0; i<5; i++) {
			col.add(numbers.get(i).get(colNum));
		}
		return col;
	}
	
	public void addRow(String rowNumbers) {
		ArrayList<Integer> row = new ArrayList<Integer>();
		int begin = 0;
		int end = 2;
		do {
			String curNumber = rowNumbers.substring(begin, end).trim();
			row.add(Integer.valueOf(curNumber));
			begin = end+1;
			end = begin+2;
		}while(end<=rowNumbers.length());
		numbers.add(row);
	}
	
	public int getSumOfAll_UNCalledNumbers() {
		int total = 0;
		for(int col=0; col<5; col++) {
			for(int row=0; row<5; row++) {
				if(called.get(row).get(col)==false) {
					total += numbers.get(row).get(col);
				}
			}
		}
		return total;
	}
	
    @Override
    public String toString() {
    	ArrayList<String> fullRow;
    	ArrayList<ArrayList<String>> card = new ArrayList<ArrayList<String>>();
		for(int row=0; row<5; row++) {
			fullRow = new ArrayList<String>();
			for(int col=0; col<5; col++) {
				if(called.get(row).get(col)) {
					fullRow.add("[" + numbers.get(row).get(col) + "]");
				} else {
					fullRow.add(String.valueOf(numbers.get(row).get(col)));
				}
			}
			card.add(fullRow);
		}
		return card.get(0).get(0) + " " + card.get(0).get(1) + " " + card.get(0).get(2) + " " + card.get(0).get(3) + " " + card.get(0).get(4) + "\n" +
		card.get(1).get(0) + " " + card.get(1).get(1) + " " + card.get(1).get(2) + " " + card.get(1).get(3) + " " + card.get(1).get(4) + "\n" +
		card.get(2).get(0) + " " + card.get(2).get(1) + " " + card.get(2).get(2) + " " + card.get(2).get(3) + " " + card.get(2).get(4) + "\n" +
		card.get(3).get(0) + " " + card.get(3).get(1) + " " + card.get(3).get(2) + " " + card.get(3).get(3) + " " + card.get(3).get(4) + "\n" +
		card.get(4).get(0) + " " + card.get(4).get(1) + " " + card.get(4).get(2) + " " + card.get(4).get(3) + " " + card.get(4).get(4);
    } 
}
