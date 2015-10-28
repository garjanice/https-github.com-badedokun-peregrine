/**
 * 
 */
package com.depth1.grc.controllers;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.depth1.grc.db.util.DataException;
import com.depth1.grc.db.util.DropDownList;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.util.Picture;
import com.depth1.grc.views.html.index;
import com.google.common.io.Files;

import play.Logger;
import play.Play;
import play.libs.F;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * This class provides utility methods to load drop down list data for different front end use.
 * 
 * @author Bisi Adedokun
 * @version Rev 1.0
 * Created 10/19/2015
 *
 */
public class Universal extends Controller {
	
	// create the required DAO Factory
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);	

    /**
     * This method retrieves states in a given country
     * @param country The ISO name of the country, for example 'US' or 'CA' or 'CH'
     * @return A list of all states in the specified country
     */
    public Result getStateOption(String countryCode){
    	List<String> states = null;
    	try {
			DropDownList dropDown = cassandraFactory.getDropDownList();
			states = dropDown.getState(countryCode);
			Collections.sort(states);			

		} catch (DataException e) {

		}	    	
    	if(states == null){
    		return null;
    	}
    	StringBuilder options = new StringBuilder();
    	for(int i = 0; i < states.size(); i++){
    		options.append("<option value='"+states.get(i)+"'>"+states.get(i)+"</option>");
    	}
    	return ok(options.toString());
    }
    
    /**
     * Retrieves countries of the world from the data store
     * @param country The ISO name of the country, for example 'US' or 'CA' or 'CH'
     * @param state The state where the county is
     * @return A list of all county in the specified state
     */
    public Result getCountryOption(){
    	List<String> countries = null;
    	try {
			DropDownList dropDown = cassandraFactory.getDropDownList();
			countries = dropDown.getCountry();
			Collections.sort(countries);			

		} catch (DataException e) {

		}
    	
    	if(countries == null){
    		return null;
    	}
    	StringBuilder options = new StringBuilder();
    	for(int i = 0; i < countries.size(); i++){
    		options.append("<option value='"+countries.get(i)+"'>"+countries.get(i)+"</option>");
    	}
    	return ok(options.toString());
    }
    
    
    /**
     * Retrieves countries of the world from the data store
     * @param country The ISO name of the country, for example 'US' or 'CA' or 'CH'
     * @param state The state where the county is
     * @return A list of all county in the specified state
     */
    public Result getLanguageOption(){
    	List<String> languages = null;
    	try {
			DropDownList dropDown = cassandraFactory.getDropDownList();
			languages = dropDown.getLanguage();
			Collections.sort(languages);			

		} catch (DataException e) {

		}
    	
    	if(languages == null){
    		return null;
    	}
    	StringBuilder options = new StringBuilder();
    	for(int i = 0; i < languages.size(); i++){
    		options.append("<option value='"+languages.get(i)+"'>"+languages.get(i)+"</option>");
    	}
    	return ok(options.toString());
    }   
    
    /**
     * Retrieves picture from a URL path or directory
     * @param pictureName picture to retrieve
     * @return picture from the URL path or directory
     */
    public Promise<Result> getPicture(final String pictureName){

    	return Promise.promise(new F.Function0<Result>(){
			@Override
			public Result apply() throws Throwable {
				String pictureUrl = new File("public/images",pictureName).getPath();
				byte[] picture = new Picture().loadPicture(pictureUrl);
				return ok(picture);
			}
    	}).recover(new Function<Throwable, Result>(){
			@Override
			public Result apply(Throwable arg0) throws Throwable {				
				//return redirect(routes.Universal.defaultPicture());
				return ok(index.render());
			}

    	});
    }   
    
    /**
     * @return
     */
    public Result defaultPicture(){
    	File file =  Play.application().getFile("/public/images/placeholder-profile-pic.jpg");
    	byte[] picture = null;
    	try {
			picture = Files.toByteArray(file);
		} catch (Exception e) {
			Logger.error("Error occurred while reading file ", e);
		}
    	return ok(picture);
    }    
}
