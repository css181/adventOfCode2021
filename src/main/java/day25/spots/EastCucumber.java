package day25.spots;

public class EastCucumber implements Spot {

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String displayValue() {
		return ">";
	}

}
