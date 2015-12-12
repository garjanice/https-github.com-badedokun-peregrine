/**
 * 
 */
package com.depth1.grc.db.util;

/**
 * An exception that indicates some operations with the back-end data source failed.
 * @author Bisi Adedokun
 *
 */
public class DataStoreException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Throwable rootCause;

	/**
	 * Wrap a DataStoreException around another throwable.
	 * 
	 * @param rootCause Root cause of the exception
	 */
	public DataStoreException(Throwable rootCause) {
		super(rootCause.getMessage());
		this.rootCause = rootCause;
	}

	/**
	 * Construct an exception with the specified detail message.
	 * 
	 * @param message Exception message
	 * @param rootCause Root cause of the exception
	 */
	public DataStoreException(String message, Throwable rootCause) {
		super(message, rootCause);
		this.rootCause = rootCause;
	}

	/**
	 * Construct an exception with the specified detail message.
	 */
	public DataStoreException(String message) {
		super(message);
	}

	/**
	 * @return a reference to the root exception or null.
	 */
	public Throwable getRootCause() {
		return this.rootCause;
	}

}
