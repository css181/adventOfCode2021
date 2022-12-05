package day25;

import java.io.File;
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

	public void performMoveStep() {
		ISpot[][] newMap = createNewCopyOfMap(seaMap.getMap());
		attemptToMoveAllEastCucumbers(newMap);
		attemptToMoveAllSouthCucumbers(newMap);
	}

	private ISpot[][] createNewCopyOfMap(ISpot[][] map) {
		ISpot [][] newMap = new ISpot[map.length][];
		for(int i = 0; i < map.length; i++)
			newMap[i] = map[i].clone();
		return newMap;
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
		seaMap.setMap(newMap);
	}
	//TODO: Figure out how to parameterize instanceOf
	private void attemptToMoveAllSouthCucumbers(ISpot[][] newMap) {
		for (ISpot[] row : seaMap.getMap()) {
			for (ISpot spot : row) {
				if(spot instanceof SouthCucumber) {
					if(((Cucumber)spot).isAbleToMove(seaMap.getMap())) {
						System.out.println("BEFORE:");
						printMap(newMap);
						((Cucumber)spot).performMove(newMap);
						System.out.println("AFTER:");
						printMap(newMap);
						System.out.println("break");
					}
				}
			}
		}
		seaMap.setMap(newMap);
	}
	
	private void printMap(ISpot[][] map) {
		for (ISpot[] row : map) {
			for (ISpot spot : row) {
				System.out.print(spot.displayValue());
			}
			System.out.print("\n");
		}
	}

}
