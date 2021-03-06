/**
 * 
 */
package com.depth1.grc.global;

import java.util.Date;

import com.depth1.grc.db.util.CacheUtil;
import com.depth1.grc.model.common.AnnotationDateFormatter;
import com.depth1.grc.security.GlobalSecurity;

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
     * On Start, set Tenant into the Play Cache framework for application wide use
     * 
     * 
     */
    @Override
    public void onStart(Application app) {
        CacheUtil.setTenantInCache(); // Set Tenant into the Play Cache framework
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
