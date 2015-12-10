package com.depth1.grc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is for utilities for the showFrontRAPageQuery (showing the front page of the Risk
 * Assessment). These utilities include sorting the RiskAssessments from the database and searching
 * for RiskAssessments that have a particular query.
 * 
 * @author Benjamin J Cargile
 * @version 1.0 -- 8/27/2015
 */
public class RiskAssessmentSort {
	
	/**
	 * This method sorts the RiskAssessments List by particular properties.
	 * @param riskAssessments - riskAssessment retrieved form the database
	 * @param order - string describing the property to sort on and how to sort
	 * @return - list of sorted RiskAssessments
	 */
	public List<RiskAssessment> sortRiskAssessment(List<RiskAssessment> riskAssessments, String order){
		
		if(order.compareTo("ascendingRisk")== 0){
			Collections.sort(riskAssessments, new Comparator<RiskAssessment>() {
				public int compare(RiskAssessment a1, RiskAssessment a2){
					return a1.getRisk().compareTo(a2.getRisk());
				}
				
			});
		} else if(order.compareTo("descendingRisk")== 0){
			Collections.sort(riskAssessments, new Comparator<RiskAssessment>() {
				public int compare(RiskAssessment a1, RiskAssessment a2){
					return a2.getRisk().compareTo(a1.getRisk());
				}
				
			});
		} else if(order.compareTo("ascendingConsequence")== 0){
			Collections.sort(riskAssessments, new Comparator<RiskAssessment>() {
				public int compare(RiskAssessment a1, RiskAssessment a2){
					return a1.getConsequence().compareTo(a2.getConsequence());
				}
				
			});
		} else if(order.compareTo("descendingConsequence")== 0){
			Collections.sort(riskAssessments, new Comparator<RiskAssessment>() {
				public int compare(RiskAssessment a1, RiskAssessment a2){
					return a2.getConsequence().compareTo(a1.getConsequence());
				}
				
			});
		} else if(order.compareTo("descendingSeverity")== 0){
			Collections.sort(riskAssessments, new Comparator<RiskAssessment>() {
				public int compare(RiskAssessment a1, RiskAssessment a2){
					return a1.getSeverity() > a2.getSeverity()? -1:1 ;
				}
				
			});
		} else if(order.compareTo("ascendingSeverity")== 0){
			Collections.sort(riskAssessments, new Comparator<RiskAssessment>() {
				public int compare(RiskAssessment a1, RiskAssessment a2){
					return a1.getSeverity() < a2.getSeverity()? -1:1 ;
				}
				
			});
		} else if(order.compareTo("descendingLikelihood")== 0){
			Collections.sort(riskAssessments, new Comparator<RiskAssessment>() {
				public int compare(RiskAssessment a1, RiskAssessment a2){
					return a1.getLikelihood() > a2.getLikelihood()? -1:1 ;
				}
				
			});
		} else if(order.compareTo("ascendingLikelihood")== 0){
			Collections.sort(riskAssessments, new Comparator<RiskAssessment>() {
				public int compare(RiskAssessment a1, RiskAssessment a2){
					return a1.getLikelihood() < a2.getLikelihood()? -1:1 ;
				}
				
			});
		}
		return riskAssessments;
	}
	
	/**
	 * This method pulls out a specific number of RiskAssessment to display for pagination.
	 * @param riskAssessments - list of RiskAssessments
	 * @param numberOfItems - the number of items to show per page
	 * @param page - the current page number for pagination
	 * @return - a List of RiskAssessments 
	 */
	
	public List<RiskAssessment> paginateRiskAssessment(List<RiskAssessment> riskAssessments, int numberOfItems, int page){
		List<RiskAssessment> shortRiskAssessments = new ArrayList<>();
		int start = numberOfItems * (page - 1);
        int end = numberOfItems * (page) - 1;
        for(int x= start; x <= end && x < riskAssessments.size(); x++){
        	shortRiskAssessments.add(riskAssessments.get(x));
        }
        return shortRiskAssessments;
	}
	
	/**
	 * This method filters the list of RiskAssessments by a specific String Query searching
	 * the Risk and Consequences fields.
	 * @param riskAssessments - List of RiskAssessments to Filter
	 * @param query - the String to search for
	 * @return - filtered List of RiskAssessments
	 */
	public List<RiskAssessment> filterDataByQuery(List<RiskAssessment> riskAssessments, String query){
		List<RiskAssessment> filteredRiskAssessments = new ArrayList<>();
		for(int x=0;x < riskAssessments.size();x++){
			if(riskAssessments.get(x).getRisk().contains(query)){
				filteredRiskAssessments.add(riskAssessments.get(x));
			}else if(riskAssessments.get(x).getConsequence().contains(query)){
				filteredRiskAssessments.add(riskAssessments.get(x));
			}
		}
		
		return filteredRiskAssessments;
		
	}
	
}

