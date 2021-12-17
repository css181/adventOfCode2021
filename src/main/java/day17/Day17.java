package day17;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import day1.FileUtility;
import day3.DecimalUtil;

public class Day17 {
	
	private static File file;
	private ProbeFireArea area;
	protected Coordinate targetTopLeft;
	protected Coordinate targetBottomRight;
	private boolean missedByOverTargetX = false;
	private boolean missedByOverTargetY = false;
	
	public Day17() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}

	protected void getInputs() {
		targetTopLeft=new Coordinate(244, -91);
		targetBottomRight = new Coordinate(303, -54);
	}
	protected void setFileToUse(File file) {
		Day17.file = file;
	}
	
	public void defineArea(int x, int y, int yOffset) {
		area = new ProbeFireArea(x, y, yOffset);
	}
	public void defineArea() {
		area = new ProbeFireArea(targetBottomRight.getX()+1, targetBottomRight.getY()*-20, targetBottomRight.getY()*20);
	}
	public ProbeFireArea getArea() {
		return area;
	}

	public void applyTargetToArea() {
		//Note: the actual indexes are 0 based, 
		//but coordinates from input are 1 based for x
		for(int x=targetTopLeft.getX(); x<targetBottomRight.getX()+1; x++) {
			for(int y=targetTopLeft.getY(); y<targetBottomRight.getY()+1; y++) {
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
			if(newX>targetBottomRight.getX()) {
				if(newX-area.getGrid()[0].length > targetBottomRight.getX() - targetTopLeft.getX()) {
					missedByOverTargetX=true;
				}
				break;//we've gone past the target zone on X axis
			}
			int newY = curProbeSpot.getY()+newYVel;
			if(newY> maxYValue) {
				maxYValue=newY;
			}
			if(newY>area.getYOffset()) {
				throw new RuntimeException("probe shot above [0,0] of the grid.");
			}
			if(Math.abs(newY)>=Math.abs(targetTopLeft.getY())) {
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
				curProbeSpot.getY() >= targetTopLeft.getY() &&
				curProbeSpot.getY() <= targetBottomRight.getY());
	}

//	public Coordinate getVelocityOfHighestYValueToHitTarget() {
//		int maxYEver = Integer.MIN_VALUE;
//		Coordinate topVelocity;
//		int xTry=0;
//		int yTry=0;
//		int curTryYMax;
//		do {
//			Coordinate curTryVelocity = new Coordinate(xTry, yTry);
//			curTryYMax=fireProbeWithVelocity(curTryVelocity);
//			if(curTryYMax>maxYEver) {
//				maxYEver=curTryYMax;
//				topVelocity = curTryVelocity;
//			}
//			if(curTryYMax==Integer.MIN_VALUE) {
//				//Did NOT hit
//				if(missedByOverTargetY) {
//					xTry++;
//					yTry=0;
//					missedByOverTargetY = false;
//				}
//				if(missedByOverTargetX) {
//					yTry++;
//					xTry=0;
//					missedByOverTargetX = false;
//				}
//			} else {
//				//DID hit
//				yTry++;
//			}
//		}while(true);
//		return topVelocity;
//	}

}
