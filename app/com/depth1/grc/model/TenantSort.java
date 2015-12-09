package com.depth1.grc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is for utilities for the showFrontTenant (showing the front page of the Tenant
 * ). These utilities include sorting the Tenants from the database and searching
 * for Tenants that have a particular query.
 * 
 * @author Benjamin J Cargile
 * @version 1.0 -- 9/18/2015
 */
public class TenantSort {

	
	/**
	 * This method sorts the Tenants List by particular properties.
	 * @param tenats - tenants retrieved form the database
	 * @param order - string describing the property to sort on and how to sort
	 * @return - list of sorted Tenants
	 */
	public List<Tenant> sortTenant(List<Tenant> tenants, String order){
		
		if(order.compareTo("descendingTenantId")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a1.getTenantId() > a2.getTenantId()? -1:1;
				}
				
			});
		} else if(order.compareTo("ascendingTenantId")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a1.getTenantId() > a2.getTenantId()? 1:-1;
				}
				
			});
		} else if(order.compareTo("ascendingName")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a1.getName().compareTo(a2.getName());
				}
				
			});
		} else if(order.compareTo("descendingName")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a2.getName().compareTo(a1.getName());
				}
				
			});
		} else if(order.compareTo("descendingCreatedDate")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a1.getCreateDateString().compareTo(a2.getCreateDateString()) ;
				}
				
			});
		} else if(order.compareTo("ascendingCreatedDate")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a2.getCreateDateString().compareTo(a1.getCreateDateString()) ;
				}
				
			});
		} else if(order.compareTo("descendingServiceStartDate")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a1.getServiceStartDate().compareTo(a2.getServiceStartDate()) ;
				}
				
			});
		} else if(order.compareTo("ascendingServiceStartDate")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a2.getServiceStartDate().compareTo(a1.getServiceStartDate()) ;
				}
				
			});
		} else if(order.compareTo("descendingStatus")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a1.getStatus().compareTo(a2.getStatus()) ;
				}
				
			});
		} else if(order.compareTo("ascendingStatus")== 0){
			Collections.sort(tenants, new Comparator<Tenant>() {
				public int compare(Tenant a1, Tenant a2){
					return a2.getStatus().compareTo(a1.getStatus()) ;
				}
				
			});
		}
		return tenants;
	}
	/**
	 * This method pulls out a specific number of Tenants to display for pagination.
	 * @param riskAssessments - list of Tenants
	 * @param numberOfItems - the number of items to show per page
	 * @param page - the current page number for pagination
	 * @return - a List of Tenants 
	 */
	public List<Tenant> paginateTenants(List<Tenant> tenants, int numberOfItems, int page){
		List<Tenant> shortTenants = new ArrayList<>();
		int start = numberOfItems * (page - 1);
        int end = numberOfItems * (page) - 1;
        for(int x= start; x <= end && x < tenants.size(); x++){
        	shortTenants.add(tenants.get(x));
        }
        return shortTenants;
	}
	/**
	 * This method filters the list of Tenants by a specific String Query searching
	 * the Name variable.
	 * @param tenants - List of Tenants to Filter
	 * @param query - the String to search for
	 * @return - filtered List of Tenants
	 */
	public List<Tenant> filterDataByQuery(List<Tenant> tenants, String query){
		List<Tenant> filteredTenants = new ArrayList<>();
		for(int x=0;x < tenants.size();x++){
			if(tenants.get(x).getName().contains(query)){
				filteredTenants.add(tenants.get(x));
			}
		}
		
		return filteredTenants;
		
	}
}
