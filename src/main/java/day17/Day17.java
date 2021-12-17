package day17;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Day17 {
	
	private static File file;
	private ProbeFireArea area;
	protected Coordinate targetTopLeft;
	protected Coordinate targetBottomRight;
	private boolean missedByOverTargetX = false;
	private boolean missedByOverTargetY = false;
	private boolean didOverShootTarget = false;
	
	public Day17() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}

	protected void getInputs() {
		targetTopLeft=new Coordinate(244, -54);
		targetBottomRight = new Coordinate(303, -91);
	}
	protected void setFileToUse(File file) {
		Day17.file = file;
	}
	
	public void defineArea(int x, int y, int yOffset) {
		area = new ProbeFireArea(x, y, yOffset);
	}
	public void defineArea(int topYHeight) {
		area = new ProbeFireArea(targetBottomRight.getX()+1, topYHeight+Math.abs(targetBottomRight.getY())+1, topYHeight*-1);
	}
	public ProbeFireArea getArea() {
		return area;
	}

	public void applyTargetToArea() {
		//Note: the actual indexes are 0 based, 
		//but coordinates from input are 1 based for x
		for(int x=targetTopLeft.getX(); x<targetBottomRight.getX()+1; x++) {
			for(int y=targetTopLeft.getY(); y>targetBottomRight.getY()-1; y--) {
				area.addTargetSpot(new Coordinate(x, y));
			}
		}
	}

	//Returns Integer.MIN_VALUE if never hit
	//Otherwise returns the top Y value
	public int fireProbeWithVelocity(Coordinate velocity) {
		int maxYValue = Integer.MIN_VALUE;
		Coordinate curProbeSpot = new Coordinate(0, 0);
		int stepNumber=0;
		do {
			if(inTargetZone(curProbeSpot)) {
				break;
			}
			int newXVel = stepNumber>=velocity.getX() ? 0 : velocity.getX()-stepNumber;
			int newYVel = velocity.getY()-stepNumber;
			int newX = curProbeSpot.getX()+newXVel;
			int newY = curProbeSpot.getY()+newYVel;
			if(newX>targetBottomRight.getX()) {
				if(newX-area.getGrid()[0].length > targetBottomRight.getX() - targetTopLeft.getX()) {
					missedByOverTargetX=true;
				}
				if(newY>targetTopLeft.getY()) {
					didOverShootTarget = true;
				}
				break;//we've gone past the target zone on X axis
			}
			if(newY> maxYValue) {
				maxYValue=newY;
			}
			if(newY>area.getYOffset()) {
				throw new RuntimeException("probe shot above [0,0] of the grid.");
			}
			if(newY<targetBottomRight.getY()) {
				if(Math.abs(newY) > Math.abs(targetBottomRight.getY()) - Math.abs(targetTopLeft.getY())) {
					missedByOverTargetY=true;
				}
				break;//we've gone past the target zone on Y axis
			}
			curProbeSpot = new Coordinate(newX, newY);
			area.addProbeSpot(curProbeSpot);
			stepNumber++;
		}while(true);//Break from if's
		if(!inTargetZone(curProbeSpot)) {
			maxYValue = Integer.MIN_VALUE;
		}
		return maxYValue;
	}

	private boolean inTargetZone(Coordinate curProbeSpot) {
		return (curProbeSpot.getX() >= targetTopLeft.getX() &&
				curProbeSpot.getX() <= targetBottomRight.getX() &&
				curProbeSpot.getY() <= targetTopLeft.getY() &&
				curProbeSpot.getY() >= targetBottomRight.getY());
	}

	public Coordinate getVelocityOfHighestYValueToHitTarget() {
		int maxYEver = Integer.MIN_VALUE;
		Coordinate topVelocity = null;
		int curTryYMax;
		ArrayList<Integer> possibleYs = getPossibleYs();
		for(int xTry=1; xTry<targetTopLeft.getX(); xTry++) {
			for (Integer yTry : possibleYs) {
				Coordinate curTryVelocity = new Coordinate(xTry, yTry);
				curTryYMax=fireProbeWithVelocity(curTryVelocity);
				if(curTryYMax>maxYEver) {
					maxYEver=curTryYMax;
					topVelocity = curTryVelocity;
				}
			}
		}
		return topVelocity;
	}

	public ArrayList<Integer> getPossibleYs() {
		ArrayList<Integer> possibleYs = new ArrayList<Integer>();
		for(int yTry=0; yTry<500; yTry++) {
			int yHigh = (yTry*yTry+yTry)/2;
			int step=0;
			do {
				step++;
				yHigh-=step;
				if(yHigh<=targetTopLeft.getY() && yHigh>=targetBottomRight.getY()) {
					possibleYs.add(yTry);
					break;
				}
			}while(yHigh>targetBottomRight.getY());
		}
		return possibleYs;
	}
}
