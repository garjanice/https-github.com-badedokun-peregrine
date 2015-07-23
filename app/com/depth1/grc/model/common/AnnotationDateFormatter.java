/**
 * 
 */
package com.depth1.grc.model.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import play.data.format.Formatters;

/**
 * This class provides capability to annotate date data with a date format pattern and converts the data
 * from String to Object and Object to String in a Form.
 * 
 * @author Bisi Adedokun
 *
 */
public class AnnotationDateFormatter extends Formatters.AnnotationFormatter<DateFormat, Date> {

	/* (non-Javadoc)
	 * @see play.data.format.Formatters.AnnotationFormatter#parse(java.lang.annotation.Annotation, java.lang.String, java.util.Locale)
	 */
	@Override
	public Date parse(DateFormat annotation, String text, Locale locale) throws ParseException {
		if(text == null || text.trim().isEmpty()) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(annotation.value(), locale);
		sdf.setLenient(false);
		return sdf.parse(text);
	}

	/* (non-Javadoc)
	 * @see play.data.format.Formatters.AnnotationFormatter#print(java.lang.annotation.Annotation, java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(DateFormat annotation, Date value, Locale locale) {
		if(value == null) {
			return "";
		}
		
		return new SimpleDateFormat (annotation.value(), locale).format(value);

	}
	

}
