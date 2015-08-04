/**
 * 
 */
package com.depth1.grc.global;

import java.util.Date;

import com.depth1.grc.model.common.AnnotationDateFormatter;

import play.Application;
import play.GlobalSettings;
import play.data.format.Formatters;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

/**
 * This class is used to managed actions that the application should perform during its lifecycle: onStart,
 * onStop, etc.
 * @author Bisi Adedokun
 *
 */
public class Global extends GlobalSettings {
	

    /**
     * Annotates any object property on a model class to indicate that we want to bind and unbind 
     * Date from a Form object using the following syntax: @DateFormat("MM-dd-yyyy")
     * 
     * 
     */
    @Override
    public void onStart(Application app) {
        super.onStart(app);
        Formatters.register(Date.class, new AnnotationDateFormatter());
    }	

	 /**
     * The onBadRequest operation will be called if a route was found, but it was not possible
     * to bind the request parameters.
     * 
     * @param request request header to determine what type of request this is
     * @param error error message
     */
    @Override
    public Promise<Result> onBadRequest(RequestHeader request, String error) {
        return F.Promise.<Result>pure(Results.badRequest("We cannot proceed, your URI request is Bad!"));
    }	   

}
