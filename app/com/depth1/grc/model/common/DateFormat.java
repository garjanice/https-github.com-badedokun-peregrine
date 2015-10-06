/**
 * 
 */
package com.depth1.grc.model.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import play.data.Form;

/**
 * A date format interface that is passed as a type to Date Formatter class.
 * 
 * @author Bisi Adedokun
 *
 */

@Target((ElementType.FIELD))
@Retention(RetentionPolicy.RUNTIME)
@Form.Display(name = "format.date", attributes = ("value"))

public @interface DateFormat {
	
	String value();

}
