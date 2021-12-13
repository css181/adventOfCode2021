package day12;

import java.util.HashSet;
import java.util.Set;

public class Cave {

	private String name;
	private Set<Cave> connections;
	
	public Cave(String name) {
		this.name = name;
		this.connections = new HashSet<Cave>();
	}

	public String getName() {
		return name;
	}

	public Set<Cave> getConnections() {
		return connections;
	}
	public void wipeConnections() {
		this.connections = new HashSet<Cave>();
	}

	public void addConnection(Cave cave) {
		connections.add(cave);
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Cave)) { return false; }
        Cave other = (Cave) obj;
        
        if(!this.name.equals(other.name)) { return false; }
        if(this.connections.size()!=(other.connections.size())) { return false; }
        boolean foundEquivalant = false;
        for (Cave cave : connections) {
        	for (Cave otherCave : other.connections) {				
        		if(otherCave.getName().equals(cave.getName())) {
        			foundEquivalant = true;
        			break;
        		}
			}
        	if(!foundEquivalant) {
        		return false;
        	}
		}
        
        return true;
    }

	@Override
    public String toString() {
		String print = "Name: " + name + ".  Connections: [";
		for (Cave cave : connections) {
			print += cave.getName() + ",";
		}
		int subtract = connections.size()>0 ? 1 : 0;
		print = print.substring(0, print.length() - subtract) + "]";
		return print;
    }
}
