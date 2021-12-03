package day3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class PowerPart1Test {

	PowerCalculation powerCalculation = new PowerCalculation();
	
	@Test
	void new_rates_starts_at_0_0() throws Exception {
		Rates rates = new Rates();
		assertEquals(0, rates.getGamma());
		assertEquals(0, rates.getEpsilon());
	}
	
	@Test
	void whichHasMore_will_be_1_when_more_1s() throws Exception {
		ArrayList<String> bytes = new ArrayList<String>();
		bytes.add("1");
		bytes.add("1");
		bytes.add("0");
		
		int actual = powerCalculation.whichHasMore(bytes);
		assertEquals(1, actual);
	}
	
	@Test
	void whichHasMore_will_be_0_when_more_0s() throws Exception {
		ArrayList<String> bytes = new ArrayList<String>();
		bytes.add("0");
		bytes.add("0");
		bytes.add("1");
		
		int actual = powerCalculation.whichHasMore(bytes);
		assertEquals(0, actual);
	}
	
	@Test
	void getAllInputs_returns_list_of_binary_strings() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		powerCalculation.setFileToUse(new File(fileName.getPath()));
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("001");
		expected.add("010");
		expected.add("010");
		expected.add("111");
		ArrayList<String> actual = powerCalculation.getAllInputs();
		assertEquals(expected, actual);
	}
	
	@Test
	//We're assuming all inputs have same number of bytes
	void getBinaryListForByteNumber_returns_all_bits_for_that_byte_of_all_inputs() throws Exception {
		URL fileName = getClass().getResource("SampleInput.txt");
		powerCalculation.setFileToUse(new File(fileName.getPath()));
		ArrayList<String> inputs = powerCalculation.getAllInputs();
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("0");
		expected.add("0");
		expected.add("0");
		expected.add("1");
		ArrayList<String> actual = powerCalculation.getBinaryListForByteNumber(0, inputs);
		assertEquals(expected, actual);
	}
	
	@Test
	void getGamma_and_epsilon_actual() throws Exception {
		powerCalculation = new PowerCalculation();
		ArrayList<String> inputs = powerCalculation.getAllInputs();
		//Cheat because we know there are 12 bytes in the input file
		ArrayList<String> inputsAtByte0 = powerCalculation.getBinaryListForByteNumber(0, inputs);
		ArrayList<String> inputsAtByte1 = powerCalculation.getBinaryListForByteNumber(1, inputs);
		ArrayList<String> inputsAtByte2 = powerCalculation.getBinaryListForByteNumber(2, inputs);
		ArrayList<String> inputsAtByte3 = powerCalculation.getBinaryListForByteNumber(3, inputs);
		ArrayList<String> inputsAtByte4 = powerCalculation.getBinaryListForByteNumber(4, inputs);
		ArrayList<String> inputsAtByte5 = powerCalculation.getBinaryListForByteNumber(5, inputs);
		ArrayList<String> inputsAtByte6 = powerCalculation.getBinaryListForByteNumber(6, inputs);
		ArrayList<String> inputsAtByte7 = powerCalculation.getBinaryListForByteNumber(7, inputs);
		ArrayList<String> inputsAtByte8 = powerCalculation.getBinaryListForByteNumber(8, inputs);
		ArrayList<String> inputsAtByte9 = powerCalculation.getBinaryListForByteNumber(9, inputs);
		ArrayList<String> inputsAtByte10 = powerCalculation.getBinaryListForByteNumber(10, inputs);
		ArrayList<String> inputsAtByte11 = powerCalculation.getBinaryListForByteNumber(11, inputs);
		
		int gammaByte0 = powerCalculation.whichHasMore(inputsAtByte0);
		int gammaByte1 = powerCalculation.whichHasMore(inputsAtByte1);
		int gammaByte2 = powerCalculation.whichHasMore(inputsAtByte2);
		int gammaByte3 = powerCalculation.whichHasMore(inputsAtByte3);
		int gammaByte4 = powerCalculation.whichHasMore(inputsAtByte4);
		int gammaByte5 = powerCalculation.whichHasMore(inputsAtByte5);
		int gammaByte6 = powerCalculation.whichHasMore(inputsAtByte6);
		int gammaByte7 = powerCalculation.whichHasMore(inputsAtByte7);
		int gammaByte8 = powerCalculation.whichHasMore(inputsAtByte8);
		int gammaByte9 = powerCalculation.whichHasMore(inputsAtByte9);
		int gammaByte10 = powerCalculation.whichHasMore(inputsAtByte10);
		int gammaByte11 = powerCalculation.whichHasMore(inputsAtByte11);
		ArrayList<Integer> gammaBytes = new ArrayList<Integer>();
		gammaBytes.add(gammaByte11);
		gammaBytes.add(gammaByte10); 
		gammaBytes.add(gammaByte9);
		gammaBytes.add(gammaByte8);
		gammaBytes.add(gammaByte7);
		gammaBytes.add(gammaByte6);
		gammaBytes.add(gammaByte5);
		gammaBytes.add(gammaByte4);
		gammaBytes.add(gammaByte3);
		gammaBytes.add(gammaByte2);
		gammaBytes.add(gammaByte1);
		gammaBytes.add(gammaByte0);
		long gammaDecimal = getDecimal(gammaBytes);
		System.out.println("GAMMA");
		System.out.println(gammaBytes);
		
		int epsilon0 = gammaByte0==1 ? 0 : 1;
		int epsilon1 = gammaByte1==1 ? 0 : 1;
		int epsilon2 = gammaByte2==1 ? 0 : 1;
		int epsilon3 = gammaByte3==1 ? 0 : 1;
		int epsilon4 = gammaByte4==1 ? 0 : 1;
		int epsilon5 = gammaByte5==1 ? 0 : 1;
		int epsilon6 = gammaByte6==1 ? 0 : 1;
		int epsilon7 = gammaByte7==1 ? 0 : 1;
		int epsilon8 = gammaByte8==1 ? 0 : 1;
		int epsilon9 = gammaByte9==1 ? 0 : 1;
		int epsilon10 = gammaByte10==1 ? 0 : 1;
		int epsilon11 = gammaByte11==1 ? 0 : 1;
		ArrayList<Integer> epsilonBytes = new ArrayList<Integer>();
		epsilonBytes.add(epsilon11);
		epsilonBytes.add(epsilon10); 
		epsilonBytes.add(epsilon9);
		epsilonBytes.add(epsilon8);
		epsilonBytes.add(epsilon7);
		epsilonBytes.add(epsilon6);
		epsilonBytes.add(epsilon5);
		epsilonBytes.add(epsilon4);
		epsilonBytes.add(epsilon3);
		epsilonBytes.add(epsilon2);
		epsilonBytes.add(epsilon1);
		epsilonBytes.add(epsilon0);
		long epsilonDecimal = getDecimal(epsilonBytes);
		System.out.println("EPSILON");
		System.out.println(epsilonBytes);
		System.out.println("\n");
		
		System.out.println("Gamma: " + gammaDecimal + "\nEpsilon: " + epsilonDecimal + "\n" +
				"multiplied: " + gammaDecimal * epsilonDecimal);
		//double check after known:
		assertEquals(1508, gammaDecimal);
		assertEquals(2587, epsilonDecimal);
	}
	
	private long getDecimal(ArrayList<Integer> bytes) {
		long total = 0;
		for (int i=0; i<bytes.size(); i++) {
			long thisByte = (long) Math.pow(2, i);
			total+=thisByte*bytes.get(i);
		}
		return total;
	}
	
	@Test
	void getDecimalTest() throws Exception {
		ArrayList<Integer> bytes = new ArrayList<Integer>();
		bytes.add(1);
		assertEquals(1, getDecimal(bytes));
		bytes.add(1);
		assertEquals(3, getDecimal(bytes));
		bytes.add(1);
		assertEquals(7, getDecimal(bytes));
		bytes.add(1);
		assertEquals(15, getDecimal(bytes));
	}
	@Test
	void getDecimalTest2() throws Exception {
		ArrayList<Integer> bytes = new ArrayList<Integer>();
		bytes.add(0);
		assertEquals(0, getDecimal(bytes));
		bytes.add(1);
		assertEquals(2, getDecimal(bytes));
		bytes.add(0);
		assertEquals(2, getDecimal(bytes));
		bytes.add(1);
		assertEquals(10, getDecimal(bytes));
	}
}
