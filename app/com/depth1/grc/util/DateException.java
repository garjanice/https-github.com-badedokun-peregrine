/**
 * 
 */
package com.depth1.grc.util;

/**
 * This exception class indicates that some operations with Date manipulation failed
 * @author Bisi Adedokun
 *
 */


public class DateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Throwable cause;

	/**
	 * No argument constructor
	 */
	public DateException() {
		
	}

	/**
	 * Constructor with error message
	 * @param message the error message to display
	 */
	public DateException(String message) {
		super(message);
	}

	/**
	 * Wrap a DateException around another throwable.
	 * @param cause the root cause of the error
	 */
	public DateException(Throwable cause) {
		super(cause.getMessage());
        this.cause = cause;
	}

	/**
	 * Constructor with message and root cause
	 * @param message the error message
	 * @param cause the root cause of the error
	 */
	public DateException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * Constructor with various information to help understand the error condition
	 * @param message the error message to display
	 * @param cause the root cause of the error
	 * @param enableSuppression true if suppression of the error is enabled, false otherwise
	 * @param writableStackTrace true if track trace is writable, false otherwise
	 */
	public DateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
