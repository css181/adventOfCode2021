package day17;


public class ProbeFireArea {
	
	private int yOffset;

	private char[][] grid;
	public ProbeFireArea(int maxX, int maxY, int minY) {
		yOffset = minY*-1;
		grid = new char[maxY+Math.abs(minY)][maxX];
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[row].length; col++) {
				grid[row][col] = '.';
			}
		}
		grid[yOffset][0] = 'S';
	}

	public char[][] getGrid() {
		return grid;
	}
	public int getYOffset() {
		return yOffset;
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof ProbeFireArea)) { return false; }
        ProbeFireArea other = (ProbeFireArea) obj;
        
        if(!this.grid.equals(other.grid)) { return false; }
        
        return true;
    }

	@Override
    public String toString() {
		String print = "0 - ";
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[row].length; col++) {
				print+=grid[row][col];
			}
			print+="\n" + row+1 + " - ";
		}
		return print;
    }

	public void addProbeSpot(Coordinate dot) {
		if(dot.getY()>=0) {
			grid[Math.abs(yOffset)-dot.getY()][dot.getX()] = '#';
		} else {
			grid[Math.abs(yOffset)+Math.abs(dot.getY())][dot.getX()] = '#';
		}
	}

	public void addTargetSpot(Coordinate dot) {
		if(dot.getY()>=0) {
			grid[Math.abs(yOffset)-dot.getY()][dot.getX()] = 'T';
		} else {
			grid[Math.abs(yOffset)+Math.abs(dot.getY())][dot.getX()] = 'T';
		}
	}
}
