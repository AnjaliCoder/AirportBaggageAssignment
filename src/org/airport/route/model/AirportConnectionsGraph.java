package org.airport.route.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class create a connection graph from user inputs
 * this will be used as input for shortest path algo
 */
/**
 * @author Vibhu
 *
 */
public class AirportConnectionsGraph {
	private List<Connection> connections = new ArrayList<>();
	private Map<String,BaggageTerminal> baggageTerminals = new HashMap<>();

	/**
	 * add the edges to the graph
	 */
	public void addEdge(String strSourceTerminal, String strDestinationTerminal , int travelTime)
	{
		BaggageTerminal sourceTerminal = null;
		if(baggageTerminals.containsKey(strSourceTerminal))
		{
			sourceTerminal = baggageTerminals.get(strSourceTerminal);
		}
		else
		{
			sourceTerminal = new BaggageTerminal(strSourceTerminal);
			baggageTerminals.put(strSourceTerminal, sourceTerminal);
		}
		
		BaggageTerminal destinationTerminal = null;
		if(baggageTerminals.containsKey(strDestinationTerminal))
		{
			destinationTerminal = baggageTerminals.get(strDestinationTerminal);
		}
		else
		{
			destinationTerminal = new BaggageTerminal(strDestinationTerminal);
			baggageTerminals.put(strDestinationTerminal, destinationTerminal);
		}
		
		Connection belt = new Connection(sourceTerminal, destinationTerminal, travelTime);
		connections.add(belt);
		sourceTerminal.addAdjacentTeminal(destinationTerminal, belt);
		destinationTerminal.addAdjacentTeminal(sourceTerminal, belt);
	}
	
	/**
	 * @param strTerminalName
	 * @return Terminal Object
	 */
	public BaggageTerminal getTerminal(String strTerminalName)
	{
		return baggageTerminals.get(strTerminalName);
	}

	/**
	 * @return list of all connections
	 */
	public List<Connection> getConnections() {
		return connections;
	}

	/**
	 * @return the list of all terminals (vertexs)
	 */
	public List<BaggageTerminal> getTerminals() {
		return new ArrayList<BaggageTerminal>(baggageTerminals.values());
	}
	

	
	
}
