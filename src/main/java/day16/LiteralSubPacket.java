package day16;

import java.util.ArrayList;
import java.util.Arrays;

import day3.DecimalUtil;

public class LiteralSubPacket extends SubPacket{

	private FourBit[] literalValue;
	
	public LiteralSubPacket() {
	}

	public LiteralSubPacket(int[] version, int[] idType, FourBit[] literalValue) {
		super(version, idType);
		this.literalValue = literalValue;
	}
	
	public LiteralSubPacket(int[] binaryArray) {
		version = new int[] {binaryArray[0],binaryArray[1],binaryArray[2]};
		typeId = new int[] {binaryArray[3],binaryArray[4],binaryArray[5]};
		if(!Arrays.equals(typeId, new int[] {1,0,0})) {
			throw new RuntimeException("Literal SubPackets can only have typeId=4.  This typeId of " + printArray(typeId) + " is invalid.");
		}
		ArrayList<FourBit> list = new ArrayList<FourBit>();
		for(int pos=6; pos<binaryArray.length; pos+=5) {
			list.add(new FourBit(binaryArray[pos+1],binaryArray[pos+2],
					binaryArray[pos+3],binaryArray[pos+4]));
			if(binaryArray[pos]==0) {
				lastPosProcessed = pos+5;
				break;
			}
		}
		literalValue = list.toArray(new FourBit[0]);
	}
	
	public int getLastPositionProcessed() {
		return lastPosProcessed;
	}

	public FourBit[] getLiteralValue() {
		return literalValue;
	}

	public long getDecimalValue() {
		ArrayList<Integer> binaryList = new ArrayList<Integer>();
		for (FourBit fourBit : literalValue) {
			binaryList.add(fourBit.getFirst());
			binaryList.add(fourBit.getSecond());
			binaryList.add(fourBit.getThird());
			binaryList.add(fourBit.getFourth());
		}
		int[] binaryArray = binaryList.stream().mapToInt(Integer::intValue).toArray();
		return DecimalUtil.getDecimal(binaryArray);
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof LiteralSubPacket)) { return false; }
        LiteralSubPacket other = (LiteralSubPacket) obj;
        
        if(!Arrays.equals(this.version, other.version)) { return false; }
        if(!Arrays.equals(this.typeId, other.typeId)) { return false; }
        if(!Arrays.deepEquals(this.literalValue, other.literalValue)) { return false; }
        
        return true;
    }

	@Override
    public String toString() {
		String print = "[version:" + printArray(version) + ", idType: " + printArray(typeId) 
		+ ", literalValue: " + printArray(literalValue) + "]";
		return print;
    }

}
