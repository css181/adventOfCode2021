package day15;
import java.util.Arrays;

public class MazeSolver {

    final static int TRIED = 2;
    final static int PATH = 3;

    // @formatter:off
    private static int[][] GRID = 			{
			{1,1,6,3,7,5,1,7,4,2},
			{1,3,8,1,3,7,3,6,7,2},
			{2,1,3,6,5,1,1,3,2,8},
			{3,6,9,4,9,3,1,5,6,9},
			{7,4,6,3,4,1,7,1,1,1},
			{1,3,1,9,1,2,8,1,3,7},
			{1,3,5,9,9,1,2,4,2,1},
			{3,1,2,5,4,2,1,6,3,9},
			{1,2,9,3,1,3,8,5,2,1},
			{2,3,1,1,9,4,4,5,8,1},
	};
    // @formatter:off

    public static void main(String[] args) {
        MazeSolver maze = new MazeSolver(GRID);
        boolean solved = maze.solve();
        System.out.println("Solved: " + solved);
        System.out.println(maze.toString());
    }

    private int[][] grid;
    private int height;
    private int width;
    private int total;

    private int[][] map;

    public MazeSolver(int[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        this.width = grid[0].length;
        this.total = 0;

        this.map = new int[height][width];
    }

    public int solve() {
        return traverse(0,0);
    }

    private int traverse(int i, int j) {
        if (!isValid(i,j)) {
            return total;
        }

        if ( isEnd(i, j) ) {
            map[i][j] = PATH;
            return total;
        } else {
            map[i][j] = TRIED;
        }

        // North
        if (traverse(i - 1, j)) {
            map[i-1][j] = PATH;
            total+=grid[i-1][j];
            return total;
        }
        // East
        if (traverse(i, j + 1)) {
            map[i][j + 1] = PATH;
            total+=grid[i][j+1];
            return total;
        }
        // South
        if (traverse(i + 1, j)) {
            map[i + 1][j] = PATH;
            total+=grid[i+1][j];
            return total;
        }
        // West
        if (traverse(i, j - 1)) {
            map[i][j - 1] = PATH;
            total+=grid[i1][j-1];
            return total;
        }

        return total;
    }

    private boolean isEnd(int i, int j) {
        return i == height - 1 && j == width - 1;
    }

    private boolean isValid(int i, int j) {
        if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
            return true;
        }

        return false;
    }

    private boolean isOpen(int i, int j) {
        return true;
    }

    private boolean isTried(int i, int j) {
        return map[i][j] == TRIED;
    }

    private boolean inRange(int i, int j) {
        return inHeight(i) && inWidth(j);
    }

    private boolean inHeight(int i) {
        return i >= 0 && i < height;
    }

    private boolean inWidth(int j) {
        return j >= 0 && j < width;
    }

    public String toString() {
        String s = "";
        for (int[] row : map) {
            s += Arrays.toString(row) + "\n";
        }

        return s;
    }

}