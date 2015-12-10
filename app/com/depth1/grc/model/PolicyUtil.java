package com.depth1.grc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PolicyUtil {

	public List<Policy> sortPolicy(List<Policy> policies, String order){
		
		if(order.compareTo("ascendingPolicyId")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a1.getPolicyId().compareTo(a2.getPolicyId());
				}
				
			});
		} else if(order.compareTo("descendingPolicyId")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a2.getPolicyId().compareTo(a1.getPolicyId());
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
		} else if(order.compareTo("ascendingDescription")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a1.getDescription().compareTo( a2.getDescription()) ;
				}
				
			});
		} else if(order.compareTo("descendingDescription")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a2.getDescription().compareTo( a1.getDescription()) ;
				}
			});
		} else if(order.compareTo("ascendingVersion")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a1.getVersion().compareTo(a2.getVersion()) ;
				}
				
			});
		} else if(order.compareTo("descendingVersion")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a2.getVersion().compareTo(a1.getVersion()) ;
				}
				
			});
		} else if(order.compareTo("ascendingClassification")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a1.getClassification().compareTo(a2.getClassification()) ;
				}
				
			});
		}else if(order.compareTo("descendingClassification")== 0){
			Collections.sort(policies, new Comparator<Policy>() {
				public int compare(Policy a1, Policy a2){
					return a2.getClassification().compareTo(a1.getClassification()) ;
				}
				
			});
		}
		return policies;
	}
	
	
	
	public List<Policy> paginatePolicy(List<Policy> policies, int numberOfItems, int page){
		List<Policy> shortPolicys = new ArrayList<>();
		int start = numberOfItems * (page - 1);
        int end = numberOfItems * (page) - 1;
        for(int x= start; x <= end && x < policies.size(); x++){
        	shortPolicys.add(policies.get(x));
        }
        return shortPolicys;
	}
	
	/**
	 * This method filters the list of Policys by a specific String Query searching
	 * the Risk and Consequences fields.
	 * @param Policies - List of Policys to Filter
	 * @param query - the String to search for
	 * @return - filtered List of Policys
	 */
	public List<Policy> filterDataByQuery(List<Policy> policies, String query){
		List<Policy> filteredPolicys = new ArrayList<>();
		for(int x=0;x < policies.size();x++){
			if(policies.get(x).getName().contains(query)){
				filteredPolicys.add(policies.get(x));
			}else if(policies.get(x).getDescription().contains(query)){
				filteredPolicys.add(policies.get(x));
			}
		}
		
		return filteredPolicys;
		
	}
	
}

	
	

