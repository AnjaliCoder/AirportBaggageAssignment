package org.airport.route.optimalalgo;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.airport.route.model.AirportConnectionsGraph;
import org.airport.route.model.Baggage;
import org.airport.route.model.BaggageTerminal;
import org.airport.route.model.Connection;
import org.airport.route.service.Connections;

/**
 * Dijkastra algo to calculate shortest path
 */

public class ShortestRoute {
		
	private AirportConnectionsGraph graph ;
	private Connections connections;
	private Baggage baggage;
	
	//stores shortest distance from source terminal to every other terminal 
    private Map<BaggageTerminal,Integer> shortestTravelTime;
    
    //stores parent of every terminal in shortest travel time
    private Map<BaggageTerminal, BaggageTerminal> parent ;
	
	public ShortestRoute(AirportConnectionsGraph graph, Connections connections) {
		this.graph = graph;
		this.connections = connections;
	}

    private BaggageTerminal getConnectingTerminal(BaggageTerminal v, Connection e){
        return e.getSourceTerminal().equals(v) ? e.getDestinationTerminal() : e.getSourceTerminal();
    }

	private void calculateShortestRoute(String strSourceTerminal){

	  	//stores shortest distance from source terminal to every other terminal in graph
		shortestTravelTime = new HashMap<>();

        //stores parent of every terminal in shortest travel time
        parent = new HashMap<>();
        
        //heap + map data structure
        MinimumHeap<BaggageTerminal> minHeap = new MinimumHeap<>();

        //initialize all terminal with infinite distance from source terminal
        for(BaggageTerminal baggageTerminal : graph.getTerminals()){
            minHeap.add(Integer.MAX_VALUE, baggageTerminal);
        }
        
        BaggageTerminal sourceTerminal = graph.getTerminal(strSourceTerminal);
        
        //set distance of source terminal to itself 0
        minHeap.decrease(sourceTerminal, 0);

        //put it in map
        shortestTravelTime.put(sourceTerminal, 0);

        //source terminal parent is null
        parent.put(sourceTerminal, null);

        //iterate till heap is not empty
        while(!minHeap.empty()){
            //get the min value from heap node which has vertex and distance of that vertex from source vertex.
            MinimumHeap<BaggageTerminal>.Node heapNode = minHeap.extractMinNode();
            BaggageTerminal currentTerminal = heapNode.key;

            //update shortest distance of current vertex from source vertex
            shortestTravelTime.put(currentTerminal, heapNode.weight);

            //iterate through all connections of current terminal
            for(Connection connection : currentTerminal.getConnections()){

                //get the adjacent terminal
                BaggageTerminal adjacent = getConnectingTerminal(currentTerminal, connection);

                //if heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex
                if(!minHeap.containsData(adjacent)){
                    continue;
                }

                //add distance of current vertex to edge weight to get distance of adjacent vertex from source vertex
                //when it goes through current vertex
                int newDistance = shortestTravelTime.get(currentTerminal) + connection.getWeight();

                //see if this above calculated distance is less than current distance stored for adjacent vertex from source vertex
                if(minHeap.getWeight(adjacent) > newDistance) {
                    minHeap.decrease(adjacent, newDistance);
                    parent.put(adjacent, currentTerminal);
                }
            }
        }
        return;
    }

	public String getShortestRoute (Baggage baggage) throws Exception
	{
		String SourceTerminal = baggage.getTerminalName();
		if(graph.getTerminal(SourceTerminal) == null)
		{
			throw new Exception("The terminal " + SourceTerminal + " is not present in the airport" );
		}
		
		if(connections.getFlight(baggage.getFlghtNumber()) == null && (! baggage.getFlghtNumber().equals("ARRIVAL")))
		{
			throw new Exception("The flight " + baggage.getFlghtNumber() + " is not present in the airport" );
		}
		String destinationTerminal = "BaggageClaim";
		
		if(!baggage.getFlghtNumber().equals("ARRIVAL"))
			destinationTerminal = connections.getFlight(baggage.getFlghtNumber()).getTerminalname();
		
		String shortestRoute ;
	    Stack<String> Path = new Stack<>();

		this.calculateShortestRoute(SourceTerminal);

		String lookupTerminal = destinationTerminal;
	    Path.push(lookupTerminal);
	    while(!lookupTerminal.equals(SourceTerminal))
	    {
	    	
	    	BaggageTerminal parentTerminal = parent.get(graph.getTerminal(lookupTerminal));
	    	lookupTerminal = parentTerminal.getName();
	    	Path.push(lookupTerminal);
	    }
	    
	    String route = baggage.getBagId();
	    
	    while(!Path.empty())
	    {
	    	route = route +  " " + Path.pop();
	    }
	    
	    route =  route +  " : " + shortestTravelTime.get(graph.getTerminal(destinationTerminal)).toString();
		return route;
	}

}
