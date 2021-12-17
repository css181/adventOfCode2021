package day16;

import java.util.Arrays;

import day3.DecimalUtil;

public abstract class SubPacket {

	protected int[] version;
	protected int[] typeId;
	protected int lastPosProcessed = -1;
	
	public SubPacket() {
	}

	public SubPacket(int[] version, int[] typeId) {
		super();
		this.version = version;
		this.typeId = typeId;
	}
	
	public int[] getVersion() {
		return version;
	}
	public int[] getTypeId() {
		return typeId;
	}
	public int getTypeIdValue() {
		return (int)DecimalUtil.getDecimal(typeId);
	}
	public int getLastPositionProcessed() {
		return lastPosProcessed;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof SubPacket)) { return false; }
        SubPacket other = (SubPacket) obj;
        
        if(!Arrays.equals(this.version, other.version)) { return false; }
        if(!Arrays.equals(this.typeId, other.typeId)) { return false; }
        
        return true;
    }

	@Override
    public String toString() {
		String print = "[version:" + printArray(version) + ", idType: " + printArray(typeId) + "]";
		return print;
    }
	
	protected String printArray(int[] array) {
		if(array==null || array.length==0)
			return "{}";
		
		String print = "{";
		for (int elem : array) {
			print+=elem + ",";
		}
		return print.substring(0, print.length()-1) + "}";
	}
	
	protected String printArray(FourBit[] array) {
		if(array==null || array.length==0)
			return "{}";
		
		String print = "{";
		for (FourBit elem : array) {
			print+=elem + ",";
		}
		return print.substring(0, print.length()-1) + "}";
	}
}
