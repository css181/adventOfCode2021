package day13;


public class Paper {

	private char[][] grid;
	public Paper(int maxX, int maxY) {
		grid = new char[maxY][maxX];
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[row].length; col++) {
				grid[row][col] = '.';
			}
		}
	}

	public char[][] getGrid() {
		return grid;
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Paper)) { return false; }
        Paper other = (Paper) obj;
        
        if(!this.grid.equals(other.grid)) { return false; }
        
        return true;
    }

	@Override
    public String toString() {
		String print = "";
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[row].length; col++) {
				print+=grid[row][col];
			}
			print+="\n";
		}
		return print;
    }

	public void addDot(Dot dot) {
		grid[dot.getY()][dot.getX()] = '#';
	}

	public void horizontalFoldAtY(int yVal) {
		for(int row=yVal+1; row<grid.length; row++) {
			for(int col=0; col<grid[row].length; col++) {
				if(grid[row][col]=='#') {
					grid[yVal-(row-yVal)][col]='#';
				}
			}
		}
		completeFold(yVal, grid[0].length);
	}

	public void verticalFoldAtX(int xVal) {
		for(int row=0; row<grid.length; row++) {
			for(int col=xVal+1; col<grid[row].length; col++) {
				if(grid[row][col]=='#') {
					grid[row][xVal-(col-xVal)]='#';
				}
			}
		}
		completeFold(grid.length, xVal);
	}
	
	private void completeFold(int yMax, int xMax) {
		char[][] newGrid = new char[yMax][xMax];
		for(int row=0; row<newGrid.length; row++) {
			for(int col=0; col<newGrid[row].length; col++) {
				newGrid[row][col] = grid[row][col] ;
			}
		}
		grid = newGrid;
	}

	public Integer getTotalDots() {
		int total = 0;
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[row].length; col++) {
				if(grid[row][col]=='#')
					total++;
			}
		}
		return total;
	}
}
