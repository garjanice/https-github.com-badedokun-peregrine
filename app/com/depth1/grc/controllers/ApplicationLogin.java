/**
 * 
 */
package com.depth1.grc.controllers;

import com.depth1.grc.model.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Login;
import com.depth1.grc.model.TenantDao;
import com.depth1.grc.model.UserProfile;
import com.depth1.grc.model.UserProfileDao;
import com.depth1.grc.views.html.index;
import com.depth1.grc.views.html.login;


import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * This is the Application entry point for sign in to the application. It authenticates a user against a security realm.
 * Presently, only database realm is supported but the goal is to provide support for multiple realms.
 * 
 * @author Bisi Adedokun
 *
 */
public class ApplicationLogin extends Controller {
	
	static DaoFactory cassandraFactory = DaoFactory.getDaoFactory(DaoFactory.CASSANDRA);
	private static final Form<Login> loginForm = Form.form(Login.class);
	
	public Result login() {
		return ok(login.render(loginForm));
	}
	
	/**
	 * Authenticates a user during login to the application
	 * @return Result result of the action call
	 */
	public Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		String username = loginForm.get().getUsername();
		String password = loginForm.get().getPassword();
		try {
			UserProfileDao login = cassandraFactory.getUserProfileDao();
			if (login.authenticate(username, password) == null) {
				flash("login_error", "invalid username or password");
				return forbidden("invalid username or password");
			}
		} catch (DaoException e) {
			Logger.error("Error occurred while reading user data ", e);
		}
		
		
		session().clear();
		session("username", username);
				
		return ok(index.render());
				
	}

}
