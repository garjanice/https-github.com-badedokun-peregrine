
/**
 * 
 */
package com.depth1.grc.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.UUID;

import com.depth1.grc.exception.DateException;

/**
 * Date utility class for converting dates from different formats; including date validations
 * 
 * @author Bisi Adedokun
 *
 */
public final class DateUtility {
	
	private static final String YEAR_GOOD = "YEAR_GOOD";
	private static final String YEAR_BAD = "YEAR_BAD";
	private static final String MONTH_GOOD = "MONTH_GOOD";
	private static final String MONTH_BAD = "MONTH_BAD";
	private static final String DAY_GOOD = "DAY_GOOD";
	private static final String DAY_BAD = "DAY_BAD";

	public static final String DISPLAY_FORMAT = "yyyy/MM/dd hh:mm:ss";
	public static final String DATABASE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String OTHER_DATE_FORMAT = "dd/MM/yyyy";
	public static final String NORTH_AMERICA_DATE_FORMAT = "MM/dd/yyyy";
	
	static final long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;
	
	//public static DateFormat IN_TIMESTAMP_FORMAT = new SimpleDateFormat("MM/dd/yyyy H:mm:ss.SSS");
	public static DateFormat IN_TIMESTAMP_FORMAT = new SimpleDateFormat(NORTH_AMERICA_DATE_FORMAT);


	/**
	 * 
	 */
	public DateUtility() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Date utility to format date retrieved from timeuuid.
	 * 
	 * @param dateFormat desired date format
	 * @param date date retrieved from the database
	 * @return a string representative of the date
	 */
	public static String formatDateFromUUID(String dateFormat, Date date) {
		
		if (date == null) {
			return null;
		} else {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date dateTime = date;
			return format.format(dateTime);
		}
	}
	
	/**
	 * Gets date as a milliseconds since "the epoch", namely January 1, 1970, 00:00:00 GMT
	 * To convert the return value to a date object use the following syntax:
	 * Date date = new Date(dateTime);
	 *  
	 * @param dateFormat date as a string format
	 * @return corresponding date as a milliseconds
	 */
	public static long getDateTime (String dateFormat) {
		long dateTime = 1L;
		try {
			if (dateFormat == null) {
				throw new ParseException("Argument cannot be null.", 0);
			} else if (dateFormat.isEmpty()) {
				throw new ParseException("Argument cannot be empty.", 0);
			} else if (dateFormat.endsWith(".")) {
				throw new ParseException("dateFormat ends with invalid character.", dateFormat.length() - 1);
			}

			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date date = new Date();
			String now = sdf.format(date);
			Date timenow = sdf.parse(now);
			dateTime = timenow.getTime();

		} catch (ParseException e) {
			// do nothing	
		}
		return dateTime;

	}
	
	
	/**
	* Formats a date object when a date string is passed to it as argument.
	* 
	* Creation date: (1/29/2002 6:06:30 PM)
	* @param date date in a string format
	* @return actualDate date object after it has been converted from a string format
	*/
	public static Date getDate(String date) {
		java.util.Date actualDate = new java.util.Date();

		if (date == null) {
			return null;
		} else {

			try {
				DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
				actualDate = dateFormat.parse(date);

			}
			catch (ParseException pe){
			}
		}
		return actualDate;
	}
	
	/**
	 * Validates date input and test for leap year when year is int yyyy.
	 * Creation date: (1/25/2002 1:11:45 PM)
	 * @param year the year of the date
	 * @param month the month of the date
	 * @param day the day of the date
	 */
	public static boolean isDateValid(int year, String month, String day) {

		boolean validInput = true;
		int monthValue = 1;
		int dayValue = 1;

		// Get the input parameters
		try {

			monthValue = Integer.parseInt(month);
			dayValue = Integer.parseInt(day);

		}
		catch (java.lang.NumberFormatException ex) {
			validInput = false;
		}

		// Perform date validation
		if (validInput) {
			if ((monthValue >= 1) && (monthValue <= 12)) {
				if (monthValue == 2) {
					if (!isLeapYear(year)) { // not a leap year
						if ((dayValue < 1) || (dayValue > 28)) {
							validInput = false;
						}
					}
					else {
						if ((dayValue < 1) || (dayValue > 29)) { // its a leap year
							validInput = false;
						}
					}
				}
				else if ((monthValue == 1) || (monthValue == 3) || (monthValue == 5) ||
						(monthValue == 7) || (monthValue == 8) || (monthValue == 10) ||
						(monthValue == 12)) {
					if ((dayValue < 1) || (dayValue > 31)) {
						validInput = false;
					}
				}
				else {
					if ((dayValue < 1) || (dayValue > 30)) {
						validInput = false;
					}
				}
			}
			else {
				validInput = false;
			}
		}

		return validInput;
	}
	/**
	 * Validates date input and test for leap year when year is a string (either yy or yyyy)
	 * Creation date: (1/27/02 8:36:53 PM)
	 * @param year year of the date
	 * @param month month of the date
	 * @param day day of the date
	 * @return boolean
	 */
	public static boolean isDateValid(String year, String month, String day) {

		int yearValue = Integer.parseInt(year.trim());

		return isDateValid(yearValue, month, day);

	}
	/**
	 * Validates form date input and test for leap year when year is a string (either yy or yyyy)
	 * Creation date: (1/27/02 8:36:53 PM)
	 * @return SortedSet a set of string constant that specify good or bad dates
	 * @param year year of the date
	 * @param month  month of the date
	 * @param day  day of the date
	 */
	public static SortedSet<String> isFormDateValid(String year, String month, String day) {

		boolean validInput = true;
		SortedSet<String> messageList = new TreeSet<String>();
		// synchronize the sorted list
		SortedSet<String> syncMessageList = Collections.synchronizedSortedSet(messageList);

		// we are assuming that the day is always good by adding it to the list the first time

		syncMessageList.add(DAY_GOOD);

		int yearValue = 1;
		int dayValue = 1;
		int monthValue = 1;

		try {
			if (year.length() == 4) {
				yearValue = Integer.parseInt(year);
				// year is valid, add to message list
				syncMessageList.add(YEAR_GOOD);
			}
			else {
				if (year.length() == 2) {
					if (Integer.parseInt(year.substring(0, year.length() - 1)) >= 0) {

						year = "20" + year;
						yearValue = Integer.parseInt(year);
						// year is valid, add to message list
						syncMessageList.add(YEAR_GOOD);
					}
				}
			}
			if (!(year.length() == 4 | year.length() == 2)) { // we don't want to do shot circuit OR here
				// year is not valid, add to message list
				syncMessageList.add(YEAR_BAD);
			}
		}
		catch (java.lang.NumberFormatException ex) {
			// year failed number format, add to list
			syncMessageList.add(YEAR_BAD);
		}
		try {

			monthValue = Integer.parseInt(month);
		}
		catch (java.lang.NumberFormatException ex) {
			// month failed number format, add to list
			syncMessageList.add(MONTH_BAD);
		}
		try {

			dayValue = Integer.parseInt(day);

		}
		catch (java.lang.NumberFormatException ex) {
			// day failed number format, add to list
			syncMessageList.remove(DAY_GOOD);
			syncMessageList.add(DAY_BAD);
		}

		// if year, month, and day failed number format then
		// return the list of bad dates

		if (syncMessageList.size() == 3) {
			return syncMessageList;
		}

		// Perform date validation

		if (validInput) {
			if ((monthValue >= 1) && (monthValue <= 12)) {
				// month is valid, add to list
				syncMessageList.add(MONTH_GOOD);
				if (monthValue == 2) {
					if (!isLeapYear(yearValue)) {

						if ((dayValue < 1) || (dayValue > 28)) { // not a leap year
							// day not valid, add to message list
							syncMessageList.remove(DAY_GOOD);
							// remove valid day and replace with invalid day
							syncMessageList.add(DAY_BAD);

						}
					}
					else {
						if ((dayValue < 1) || (dayValue > 29)) { // its a leap year
							// day is not valid, add to message list
							syncMessageList.remove(DAY_GOOD);
							syncMessageList.add(DAY_BAD);

						}
					}
				}
				else if ((monthValue == 1) || (monthValue == 3) ||
					(monthValue == 5) || (monthValue == 7) ||
					(monthValue == 8) || (monthValue == 10) || (monthValue == 12)) {
						if ((dayValue < 1) || (dayValue > 31)) {

							// day is invalid add to message list
							syncMessageList.remove(DAY_GOOD);
							syncMessageList.add(DAY_BAD);
						}
				}
				else {
					if ((dayValue < 1) || (dayValue > 30)) {

						// day is invalid, add to message list
						syncMessageList.remove(DAY_GOOD);
						syncMessageList.add(DAY_BAD);
					}
				}
			}
			else {
				// month is invalid, add to message list
				syncMessageList.add(MONTH_BAD);
				if ((dayValue < 1) || (dayValue > 31)) {
					syncMessageList.remove(DAY_GOOD);
					syncMessageList.add(DAY_BAD);
				}
			}
		}

		// if day is good and month is good and year is good, all validation is passed
		// we just need to return a syncmessagelist of zero length

		SortedSet<String> monthSet = syncMessageList.subSet(syncMessageList.first(), syncMessageList.last());
		Object middle = monthSet.last();

		if ((syncMessageList.first().toString().equals(DAY_GOOD))
			&& (syncMessageList.last().toString().equals(YEAR_GOOD))
			&& (middle.toString().equals(MONTH_GOOD))) {
			return syncMessageList = new TreeSet<String>();
		}

		return syncMessageList;
	}
	/**
	 * Test to see if a given year is a leap year or not
	 * @param year as integer value
	 * @return boolean - if it is a leap year or not
	 */
	public static boolean isLeapYear(int year) {
		return (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0));
	}
	
	/**
	 * Formats a date string to another date string format.
	 * Creation date: (1/12/02 11:48:52 AM)
	 * <p>
	 * For Example:
	 * </p>
	 * <pre>
	 * String inputDateFormat  = "yyyy-MM-dd HH:mm:ss";
	 * String outputDateFormat = "yyyy/MM/dd hh:mm:ss";
	 * String dateToFormat     = "2002-01-29 15:27:55";
	 * String result = DateUtility.formatDate(inputDateFormat,outputDateFormat,dateToFormat)
	 * returns 2002/01/29 03:27:55
	 * </pre>
	 * @param inputDateFormat input date format
	 * @param outputDateFormat desired output date format
	 * @param dateToFormat date string to format
	 * @return formmattedDate in the desired format
	 */
	public static String formatDate(String inputDateFormat, String outputDateFormat, String dateToFormat) {
		String formattedDate = null;
		SimpleDateFormat sdfInput = new SimpleDateFormat(inputDateFormat);
		SimpleDateFormat sdfOutput = new SimpleDateFormat(outputDateFormat);
		try {
			formattedDate = sdfOutput.format(sdfInput.parse(dateToFormat));
		} catch (ParseException pe) {
			// do nothing
		}

		return formattedDate;
	}
	/**
	 * Gets timestamp to insert in the database
	 * Creation date: (1/29/2002 12:15:27 PM)
	 *
	 * @return java.sql.Timestamp
	 */
	public static Timestamp timeStamp() {

		java.util.Date date = new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());

		return timestamp;
	}

	/**
	* Gets java.sql.Date when passed a date string.
	* Creation date: (3/4/2002 10:55:35)
	* @param date a date string
	* @return java.sql.Date
	*/
	public static java.sql.Date getSqlDate(String date) {
		return new java.sql.Date(getDate(date).getTime());
	}
	
    /**
     * Converts an Object to an java.sql.Date.
     * @param value the object to convert to date
     * @param dateFormat desired date format to convert to
     * @return date object as java.sql.Date
     * @exception ParseException if error occurs while parsing a date format
     */
    public static java.sql.Date toDate( Object value, String dateFormat  ) throws ParseException
    {
    	DateFormat format = new SimpleDateFormat(dateFormat);
        if( value == null ) return null;        
        if( value instanceof java.sql.Date ) return (java.sql.Date)value;
        if( value instanceof String )
        {
            if( "".equals( (String)value ) ) return null;
            return new java.sql.Date( format.parse( (String)value ).getTime() );
        }
                
        return new java.sql.Date( format.parse( value.toString() ).getTime() );
    }	
	
    /**
     * Converts an Object to a Timestamp.
     * @param value the object to convert
     * @param dateTimeFormat the desire date and time format
     * @return timestamp object in the date time format provided
     * @exception ParseException if error occurs while parsing a date format
     */
    public static java.sql.Timestamp toTimestamp( Object value, String dateTimeFormat ) throws ParseException
    {
    	DateFormat format = new SimpleDateFormat(dateTimeFormat);
        if( value == null ) return null;        
        if( value instanceof java.sql.Timestamp ) return (java.sql.Timestamp)value;
        if( value instanceof String )
        {
            if( "".equals( (String)value ) ) return null;
            return new java.sql.Timestamp( format.parse( (String)value ).getTime() );
        }
                
        return new java.sql.Timestamp( format.parse( value.toString() ).getTime() );
    }	
    
    /**
     * Converts an Object to a String using Dates
     * @param date the object to convert
     * @param format the desire date and time format
     * @return date a string format of the date
     */
    public static String toString( Object date, DateFormat format )
    {
        if( date == null ) return null;
        
        if( java.sql.Timestamp.class.isAssignableFrom( date.getClass() ) )
        {
            return format.format( date );
        }
        if( java.sql.Time.class.isAssignableFrom( date.getClass() ) )
        {
            return format.format( date );
        }
        if( java.sql.Date.class.isAssignableFrom( date.getClass() ) )
        {
            return format.format( date );
        }
        if( java.util.Date.class.isAssignableFrom( date.getClass() ) )
        {
            return format.format( date );
        }
        
        throw new IllegalArgumentException( "Unsupported type " + date.getClass() );
    } 
    
	/**
	 * Gets data as a string object.
	 * 
	 * @param timeStamp the timestamp object to convert to a string object
	 * @param format the date string format
	 * @return String date corresponding to the date object
	 */
    public String getDateAsString(Timestamp timeStamp, DateFormat format) {
    	format = new SimpleDateFormat(NORTH_AMERICA_DATE_FORMAT);
    	return DateUtility.toString(timeStamp, format);
    }
	
	/**
	 * Sets date as string.
	 * 
	 * @param date the string object to set
	 * @param timeStamp the timestamp object to set
	 * @throws DateException if error occurs while parsing the date object
	 */
	public void setDateAsString(String date, Timestamp timeStamp) throws DateException {
		try {
			timeStamp = DateUtility.toTimestamp(date, NORTH_AMERICA_DATE_FORMAT);

		} catch (ParseException e) {
			throw new DateException("Error occurred while parsing a date object", e);

		}

	}
	
	/**
	 * Gets date as String for displaying.
	 * 
	 * @param date the date object to convert as string
	 * @param uuidTime the UUID time to converted as a long primitive type
	 * @param uuid the UUID object to convert to date
	 * @return Date object as a string 
	 */
	public String getDateAsString(Date date, long uuidTime, UUID uuid) {
		uuidTime = UUID.randomUUID().timestamp();
		date = new Date(uuidTime);
		return DateUtility.formatDateFromUUID(NORTH_AMERICA_DATE_FORMAT, date);
	}
	
	/**
	 * Gets time from a UUID
	 * @param uuid
	 * @return a time stamp that has been converted to long expected by the Date object
	 */
	public static long getTimeFromUUID(UUID uuid) {
		return  (uuid.timestamp() - NUM_100NS_INTERVALS_SINCE_UUID_EPOCH) / 10000;
	}
	
	/**
	 * Converts time from timeuuid to a date object
	 * @param uuidString the string form of the timeuuid 
	 * @return Date object
	 */	
	public static Date convertTimeuuid(String uuidString) {
		UUID uuid = UUID.fromString(uuidString);
		long time = getTimeFromUUID(uuid);
		return new Date(time);
	}
	
	/**
	 * Converts time from timeuuid to a date object
	 * @param uuid the UUID to convert from 
	 * @return Date object
	 */	
	public static Date convertTimeuuid(UUID uuid) {
		long time = getTimeFromUUID(uuid);
		return new Date(time);
	}	
	    
}

