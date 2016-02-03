/**
 * 
 */
package com.depth1.grc.controllers;

import java.util.HashMap;
import java.util.Map;

import com.depth1.grc.exception.DaoException;
import com.depth1.grc.model.DaoFactory;
import com.depth1.grc.model.Login;
import com.depth1.grc.model.UserProfile;
import com.depth1.grc.model.UserProfileDao;
import com.depth1.grc.util.IdProducer;
import com.depth1.grc.views.html.*;
import com.depth1.grc.views.html.login;

import play.Logger;
import play.cache.Cache;
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
	public static final String LOGIN_EMAIL = "username";
	public static final String SUCCESS = "success";
	
	/**
	 * Authenticates a user during login to the application.
	 * 
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
	
	/**
     * Login a user into the application.
     * 
     * @return Result result of the action call
     */
	public Result login() {

		return ok(login.render());

	}
	
	/**
     * Logout a login user when the user clicks the logout button.
     * 
     * @return Result result of the action call
     */
    public Result logout() {
        session().remove(LOGIN_EMAIL);
        Cache.remove(LOGIN_EMAIL);
        flash(SUCCESS, "You have successfully logged out!");
        return redirect(routes.ApplicationLogin.login() );
    }

}
