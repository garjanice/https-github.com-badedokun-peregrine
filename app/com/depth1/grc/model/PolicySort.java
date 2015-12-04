package com.depth1.grc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is for utilities for the showFrontRAPageQuery (showing the front page of the Risk
 * Assessment). These utilities include sorting the Policys from the database and searching
 * for Policys that have a particular query.
 * 
 * @author Benjamin J Cargile
 * @version 1.0 -- 8/27/2015
 */
public class PolicySort {
	
	/**
	 * Sorts the Policys List by particular properties.
	 * @param policies - riskAssessment retrieved form the database
	 * @param order - string describing the property to sort on and how to sort
	 * @return - list of sorted Policys
	 */
	public List<Policy> sortPolicy(List<Policy> policies, String order){
		
		if(order.compareTo("ascendingPolicyId")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a1.getId().compareTo(a2.getId());
				}
				
			});
		} else if(order.compareTo("descendingPolicyId")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a2.getId().compareTo(a1.getId());
				}
				
			});
		} else if(order.compareTo("ascendingName")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a1.getName().compareTo(a2.getName());
				}
				
			});
		} else if(order.compareTo("descendingName")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a2.getName().compareTo(a1.getName());
				}
				
			});
		} else if(order.compareTo("descendingDescription")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a1.getDescription().compareTo(a2.getDescription());
				}
				
			});
		} else if(order.compareTo("ascendingDescription")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a2.getDescription().compareTo(a1.getDescription());
				}
				
			});
		} else if(order.compareTo("descendingVersion")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a1.getVersion().compareTo(a2.getVersion());
				}
				
			});
		} else if(order.compareTo("ascendingVersion")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a2.getVersion().compareTo(a1.getVersion());
				}
				
			});
		}else if(order.compareTo("descendingClassification")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a1.getClassification().compareTo(a2.getClassification());
				}
				
			});
		} else if(order.compareTo("ascendingClassification")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a2.getClassification().compareTo(a1.getClassification());
				}
				
			});
		}
		return policies;
	}
	
	/**
	 * Pulls out a specific number of Policy to display for pagination.
	 * @param policies - list of Policies
	 * @param numberOfItems - the number of items to show per page
	 * @param page - the current page number for pagination
	 * @return - a List of Policies 
	 */
	
	public List<Policy> paginatePolicy(List<Policy> policies, int numberOfItems, int page){
		List<Policy> shortPolicies = new ArrayList<>();
		int start = numberOfItems * (page - 1);
        int end = numberOfItems * (page) - 1;
        for(int x= start; x <= end && x < policies.size(); x++){
        	shortPolicies.add(policies.get(x));
        }
        return shortPolicies;
	}
	
	/**
	 * Filters the list of Policies by a specific String Query searching
	 * the Risk and Consequences fields.
	 * @param policies - List of Policies to Filter
	 * @param query - the String to search for
	 * @return - filtered List of Policies
	 */
	public List<Policy> filterDataByQuery(List<Policy> policies, String query){
		List<Policy> filteredPolicies = new ArrayList<>();
		for(int x=0;x < policies.size();x++){
			if(policies.get(x).getName().contains(query)){
				filteredPolicies.add(policies.get(x));
			}else if(policies.get(x).getDescription().contains(query)){
				filteredPolicies.add(policies.get(x));
			}
		}
		
		return filteredPolicies;
		
	}
	
}
