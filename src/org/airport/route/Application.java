package org.airport.route;


import java.io.BufferedInputStream;
import java.util.Scanner;
import org.airport.route.model.AirportConnectionsGraph;
import org.airport.route.model.Baggage;
import org.airport.route.optimalalgo.ShortestRoute;
import org.airport.route.service.Baggages;
import org.airport.route.service.Connections;

public class Application {

	public static void main(String[] args) {
	
		AirportConnectionsGraph graph = new AirportConnectionsGraph();
		System.out.println("# Section: Conveyor System");
		System.out.println("please input in the Format of <Node 1> <Node 2> <travel_time> and to exit the section input " + "'SECTION_END'");
		Scanner stdin = new Scanner(new BufferedInputStream(System.in));
	    while (stdin.hasNextLine()) {
        	String str = stdin.nextLine();
        	if(!str.equalsIgnoreCase("SECTION_END")){
        		
        		if(!str.isEmpty())
        		{
        		 String input[] =	str.split(" ");
        		 graph.addEdge(input[0], input[1], Integer.valueOf(input[2]));
        		 //System.out.println("input 1: " + input[0] + " input 2: " + input[1] + " input 3: " + input[2] );
        			}
        	}
        	else
        	{
        		break;
        	}
        	
        }

	    System.out.println("");
		System.out.println("# Section: Departures : ");
		System.out.println("please input departure list in Format: <flight_id> <flight_gate> <destination> <flight_time> and to exit the section input " + "'SECTION_END'");
		Connections flightInfo = new Connections();
		
		while (stdin.hasNextLine()) {
        	String str = stdin.nextLine();
        	if(!str.equalsIgnoreCase("SECTION_END")){
        		
        		if(!str.isEmpty()){
        		 String input[] =	str.split(" ");
        		 flightInfo.addFlight(input[0], input[1], input[2],input[3]);
        		 }
        		}
        	else{
        		break;
        	}
        }

		System.out.println("# Section: Bags : ");
		System.out.println("please input Bag list Format: <bag_number> <entry_point> <flight_id> and to exit the section input " + "'SECTION_END'");
		Baggages bags = new Baggages();
		
		while (stdin.hasNextLine()) {
        	String str = stdin.nextLine();
        	if(!str.equalsIgnoreCase("SECTION_END")){
        		
        		if(!str.isEmpty()){
        		 String input[] =	str.split(" ");
        		 bags.addBag(input[0], input[1], input[2]);
        		 }
        		}
        	else{
        		break;
        	}
        }
	
		ShortestRoute path = new ShortestRoute(graph, flightInfo);
		System.out.println();
		for (Baggage baggage : bags.getBags()) {
		
			try {
				System.out.println(path.getShortestRoute(baggage));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
				
	
	}

}
