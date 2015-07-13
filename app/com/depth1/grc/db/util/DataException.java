/**
 * 
 */
package com.depth1.grc.db.util;

/**
<<<<<<< HEAD
 * @author Bisi Adedokun
 * An exception that indicates some operation with the back-end data source failed.
 * 
 *
=======
 * An exception that indicates some operation with the back-end data source failed.
 * @author Bisi Adedokun
 * 
>>>>>>> 4719f061e98dd07f242674efbf041aae9c8c9c28
 */
public class DataException extends Exception {

	/**
	 * An exception that indicates some operation with the back-end
	 * data source failed.
	 */
	    
		private static final long serialVersionUID = 1L;
		private Throwable rootCause;

	    /**
	     * Wrap a DataException around another throwable.
	     */
	    public DataException(Throwable rootCause) {
	        super(rootCause.getMessage( ));
	        this.rootCause = rootCause;
	    }

	    /**
	     * Construct an exception with the specified detail message.
	     */
	    public DataException(String message) {
	        super(message);
	    }

	    /**
	     * @return a reference to the root exception or null.
	     */
	    public Throwable getRootCause( ) {
	        return this.rootCause;
	    }
}
