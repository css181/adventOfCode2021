package day25.spots;

public class EmptySpot implements Spot {

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public String displayValue() {
		return ".";
	}

}
