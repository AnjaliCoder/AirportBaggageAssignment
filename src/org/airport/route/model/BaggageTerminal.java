package org.airport.route.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the model class for terminals this will contain the information
 * about terminal name, connecting terminals and there connections
 */

/**
 * @author Vibhu
 *
 */
public class BaggageTerminal {
 
	private final String name;
	private List<BaggageTerminal> adjacentTerminals = new ArrayList<>();
	private List<Connection> connections = new ArrayList<>();

	public BaggageTerminal(String name)
	{
		this.name= name;
	}
	public String getName() {
		return name;
	}
	
	/**
	 * @return
	 */
	public List<BaggageTerminal> getAdjacentTerminals() {
		return adjacentTerminals;
	}
	
	/**
	 * @return connections  
	 */
	public List<Connection> getConnections() {
		return connections;
	}
	
	/**
	 * add near by terminal info with travel time info

	 */
	public void addAdjacentTeminal(BaggageTerminal t, Connection c)
	{
		adjacentTerminals.add(t);
		connections.add(c);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaggageTerminal other = (BaggageTerminal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Terminal [name=" + name + "]";
	}


}
