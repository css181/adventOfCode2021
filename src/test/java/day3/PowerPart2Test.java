package day3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class PowerPart2Test {

	PowerCalculation powerCalculation = new PowerCalculation();

	@Test
	void getInputsThatHaveKeyValueForByte_test() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		powerCalculation.setFileToUse(new File(fileName.getPath()));
		ArrayList<String> allInputs = new ArrayList<String>();
		allInputs.add("001");
		allInputs.add("010");
		allInputs.add("010");
		allInputs.add("111");
		ArrayList<String> inputs = powerCalculation.getAllInputs();
		assertEquals(allInputs, inputs);
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("001");
		expected.add("010");
		expected.add("010");
		ArrayList<String> actual = powerCalculation.getInputsThatHaveKeyValueForByte("0", 0, inputs);
		assertEquals(expected, actual);
		
		expected.remove(0);
		ArrayList<String> newActual = powerCalculation.getInputsThatHaveKeyValueForByte("1", 1, actual);
		assertEquals(expected, newActual);
	}
	@Test
	void getOxygen_and_CO2() throws Exception {
		powerCalculation = new PowerCalculation();
		ArrayList<String> inputs = powerCalculation.getAllInputs();
		int maxByteSize = inputs.get(0).length();
		for(int index=0; index<maxByteSize; index++) {
			ArrayList<String> binaryList = powerCalculation.getBinaryListForByteNumber(index, inputs);
			String key = String.valueOf(powerCalculation.whichHasMore(binaryList));
			System.out.println("filtering for key [" + key + "] in pos:" + index);
			inputs = powerCalculation.getInputsThatHaveKeyValueForByte(key, index, inputs);
			System.out.println("Remaining inputs:");
			System.out.println(inputs);
			if(inputs.size()==1) { break; }
		}
		if(inputs.size()> 1) { throw new RuntimeException("More than one input still remains.");}
		String oxygenBits = inputs.get(0);
		long oxygen = DecimalUtil.getDecimal(oxygenBits);
		System.out.println("\n");
		
		inputs = powerCalculation.getAllInputs();
		for(int index=0; index<maxByteSize; index++) {
			ArrayList<String> binaryList = powerCalculation.getBinaryListForByteNumber(index, inputs);
			String more = String.valueOf(powerCalculation.whichHasMore(binaryList));
			String key = (more.equals("1")) ? "0" : "1";
			System.out.println("filtering for key [" + key + "] in pos:" + index);
			inputs = powerCalculation.getInputsThatHaveKeyValueForByte(key, index, inputs);
			System.out.println("Remaining inputs:");
			System.out.println(inputs);
			if(inputs.size()==1) { break; }
		}
		if(inputs.size()> 1) { throw new RuntimeException("More than one input still remains.");}
		String co2Bits = inputs.get(0);
		long co2 = DecimalUtil.getDecimal(co2Bits);
		System.out.println("Oxygen:" + oxygenBits);
		System.out.println("Oxygen:" + oxygen);
		System.out.println("CO2:" + co2Bits);
		System.out.println("CO2:" + co2);
		System.out.println("Multiplied: " + (oxygen*co2));
	}
}
