package day16;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import day3.DecimalUtil;


public class Day16Part1Test {

	Day16 day16;
	
	@Test
	void can_map_hex_to_binary() throws Exception {
		day16 = new Day16();
		
		assertEquals(new FourBit(0, 0, 0, 0), day16.getBinaryForHex("0"));
		assertEquals(new FourBit(0, 0, 0, 1), day16.getBinaryForHex("1"));
		assertEquals(new FourBit(0, 0, 1, 0), day16.getBinaryForHex("2"));
		assertEquals(new FourBit(0, 0, 1, 1), day16.getBinaryForHex("3"));
		assertEquals(new FourBit(0, 1, 0, 0), day16.getBinaryForHex("4"));
		assertEquals(new FourBit(0, 1, 0, 1), day16.getBinaryForHex("5"));
		assertEquals(new FourBit(0, 1, 1, 0), day16.getBinaryForHex("6"));
		assertEquals(new FourBit(0, 1, 1, 1), day16.getBinaryForHex("7"));
		assertEquals(new FourBit(1, 0, 0, 0), day16.getBinaryForHex("8"));
		assertEquals(new FourBit(1, 0, 0, 1), day16.getBinaryForHex("9"));
		assertEquals(new FourBit(1, 0, 1, 0), day16.getBinaryForHex("A"));
		assertEquals(new FourBit(1, 0, 1, 1), day16.getBinaryForHex("B"));
		assertEquals(new FourBit(1, 1, 0, 0), day16.getBinaryForHex("C"));
		assertEquals(new FourBit(1, 1, 0, 1), day16.getBinaryForHex("D"));
		assertEquals(new FourBit(1, 1, 1, 0), day16.getBinaryForHex("E"));
		assertEquals(new FourBit(1, 1, 1, 1), day16.getBinaryForHex("F"));
	}
	
	@Test
	void can_convert_Hex_string_to_FourBit_array_of_binary() throws Exception {
		day16 = new Day16();
		FourBit[] expected = new FourBit[] {new FourBit(1,1,0,1),new FourBit(0,0,1,0),
				new FourBit(1,1,1,1),new FourBit(1,1,1,0),new FourBit(0,0,1,0),
				new FourBit(1,0,0,0)};
		
		FourBit[] actual = day16.convertHexToFourBitArray("D2FE28");
		assertTrue(Arrays.deepEquals(expected, actual));
	}
	
	@Test
	void can_convert_FourBit_array_to_int_array_of_binary() throws Exception {
		day16 = new Day16();
		int[] expected = new int[] {1,1,0,1,0,0,1,0,1,1,1,1,1,1,1,0,0,0,1,0,1,0,0,0};
		FourBit[] fourBitArray = day16.convertHexToFourBitArray("D2FE28");
		
		int[] actual = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		assertTrue(Arrays.equals(expected, actual));
	}
	
	@Test
	void can_convert_binary_array_with_typeId_4_to_LiteralSubPacket() throws Exception {
		day16 = new Day16();
		FourBit[] fourBitArray = day16.convertHexToFourBitArray("D2FE28");
		int[] binaryArray = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		int[] expectedVersion = new int[] {1, 1, 0};
		int[] expectedTypeID = new int[] {1,0,0};
		FourBit[] expectedLiteralValue = new FourBit[] {new FourBit(0,1,1,1), 
				new FourBit(1,1,1,0), new FourBit(0, 1, 0, 1)};
		LiteralSubPacket expected = new LiteralSubPacket(expectedVersion, expectedTypeID, expectedLiteralValue);
		
		LiteralSubPacket actual = new LiteralSubPacket(binaryArray);
		assertEquals(expected, actual);
		assertTrue(Arrays.equals(expectedVersion, actual.version));
		assertTrue(Arrays.equals(expectedTypeID, actual.typeId));
		assertTrue(Arrays.deepEquals(expectedLiteralValue, actual.getLiteralValue()));
	}

	@Test
	void binary_array_with_typeId_not_4_will_throw_exception_if_converted_to_LiteralSubPacket() throws Exception {
		day16 = new Day16();
		FourBit[] fourBitArray = day16.convertHexToFourBitArray("E2FE28");
		int[] binaryArray = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		Assertions.assertThrows(RuntimeException.class, () -> new LiteralSubPacket(binaryArray));
	}
	
	@Test
	void can_convert_binary_array_with_typeId_6_and_lengthTypeId_of_0_to_OperatorSubPacket() throws Exception {
		day16 = new Day16();
		FourBit[] fourBitArray = day16.convertHexToFourBitArray("38006F45291200");
		int[] binaryArray = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		int[] expectedVersion = new int[] {0,0,1};
		int[] expectedTypeID = new int[] {1,1,0};
		int expectedLengthTypeId = 0;
		int[] expectedLengthInBits = new int[] {0,0,0,0,0,0,0,0,0,0,1,1,0,1,1};
		SubPacket[] expectedSubPackets = new SubPacket[] {
				new LiteralSubPacket(new int[] {1,1,0,1,0,0,0,1,0,1,0}),
				new LiteralSubPacket(new int[] {0,1,0,1,0,0,1,0,0,0,1,0,0,1,0,0})
		};
		OperatorSubPacket expected = new OperatorSubPacket(expectedVersion, 
				expectedTypeID, expectedLengthTypeId, expectedLengthInBits, expectedSubPackets);
		
		OperatorSubPacket actual = new OperatorSubPacket(binaryArray);
		assertEquals(expected, actual);
		assertTrue(Arrays.equals(expectedVersion, actual.version));
		assertTrue(Arrays.equals(expectedTypeID, actual.typeId));
		assertEquals(expectedLengthTypeId, actual.getLengthTypeId());
		assertTrue(Arrays.deepEquals(expectedSubPackets, actual.getSubPackets()));
	}
	
	@Test
	void can_convert_binary_array_with_typeId_6_and_lengthTypeId_of_1_to_OperatorSubPacket() throws Exception {
		day16 = new Day16();
		FourBit[] fourBitArray = day16.convertHexToFourBitArray("EE00D40C823060");
		int[] binaryArray = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		int[] expectedVersion = new int[] {1,1,1};
		int[] expectedTypeID = new int[] {0,1,1};
		int expectedLengthTypeId = 1;
		int[] expectedNumOfSubPackets = new int[] {0,0,0,0,0,0,0,0,0,1,1};
		SubPacket[] expectedSubPackets = new SubPacket[] {
				new LiteralSubPacket(new int[] {0,1,0,1,0,0,0,0,0,0,1}),
				new LiteralSubPacket(new int[] {1,0,0,1,0,0,0,0,0,1,0}),
				new LiteralSubPacket(new int[] {0,0,1,1,0,0,0,0,0,1,1})
		};
		OperatorSubPacket expected = new OperatorSubPacket(expectedVersion, 
				expectedTypeID, expectedLengthTypeId, expectedNumOfSubPackets, expectedSubPackets);
		
		OperatorSubPacket actual = new OperatorSubPacket(binaryArray);
		assertEquals(expected, actual);
		assertTrue(Arrays.equals(expectedVersion, actual.version));
		assertTrue(Arrays.equals(expectedTypeID, actual.typeId));
		assertEquals(expectedLengthTypeId, actual.getLengthTypeId());
		assertTrue(Arrays.deepEquals(expectedSubPackets, actual.getSubPackets()));
	}
	
	@Test
	void operatorSubPackets_may_also_contain_other_operatorSubPackets_in_their_subPacket_list() throws Exception {
		day16 = new Day16();
		int[] binaryArray = new int[] {1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,1};
		
		
		int[] expectedVersion = new int[] {1,1,1};
		int[] expectedTypeID = new int[] {0,1,1};
		int expectedLengthTypeId = 1;
		int[] expectedNumOfSubPackets = new int[] {0,0,0,0,0,0,0,0,0,0,1};
		SubPacket[] expectedSubPackets = new SubPacket[] {
				new OperatorSubPacket(new int[] {1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,1}),
		};
		SubPacket[] expectedSecondLevelSubPackets = new SubPacket[] {
				new LiteralSubPacket(new int[] {0,1,0,1,0,0,0,0,0,0,1}),
		};
		OperatorSubPacket expected = new OperatorSubPacket(expectedVersion, 
				expectedTypeID, expectedLengthTypeId, expectedNumOfSubPackets, expectedSubPackets);
		
		OperatorSubPacket actual = new OperatorSubPacket(binaryArray);
		assertEquals(expected, actual);
		assertTrue(Arrays.equals(expectedVersion, actual.version));
		assertTrue(Arrays.equals(expectedTypeID, actual.typeId));
		assertEquals(expectedLengthTypeId, actual.getLengthTypeId());
		assertTrue(Arrays.deepEquals(expectedSubPackets, actual.getSubPackets()));
		assertEquals(expectedSecondLevelSubPackets[0], ((OperatorSubPacket)((OperatorSubPacket)actual).getSubPackets()[0]).getSubPackets()[0]);
	}
	
	@Test
	void operator_containing_operator_containing_operator_containing_literal() throws Exception {
		day16 = new Day16();
		FourBit[] fourBitArray = day16.convertHexToFourBitArray("8A004A801A8002F478");
		int[] binaryArray = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		
		OperatorSubPacket outerMostPacket = new OperatorSubPacket(binaryArray);
		assertEquals(4, DecimalUtil.getDecimal(outerMostPacket.getVersion()));
		OperatorSubPacket firstInner = (OperatorSubPacket) outerMostPacket.getSubPackets()[0];
		assertEquals(1, DecimalUtil.getDecimal(firstInner.getVersion()));
		OperatorSubPacket secondInner = (OperatorSubPacket) firstInner.getSubPackets()[0];
		assertEquals(5, DecimalUtil.getDecimal(secondInner.getVersion()));
		LiteralSubPacket thridInner = (LiteralSubPacket) secondInner.getSubPackets()[0];
		assertEquals(6, DecimalUtil.getDecimal(thridInner.getVersion()));
		
		assertEquals(16, day16.getSumOfAllVersionsIncludingNestedSubPackets(outerMostPacket));
	}
	
	@Test
	void operator_containing_2_subPackets_each_containing_2_literals() throws Exception {
		day16 = new Day16();
		FourBit[] fourBitArray = day16.convertHexToFourBitArray("620080001611562C8802118E34");
		int[] binaryArray = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		
		OperatorSubPacket outerMostPacket = new OperatorSubPacket(binaryArray);
		
		assertEquals(12, day16.getSumOfAllVersionsIncludingNestedSubPackets(outerMostPacket));
	}
	
	@Test
	void operator_containing_2_subPackets_withTypeId_1_each_containing_2_literals() throws Exception {
		day16 = new Day16();
		FourBit[] fourBitArray = day16.convertHexToFourBitArray("C0015000016115A2E0802F182340");
		int[] binaryArray = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		
		OperatorSubPacket outerMostPacket = new OperatorSubPacket(binaryArray);
		
		assertEquals(23, day16.getSumOfAllVersionsIncludingNestedSubPackets(outerMostPacket));
	}
	
	@Test
	void operator_containing_operator_containing_operator_containing_5_literals() throws Exception {
		day16 = new Day16();
		FourBit[] fourBitArray = day16.convertHexToFourBitArray("A0016C880162017C3686B18A3D4780");
		int[] binaryArray = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		
		OperatorSubPacket outerMostPacket = new OperatorSubPacket(binaryArray);
		OperatorSubPacket firstInner = (OperatorSubPacket) outerMostPacket.getSubPackets()[0];
		OperatorSubPacket secondInner = (OperatorSubPacket) firstInner.getSubPackets()[0];
		assertEquals(5, secondInner.getSubPackets().length);
		
		assertEquals(31, day16.getSumOfAllVersionsIncludingNestedSubPackets(outerMostPacket));
	}
	
	@Test
	void part1_full_Test() throws Exception {
		day16 = new Day16();
		day16.getInputs();
		FourBit[] fourBitArray = day16.convertHexToFourBitArray(day16.getHexInput());
		int[] binaryArray = day16.getBinaryArrayFromFourBitArray(fourBitArray);
		
		OperatorSubPacket outerMostPacket = new OperatorSubPacket(binaryArray);
		System.out.println("Total Version sum: " + day16.getSumOfAllVersionsIncludingNestedSubPackets(outerMostPacket));
	}
}
