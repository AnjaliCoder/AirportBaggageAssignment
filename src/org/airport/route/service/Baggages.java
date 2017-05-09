package org.airport.route.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.airport.route.model.Baggage;

/**
 * bag collection class to hold all the input bags
 *
 */
public class Baggages {

	private Map<String,Baggage> baggages;
	
	/**
	 * 
	 */
	public Baggages() {
		baggages = new HashMap<>();
	}
	
	/**
	 * @param bagId
	 * @param terminalname
	 * @param flghtNumber
	 */
	public void addBag(String bagId, String terminalname, String flghtNumber)
	{	
		baggages.putIfAbsent( bagId, new Baggage(bagId, terminalname, flghtNumber));
	}
	
	/**
	 * @param bagId
	 * @return bag
	 */
	public Baggage getBag(String bagId)
	{
		return baggages.get(bagId);
	}
	
	/**
	 * @return bags list
	 */
	public List<Baggage> getBags()
	{
		List<Baggage> list = new ArrayList<Baggage>(baggages.values());
		Collections.sort(list,new Comparator<Baggage>() {

			@Override
			public int compare(Baggage o1, Baggage o2) {
			return o1.getBagId().compareTo(o2.getBagId());
			
			}
			
		});
		return  list;
	}
}
