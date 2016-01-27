package com.depth1.grc.jpa.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is for utilities for the showFrontObjective (showing the front page of the Objective
 * ). These utilities include sorting the Objectives from the database and searching
 * for Objective that have a particular query.
 * 
 * @author Benjamin J Cargile
 * @version 1.0 -- 9/18/2015
 */
public class ObjectiveSort {

	
	/**
	 * This method sorts the Objective List by particular properties.
	 * @param objective - objective retrieved form the database
	 * @param order - string describing the property to sort on and how to sort
	 * @return - list of sorted Objective
	 */
	public List<Objective> sortObjective(List<Objective> objective, String order){
		
		if(order.compareTo("descendingObjectiveId")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a1.getObjectiveId() > a2.getObjectiveId()? -1:1;
				}
				
			});
		} else if(order.compareTo("ascendingObjectiveId")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a1.getObjectiveId() > a2.getObjectiveId()? 1:-1;
				}
				
			});
		} else if(order.compareTo("ascendingName")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a1.getName().compareTo(a2.getName());
				}
				
			});
		} else if(order.compareTo("descendingName")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a2.getName().compareTo(a1.getName());
				}
				
			});
		} else if(order.compareTo("descendingObjectiveLevel")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a1.getObjectiveLevel().compareTo(a2.getObjectiveLevel()) ;
				}
				
			});
		} else if(order.compareTo("ascendingObjectiveLevel")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a2.getObjectiveLevel().compareTo(a1.getObjectiveLevel()) ;
				}
				
			});
		} else if(order.compareTo("descendingObjectiveType")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a1.getObjectiveType().compareTo(a2.getObjectiveType()) ;
				}
				
			});
		} else if(order.compareTo("ascendingObjectiveType")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a2.getObjectiveType().compareTo(a1.getObjectiveType()) ;
				}
				
			});
		} else if(order.compareTo("descendingObjective")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a1.getObjective().compareTo(a2.getObjective()) ;
				}
				
			});
		} else if(order.compareTo("ascendingObjective")== 0){
			Collections.sort(objective, new Comparator<Objective>() {
				public int compare(Objective a1, Objective a2){
					return a2.getObjective().compareTo(a1.getObjective()) ;
				}
				
			});
				
	    }
		
		return objective;
	}
	/**
	 * This method pulls out a specific number of Tenants to display for pagination.
	 * @param riskAssessments - list of Tenants
	 * @param numberOfItems - the number of items to show per page
	 * @param page - the current page number for pagination
	 * @return - a List of Tenants 
	 */
	public List<Objective> paginateObjective(List<Objective> objective, int numberOfItems, int page){
		List<Objective> shortObjective = new ArrayList<>();
		int start = numberOfItems * (page - 1);
        int end = numberOfItems * (page) - 1;
        for(int x= start; x <= end && x < objective.size(); x++){
        	shortObjective.add(objective.get(x));
        }
        return shortObjective;
	}
	/**
	 * This method filters the list of Tenants by a specific String Query searching
	 * the Name variable.
	 * @param tenants - List of Tenants to Filter
	 * @param query - the String to search for
	 * @return - filtered List of Tenants
	 */
	public List<Objective> filterDataByQuery(List<Objective> objective, String query){
		List<Objective> filteredObjective = new ArrayList<>();
		for(int x=0;x < objective.size();x++){
			if(objective.get(x).getName().contains(query)){
				filteredObjective.add(objective.get(x));
			}
		}
		
		return filteredObjective;
		
	}
}
