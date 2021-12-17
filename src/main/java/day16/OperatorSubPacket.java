package day16;

import java.util.ArrayList;
import java.util.Arrays;

import day3.DecimalUtil;

public class OperatorSubPacket extends SubPacket{

	protected int lengthTypeId;
	protected int[] lengthInBits_or_numOfSubPackets;
	protected SubPacket[] subPackets;
	
	public OperatorSubPacket() {
	}

	public OperatorSubPacket(int[] version, int[] idType, int lengthTypeId, int[] lengthInBits_or_numOfSubPackets, SubPacket[] subPackets) {
		super(version, idType);
		this.lengthTypeId = lengthTypeId;
		this.lengthInBits_or_numOfSubPackets = lengthInBits_or_numOfSubPackets;
		this.subPackets = subPackets;
	}

	public OperatorSubPacket(int[] binaryArray) {
		version = new int[] {binaryArray[0],binaryArray[1],binaryArray[2]};
		typeId = new int[] {binaryArray[3],binaryArray[4],binaryArray[5]};
		lengthTypeId = binaryArray[6];
		if(lengthTypeId==0) {			
			addSubPacketsByBytes(binaryArray);
		} else {
			addSubPacketsByNumber(binaryArray);
		}
	}

	private void addSubPacketsByBytes(int[] binaryArray) {
		setLengthOfSubPacketsInBits(binaryArray);
//		System.out.println(printArray(lengthInBits_or_numOfSubPackets));
		long lengthInBitsInDecimal = DecimalUtil.getDecimal(lengthInBits_or_numOfSubPackets);
//		System.out.println("Len in bits in decimal: " + lengthInBitsInDecimal);
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int pos=22; pos<22+lengthInBitsInDecimal; pos++) {
			list.add(binaryArray[pos]);
		}
		int[] allSubPacketBits = list.stream().mapToInt(Integer::intValue).toArray();
		int stopPos;
		ArrayList<SubPacket> subPacketList = new ArrayList<SubPacket>();
		do {
			if(!(allSubPacketBits[3]==1 && allSubPacketBits[4]==0 && allSubPacketBits[5]==0)) {
				//add OperatorSubPacket if TypeId!=4
				subPacketList.add(new OperatorSubPacket(allSubPacketBits));
			} else {
				//add LiteralSubPacket(s) if TypeId==4
				subPacketList.add(new LiteralSubPacket(allSubPacketBits));
			}
			stopPos = subPacketList.get(subPacketList.size()-1).getLastPositionProcessed();
//			System.out.println("OperatorSubPacket stop pos: " + stopPos + ", of total: " + allSubPacketBits.length);
			ArrayList<Integer> newSubPacketBinaryList = new ArrayList<Integer>();
			for(int pos=stopPos; pos<allSubPacketBits.length; pos++) {
				newSubPacketBinaryList.add(allSubPacketBits[pos]);
			}
			allSubPacketBits = newSubPacketBinaryList.stream().mapToInt(Integer::intValue).toArray();
		}while(allSubPacketBits.length>0);
		lastPosProcessed = (int) (22+lengthInBitsInDecimal);
		subPackets = subPacketList.toArray(new SubPacket[0]);
	}

	private void addSubPacketsByNumber(int[] binaryArray) {
		setNumOfSubPackets(binaryArray);
//		System.out.println(printArray(lengthInBits_or_numOfSubPackets));
		long numOfSubPacketsInDecimal = DecimalUtil.getDecimal(lengthInBits_or_numOfSubPackets);
//		System.out.println("Num of sub packets in decimal: " + numOfSubPacketsInDecimal);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int pos=18; pos<binaryArray.length; pos++) {
			list.add(binaryArray[pos]);
		}
		int[] allSubPacketBits = list.stream().mapToInt(Integer::intValue).toArray();
		int stopPos;
		ArrayList<SubPacket> subPacketList = new ArrayList<SubPacket>();
		do {
			if(!(allSubPacketBits[3]==1 && allSubPacketBits[4]==0 && allSubPacketBits[5]==0)) {
				//add OperatorSubPacket if TypeId!=4
					subPacketList.add(new OperatorSubPacket(allSubPacketBits));
			} else {
				//add LiteralSubPacket(s) if TypeId==4
				subPacketList.add(new LiteralSubPacket(allSubPacketBits));
			}
			stopPos = subPacketList.get(subPacketList.size()-1).getLastPositionProcessed();
			//				System.out.println("Operator stop pos: " + stopPos + ", of total: " + allSubPacketBits.length);
			ArrayList<Integer> newSubPacketBinaryList = new ArrayList<Integer>();
			for(int pos=stopPos; pos<allSubPacketBits.length; pos++) {
				newSubPacketBinaryList.add(allSubPacketBits[pos]);
			}
			allSubPacketBits = newSubPacketBinaryList.stream().mapToInt(Integer::intValue).toArray();
		}while(subPacketList.size()<numOfSubPacketsInDecimal);
		lastPosProcessed = binaryArray.length-allSubPacketBits.length;
		subPackets = subPacketList.toArray(new SubPacket[0]);
	}

	private void setLengthOfSubPacketsInBits(int[] binaryArray) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int pos=7; pos<(7+15); pos++) {
			list.add(binaryArray[pos]);
		}
		lengthInBits_or_numOfSubPackets = list.stream().mapToInt(Integer::intValue).toArray();
	}
	private void setNumOfSubPackets(int[] binaryArray) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int pos=7; pos<(7+11); pos++) {
			list.add(binaryArray[pos]);
		}
		lengthInBits_or_numOfSubPackets = list.stream().mapToInt(Integer::intValue).toArray();
	}

	public int getLengthTypeId() {
		return lengthTypeId;
	}
	public int[] getLengthInBitsOrNumOfSubPackets() {
		return lengthInBits_or_numOfSubPackets;
	}
	public SubPacket[] getSubPackets() {
		return subPackets;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof OperatorSubPacket)) { return false; }
        OperatorSubPacket other = (OperatorSubPacket) obj;
        
        if(!Arrays.equals(this.version, other.version)) { return false; }
        if(!Arrays.equals(this.typeId, other.typeId)) { return false; }
        if(this.lengthTypeId!=(other.lengthTypeId)) { return false; }
        if(!Arrays.equals(this.lengthInBits_or_numOfSubPackets, other.lengthInBits_or_numOfSubPackets)) { return false; }
        if(!Arrays.deepEquals(this.subPackets, other.subPackets)) { return false; }
        
        return true;
    }

	@Override
    public String toString() {
		String print = "[version:" + printArray(version) + ", idType: " + printArray(typeId) + 
				", lengthTypeId: " + lengthTypeId + ", lengthInBitsOrNumOfSubPackets: " + 
				printArray(lengthInBits_or_numOfSubPackets) + ", SubPacket List size: ";
		if(subPackets!=null)
			print+=String.valueOf(subPackets.length);
		else
			print+="0";
		print+="]";
		return print;
    }

}
