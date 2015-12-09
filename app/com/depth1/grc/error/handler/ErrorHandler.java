/**
 * 
 */
package com.depth1.grc.error.handler;

import play.http.HttpErrorHandler;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

/**
 * @author badedokun
 *
 */
public class ErrorHandler implements HttpErrorHandler {

	/* (non-Javadoc)
	 * @see play.http.HttpErrorHandler#onClientError(play.mvc.Http.RequestHeader, int, java.lang.String)
	 */
	@Override
	public Promise<Result> onClientError(RequestHeader request, int statusCode, String message) {
		return Promise.<Result>pure(
				Results.status(statusCode, "A client error occurred: " + message)
				);
	}

	/* (non-Javadoc)
	 * @see play.http.HttpErrorHandler#onServerError(play.mvc.Http.RequestHeader, java.lang.Throwable)
	 */
	@Override
	public Promise<Result> onServerError(RequestHeader request, Throwable exception) {
		return Promise.<Result>pure(
				Results.internalServerError("A server error occurred: " + exception.getMessage())
				);
	}
	
	 /**
	  * Displays error message if a protected resource is accessed by unauthorized user
	  * 
	 * @param request http request to the protected resource
	 * @param message message to display
	 * @return the error message
	 */
	protected Promise<Result> onForbidden(RequestHeader request, String message) {
		return Promise.<Result>pure(
				Results.forbidden("You're not allowed to access this resource.")
				);
	}	
}
