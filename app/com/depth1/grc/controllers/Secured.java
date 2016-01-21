/**
 * 
 */
package com.depth1.grc.controllers;

import com.depth1.grc.views.html.login;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * This class protect actions with authentication using action composition - the ability to compose
 * multiple actions together in a chain.
 * @author Bisi Adedokun
 *
 */
public class Secured extends Security.Authenticator {
	/* (non-Javadoc)
	 * @see play.mvc.Security.Authenticator#getUsername(play.mvc.Http.Context)
	 */
	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("username");
		
	}
	
	/* (non-Javadoc)
	 * @see play.mvc.Security.Authenticator#onUnauthorized(play.mvc.Http.Context)
	 */
	@Override
	public Result onUnauthorized(Context cts) {

		return ok(login.render());

		//return redirect(routes.controllers.Applicationlogin());
	}

}
