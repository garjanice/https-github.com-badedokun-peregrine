/**
 * 
 */
package com.depth1.grc.model;

import com.depth1.grc.db.util.DataException;

/**
 * Data Access Object specific exception handler
 * @author Bisi Adedokun
 *
 */
public class DaoException extends DataException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param rootCause
	 */
	public DaoException(Throwable rootCause) {
		super(rootCause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
