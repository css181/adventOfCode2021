package day12;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import day1.FileUtility;

public class Day12 {
	
	private Cave startCave = null;
	private Cave endCave = null;
	private ArrayList<Cave> otherCaves = new ArrayList<Cave>();
	private ArrayList<ArrayList<String>> allPaths = new ArrayList<ArrayList<String>>();
	private ArrayList<String> curPath = new ArrayList<String>();
	private boolean keepSearchingPath = true;
	private static File file;
	
	public Day12() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}

	protected void getInputs() {
		ArrayList<String> inputLines = FileUtility.convertFileToStringArray(file);
		for (String inputLine : inputLines) {
			int dashPos=inputLine.indexOf("-");
			String beforeName = inputLine.substring(0, dashPos);
			String afterName = inputLine.substring(dashPos+1);
			createCaveIfNotAlreadyCreated(beforeName);
			createCaveIfNotAlreadyCreated(afterName);
			addConnectionToEachCave(beforeName, afterName);
		}
		endCave.wipeConnections();
	}

	private void addConnectionToEachCave(String beforeName, String afterName) {
		Cave before = getCave(beforeName);
		Cave after = getCave(afterName);
		before.addConnection(after);
		after.addConnection(before);
	}

	private Cave getCave(String name) {
		if(name.equals("start")) {
			return startCave;
		} else if(name.equals("end")) {
			return endCave;
		} else {
			for (Cave cave : otherCaves) {
				if(cave.getName().equals(name)) {
					return cave;
				}
			}
		}
		//If here we have a bad name
		throw new RuntimeException("Can't find cave named: " + name + " anywhere.");
	}

	private void createCaveIfNotAlreadyCreated(String caveName) {
		if(caveName.equals("start")) {
			if(startCave==null) {
				startCave = new Cave(caveName);
			}
		} else if(caveName.equals("end")) {
			if(endCave==null) {
				endCave = new Cave(caveName);
			}
		} else {
			boolean found = false;
			for (Cave cave : otherCaves) {
				if(cave.getName().equals(caveName)) {
					found = true;
					break;
				}
			}
			if(!found) {
				otherCaves.add(new Cave(caveName));
			}
		}
	}

	protected void setFileToUse(File file) {
		Day12.file = file;
	}

	public Cave getStartCave() {
		return startCave;
	}
	public Cave getEndCave() {
		return endCave;
	}
	public ArrayList<Cave> getOtherCaves() {
		return otherCaves;
	}

	public ArrayList<ArrayList<String>> getAllPaths() {
		return allPaths;
	}

	private boolean isSecondTimeLandingOnLowerCaseCave(Cave cave) {
		return curPath.contains(cave.getName()) && 
				cave.getName().toLowerCase().equals(cave.getName());
	}
	
	
	public void goToNext(Cave curCave) {
		if(isSecondTimeLandingOnLowerCaseCave(curCave)) {
			return;
		}
		curPath.add(curCave.getName());
		Iterator<Cave> it = curCave.getConnections().iterator();
		if(curCave.equals(endCave)) {
			allPaths.add(cloneList(curPath));
		}
		while(it.hasNext()) {
			if(curCave.equals(startCave)) {
				curPath = new ArrayList<String>();
				curPath.add(startCave.getName());
			}
			Cave next = it.next();
			goToNext(next);
		}
		curPath.remove(curPath.size()-1);
	}

	private ArrayList<String> cloneList(ArrayList<String> list) {
		ArrayList<String> clone = new ArrayList<String>();
		for (String string : list) {
			clone.add(string);
		}
		return clone;
	}
}
