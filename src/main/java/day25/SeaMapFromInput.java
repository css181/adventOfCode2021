package day25;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;

import day1.FileUtility;
import day25.spots.Cucumber;
import day25.spots.EastCucumber;
import day25.spots.ISpot;
import day25.spots.SouthCucumber;

public class SeaMapFromInput {

	private static File file;
	private SeaMap seaMap;
	
	public SeaMapFromInput() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}
	
	protected void setFileToUse(File file) {
		SeaMapFromInput.file = file;
	}

	public void populateSeaMap() {
		ArrayList<ArrayList<Character>> input = FileUtility.convertFileToCharacterArray(file);
		seaMap = new SeaMap(input);
	}

	public SeaMap getSeaMap() {
		return seaMap;
	}
	
	public int getNumOfStepsUntilNoMovesArePossible() {
		int numOfSteps = 0;
		ISpot [][] preStepMap = null;
		do {
			preStepMap = createNewCopyOfMap(seaMap.getMap());
			performMoveStep();
			numOfSteps++;
		} while (!areMapsEqual(preStepMap, seaMap.getMap()));
		return numOfSteps;
	}

	private boolean areMapsEqual(ISpot[][] one, ISpot[][] two) {
		if(one.length != two.length) { return false; }
		for(int row=0; row<one.length; row++) {
			if(one[row].length != two[row].length) { return false; }
			for(int col=0; col<one[row].length; col++) {
				if(!one[row][col].equals(two[row][col])) { return false; }
			}
		}
		return true;
	}

	public void performMoveStep() {
		ISpot[][] newMap = createNewCopyOfMap(seaMap.getMap());
		attemptToMoveAllEastCucumbers(newMap);
		attemptToMoveAllSouthCucumbers(newMap);
	}

	private void attemptToMoveAllEastCucumbers(ISpot[][] newMap) {
		for (ISpot[] row : seaMap.getMap()) {
			for (ISpot spot : row) {
				if(spot instanceof EastCucumber) {
					if(((Cucumber)spot).isAbleToMove(seaMap.getMap())) {
						((Cucumber)spot).performMove(newMap);
					}
				}
			}
		}
		seaMap.setMap(createNewCopyOfMap(newMap));
	}
	//TODO: Figure out how to parameterize instanceOf
	private void attemptToMoveAllSouthCucumbers(ISpot[][] newMap) {
		for (ISpot[] row : seaMap.getMap()) {
			for (ISpot spot : row) {
				if(spot instanceof SouthCucumber) {
					if(((Cucumber)spot).isAbleToMove(seaMap.getMap())) {
						((Cucumber)spot).performMove(newMap);
					}
				}
			}
		}
		seaMap.setMap(newMap);
	}

	private ISpot[][] createNewCopyOfMap(ISpot[][] map) {
		ISpot [][] newMap = new ISpot[map.length][map[0].length];
		for(int row=0; row<map.length; row++) {
			for(int col=0; col<map[row].length; col++) {
				ISpot spot = null;
				try {
					spot = map[row][col].getClass().getDeclaredConstructor(Coordinate.class).newInstance(new Coordinate(col, row));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				newMap[row][col] = spot;
			}
		}
		return newMap;
	}
	
	//Used For Debugging
	@SuppressWarnings("unused")
	private void printMaps(ISpot[][] newMap, ISpot[][] original) {
		
		for (int rowIndex=0; rowIndex<newMap.length; rowIndex++) {
			ISpot[] row = newMap[rowIndex];
			for (ISpot spot : row) {
				System.out.print(spot.displayValue());
			}
			System.out.print("   ");
			for (int colIndex=0; colIndex<=original.length; colIndex++) {
				System.out.print(original[rowIndex][colIndex].displayValue());
			}
			System.out.print("\n");
		}
	}
}
