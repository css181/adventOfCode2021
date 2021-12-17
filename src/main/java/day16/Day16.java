package day16;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import day1.FileUtility;
import day3.DecimalUtil;

public class Day16 {
	
	private static File file;
	private String hexInput;
	
	public Day16() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}

	protected void getInputs() {
		hexInput = FileUtility.convertFileToString(file);
	}
	protected void setFileToUse(File file) {
		Day16.file = file;
	}


	public FourBit getBinaryForHex(String hex) {
		switch (hex) {
		case "0":
			return new FourBit(0, 0, 0, 0);
		case "1":
			return new FourBit(0, 0, 0, 1);
		case "2":
			return new FourBit(0, 0, 1, 0);
		case "3":
			return new FourBit(0, 0, 1, 1);
		case "4":
			return new FourBit(0, 1, 0, 0);
		case "5":
			return new FourBit(0, 1, 0, 1);
		case "6":
			return new FourBit(0, 1, 1, 0);
		case "7":
			return new FourBit(0, 1, 1, 1);
		case "8":
			return new FourBit(1, 0, 0, 0);
		case "9":
			return new FourBit(1, 0, 0, 1);
		case "A":
			return new FourBit(1, 0, 1, 0);
		case "B":
			return new FourBit(1, 0, 1, 1);
		case "C":
			return new FourBit(1, 1, 0, 0);
		case "D":
			return new FourBit(1, 1, 0, 1);
		case "E":
			return new FourBit(1, 1, 1, 0);
		case "F":
			return new FourBit(1, 1, 1, 1);
		default:
			throw new RuntimeException("Can't get bits for invalid hex: " + hex);
		}
	}
	
	public String getHexInput() {
		return hexInput;
	}

	public FourBit[] convertHexToFourBitArray(String hex) {
		ArrayList<FourBit> fourBitList = new ArrayList<FourBit>();
		for(int pos=0; pos<hex.length(); pos++) {
			fourBitList.add(getBinaryForHex(String.valueOf(hex.charAt(pos))));
		}
		return fourBitList.toArray(new FourBit[0]);
	}

	public int[] getBinaryArrayFromFourBitArray(FourBit[] fourBitArray) {
		ArrayList<Integer> binaryList = new ArrayList<Integer>();
		for (FourBit fourBit : fourBitArray) {
			binaryList.add(fourBit.getFirst());
			binaryList.add(fourBit.getSecond());
			binaryList.add(fourBit.getThird());
			binaryList.add(fourBit.getFourth());
		}
		
		return binaryList.stream().mapToInt(Integer::intValue).toArray();
	}

	public long getSumOfAllVersionsIncludingNestedSubPackets(SubPacket subPacket) {
		long total = DecimalUtil.getDecimal(subPacket.getVersion());
		if(subPacket instanceof LiteralSubPacket) {
			//Nothing else to do
		} else {
			for (SubPacket nestedPacket : ((OperatorSubPacket)subPacket).getSubPackets()) {
				total+=getSumOfAllVersionsIncludingNestedSubPackets(nestedPacket);
			}
		}
		return total;
	}

	public long processPacket(SubPacket packet) {
		long processedValue = -1;
		if(packet.getTypeIdValue()==4) {
			//Return literal Value
			return ((LiteralSubPacket) packet).getDecimalValue();
		}
		SubPacket[] subPackets = ((OperatorSubPacket)packet).getSubPackets();
		switch (packet.getTypeIdValue()) {
		case 0:
			processedValue = 0;
			for (SubPacket subPacket : subPackets) {
				processedValue+=processPacket(subPacket);
			}
			break;
		case 1:
			processedValue = 1;
			for (SubPacket subPacket : subPackets) {
				processedValue*=processPacket(subPacket);
			}
			break;
		case 2:
			processedValue = Integer.MAX_VALUE;
			for (SubPacket subPacket : subPackets) {
				long curProcessValue = processPacket(subPacket);
				if(curProcessValue<processedValue) {
					processedValue = curProcessValue;
				}
			}
			break;
		case 3:
			processedValue = Integer.MIN_VALUE;
			for (SubPacket subPacket : subPackets) {
				long curProcessValue = processPacket(subPacket);
				if(curProcessValue>processedValue) {
					processedValue = curProcessValue;
				}
			}
			break;
		case 5:
			if(subPackets.length!=2) {
				throw new RuntimeException("Can't process greater than, cause there are [" + subPackets.length + "] subPackets.");
			}
			if(processPacket(subPackets[0]) > processPacket(subPackets[1])) {
				processedValue = 1;
			} else {
				processedValue = 0;
			}
			break;
		case 6:
			if(subPackets.length!=2) {
				throw new RuntimeException("Can't process less than, cause there are [" + subPackets.length + "] subPackets.");
			}
			if(processPacket(subPackets[0]) < processPacket(subPackets[1])) {
				processedValue = 1;
			} else {
				processedValue = 0;
			}
			break;
		case 7:
			if(subPackets.length!=2) {
				throw new RuntimeException("Can't process equal, cause there are [" + subPackets.length + "] subPackets.");
			}
			if(processPacket(subPackets[0]) == processPacket(subPackets[1])) {
				processedValue = 1;
			} else {
				processedValue = 0;
			}
			break;
		default:
			throw new RuntimeException("Can't process packet.  Unknown packet TypeID: " + packet.getTypeIdValue());
		}
		return processedValue;
	}
}
