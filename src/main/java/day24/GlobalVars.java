package day24;

import java.util.ArrayList;

public class GlobalVars {

	public static ArrayList<Integer> MONAD_input;
	public static int MONAD_index = 0;
	public static int w = 0;
	public static int x = 0;
	public static int y = 0;
	public static int z = 0;
	
	public static String print() {
		return "w=" + w + ", x=" + x + ", y=" + y + ", __ z=" + z + ", ~ MONAD=" + MONAD_input;
	}
}
