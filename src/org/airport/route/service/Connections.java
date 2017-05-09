package org.airport.route.service;

import java.util.HashMap;
import java.util.Map;

import org.airport.route.model.Departures;

/**
 * create departure collection from input
 *
 */
public class Connections {

	private Map<String,Departures> flightList;
	
	public Connections() {
		flightList = new HashMap<>();
	}
	
	/**
	 * add a new depaarture to collection
	 * @param flightNumber
	 * @param terminalname
	 * @param destinationCity
	 * @param departureTime
	 */
	public void addFlight(String flightNumber, String terminalname, String destinationCity, String departureTime)
	{	
		flightList.putIfAbsent(flightNumber, new Departures(flightNumber, terminalname, destinationCity, departureTime));
	}
	
	/**
	 * @param FlightNumber
	 * @return departures info
	 */
	public Departures getFlight(String FlightNumber)
	{
		return flightList.get(FlightNumber);
	}
}
