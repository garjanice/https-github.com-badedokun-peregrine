/**
 * 
 */
package com.depth1.grc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is for utilities for the showFrontUserProfile (showing the front page of the UserProfile
 * ). These utilities include sorting the UserProfiles from the database and searching
 * for UserProfiles that have a particular query.
 * 
 * @author Benjamin J Cargile
 * @version 1.0 -- 9/24/2015
 */
public class UserProfileSort {
	/**
	 * This method sorts the UserProfiles List by particular properties.
	 * @param userprofiles - userprofiles retrieved form the database
	 * @param order - string describing the property to sort on and how to sort
	 * @return - list of sorted UserProfiles
	 */
	public List<UserProfile> sortUserProfile(List<UserProfile> userprofiles, String order){
		
		if(order.compareTo("descendingName")== 0){
			Collections.sort(userprofiles, new Comparator<UserProfile>() {
				public int compare(UserProfile a1, UserProfile a2){
					String name1 = a1.getLastName() + " " + a1.getFirstName(); 
					String name2 = a2.getLastName() + " " + a2.getFirstName();
					return name1.compareTo(name2);
				}
				
			});
		} else if(order.compareTo("ascendingName")== 0){
			Collections.sort(userprofiles, new Comparator<UserProfile>() {
				public int compare(UserProfile a1, UserProfile a2){
					String name1 = a1.getLastName() + " " + a1.getFirstName(); 
					String name2 = a2.getLastName() + " " + a2.getFirstName();
					return name2.compareTo(name1);
				}
				
			});
		} else if(order.compareTo("ascendingUsername")== 0){
			Collections.sort(userprofiles, new Comparator<UserProfile>() {
				public int compare(UserProfile a1, UserProfile a2){
					return a1.getUsername().compareTo(a2.getUsername());
				}
				
			});
		} else if(order.compareTo("descendingUsername")== 0){
			Collections.sort(userprofiles, new Comparator<UserProfile>() {
				public int compare(UserProfile a1, UserProfile a2){
					return a2.getUsername().compareTo(a1.getUsername());
				}
				
			});
		} else if(order.compareTo("descendingEmail")== 0){
			Collections.sort(userprofiles, new Comparator<UserProfile>() {
				public int compare(UserProfile a1, UserProfile a2){
					return a1.getEmail().compareTo(a2.getEmail()) ;
				}
				
			});
		} else if(order.compareTo("ascendingEmail")== 0){
			Collections.sort(userprofiles, new Comparator<UserProfile>() {
				public int compare(UserProfile a1, UserProfile a2){
					return a2.getEmail().compareTo(a1.getEmail()) ;
				}
				
			});
		
		}
		return userprofiles;
	}
	/**
	 * This method pulls out a specific number of UserProfiles to display for pagination.
	 * @param userprofiles - list of UserProfiles
	 * @param numberOfItems - the number of items to show per page
	 * @param page - the current page number for pagination
	 * @return - a List of UserProfiles 
	 */
	public List<UserProfile> paginateUserProfiles(List<UserProfile> userprofiles, int numberOfItems, int page){
		List<UserProfile> shortUserProfiles = new ArrayList<>();
		int start = numberOfItems * (page - 1);
        int end = numberOfItems * (page) - 1;
        for(int x= start; x <= end && x < userprofiles.size(); x++){
        	shortUserProfiles.add(userprofiles.get(x));
        }
        return shortUserProfiles;
	}
	/**
	 * This method filters the list of UserProfiles by a specific String Query searching
	 * the Name variable.
	 * @param tenants - List of UserProfiles to Filter
	 * @param query - the String to search for
	 * @return - filtered List of UserProfiles
	 */
	public List<UserProfile> filterDataByQuery(List<UserProfile> userprofiles, String query){
		List<UserProfile> filteredUserProfiles = new ArrayList<>();
		String name;
		for(int x=0;x < userprofiles.size();x++){
			name = userprofiles.get(x).getFirstName() + " " + userprofiles.get(x).getLastName();
			if(name.contains(query)){
				filteredUserProfiles.add(userprofiles.get(x));
			} else if (userprofiles.get(x).getUsername().contains(query)){
				filteredUserProfiles.add(userprofiles.get(x));
			}
		}
		
		return filteredUserProfiles;
		
	}
}
