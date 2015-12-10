package com.depth1.grc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is for utilities for the showFrontRAPageQuery (showing the front page of the Risk
 * Assessment). These utilities include sorting the RiskRegisters from the database and searching
 * for RiskRegisters that have a particular query.
 * 
 * @author Benjamin J Cargile
 * @version 1.0 -- 8/27/2015
 */
public class RiskRegisterSort {
	
	/**
	 * This method sorts the RiskRegisters List by particular properties.
	 * @param riskRegisters - riskRegister retrieved form the database
	 * @param order - string describing the property to sort on and how to sort
	 * @return - list of sorted RiskRegisters
	 */
	public List<RiskRegister> sortRiskRegister(List<RiskRegister> riskRegisters, String order){
		
		if(order.compareTo("ascendingName")== 0){
			Collections.sort(riskRegisters, new Comparator<RiskRegister>() {
				public int compare(RiskRegister a1, RiskRegister a2){
					return a1.getName().compareTo(a2.getName());
				}
				
			});
		} else if(order.compareTo("descendingName")== 0){
			Collections.sort(riskRegisters, new Comparator<RiskRegister>() {
				public int compare(RiskRegister a1, RiskRegister a2){
					return a2.getName().compareTo(a1.getName());
				}
				
			});
		} else if(order.compareTo("ascendingDescription")== 0){
			Collections.sort(riskRegisters, new Comparator<RiskRegister>() {
				public int compare(RiskRegister a1, RiskRegister a2){
					return a1.getDescription().compareTo(a2.getDescription());
				}
				
			});
		} else if(order.compareTo("descendingDescription")== 0){
			Collections.sort(riskRegisters, new Comparator<RiskRegister>() {
				public int compare(RiskRegister a1, RiskRegister a2){
					return a2.getDescription().compareTo(a1.getDescription());
				}
				
			});
		} 
		return riskRegisters;
	}
	
	/**
	 * This method pulls out a specific number of RiskRegister to display for pagination.
	 * @param riskRegisters - list of RiskRegisters
	 * @param numberOfItems - the number of items to show per page
	 * @param page - the current page number for pagination
	 * @return - a List of RiskRegisters 
	 */
	
	public List<RiskRegister> paginateRiskRegister(List<RiskRegister> riskRegisters, int numberOfItems, int page){
		List<RiskRegister> shortRiskRegisters = new ArrayList<>();
		int start = numberOfItems * (page - 1);
        int end = numberOfItems * (page) - 1;
        for(int x= start; x <= end && x < riskRegisters.size(); x++){
        	shortRiskRegisters.add(riskRegisters.get(x));
        }
        return shortRiskRegisters;
	}
	
	/**
	 * This method filters the list of RiskRegisters by a specific String Query searching
	 * the Risk and Consequences fields.
	 * @param riskRegisters - List of RiskRegisters to Filter
	 * @param query - the String to search for
	 * @return - filtered List of RiskRegisters
	 */
	public List<RiskRegister> filterDataByQuery(List<RiskRegister> riskRegisters, String query){
		List<RiskRegister> filteredRiskRegisters = new ArrayList<>();
		for(int x=0;x < riskRegisters.size();x++){
			if(riskRegisters.get(x).getName().contains(query)){
				filteredRiskRegisters.add(riskRegisters.get(x));
			}else if(riskRegisters.get(x).getDescription().contains(query)){
				filteredRiskRegisters.add(riskRegisters.get(x));
			}
		}
		
		return filteredRiskRegisters;
		
	}
	
}

