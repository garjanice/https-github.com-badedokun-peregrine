/**
 * 
 */
package com.depth1.grc.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import play.Logger;

/**
 * This class produces unique id that can be used to set id for classes that requires ID generator
 * The class is a singleton and cannot be instantiated
 * @author Bisi Adedokun
 *
 */
public class IdProducer {

	private static final IdProducer INSTANCE = new IdProducer(103);
	private AtomicLong atomicLong;
	private AtomicInteger atomicInteger;

	/**
	 * Private constructor that ensures the class cannot be instantiated.
	 * @param initialValue initial value to set
	 */
	private IdProducer(long initialValue) {
		this.atomicLong = new AtomicLong(initialValue);
	}
	
	/**
	 * Private constructor that ensures the class cannot be instantiated.
	 * @param initialValue initial value to set
	 */
	private IdProducer(int initialValue) {
		this.atomicInteger = new AtomicInteger(initialValue);
	}	

	/**
	 * Gets the only instance of the class.
	 * 
	 * @return an instance of the class with an initial value set to 103
	 */
	public static IdProducer getInstance() {
		return INSTANCE;
	}

	/**
	 * Gets the next value.
	 * 
	 * @return the next long value after it has been incremented by 1
	 */
	public long getNextId() {
		return atomicLong.getAndIncrement();
	}
	
	/**
	 * Gets the next value.
	 * 
	 * @return the next long value after it has been incremented by 1
	 */
	public int getNextIntId() {
		return atomicInteger.getAndIncrement();
	}	
	
	/**
	 * 
	 */
	private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());
	private static char Character;

	
	/**
	 * Generates a unique Id based on Atomic Reference, which is guaranteed to be unique across multiple JVMs.
	 * 
	 * @return next unique identifier
	 */
	public static Long nextId() {
		return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) -> next > prev ? next : prev + 1);
	}
	
	/**
	 * Generates a unique Id based on Atomic Reference, which is guaranteed to be unique across multiple JVMs.
	 * 
	 * @return next unique identifier
	 */
	public static Integer nextIntId() {
		return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) -> next > prev ? next : prev + 1).intValue();
	}	
	
	/**
	 * Generates a unique Id with a prefix. For example, a prefix could be P for Procedure or any other character
	 * @param prefix the prefix to prepend to the generated number
	 * @return an Id with a character prefix
	 */
	public static String nextStringId(String prefix) {
		if (prefix == null)
			return null;
		
		return prefix.substring(0,1).toUpperCase().concat(nextId().toString().substring(4, 13));
	}
	
	/**
	 * Returns a string form of a java.util.UUID
	 * @param uuid the UUID to convert to a string with no "-" in between
	 * @return a string with dashes removed
	 */
	public static String convertUUIDToString(UUID uuid) {
		return uuid.toString().replace("-", "");
	}
	
	/**
	 * Returns a java.util.UUID from a string UUID.
	 * @param uuid the string UUID to convert
	 * @return a java.util.UUID object
	 */
	public static UUID convertStringToUUID(String uuid) {
		uuid = uuid.substring(0, 8).concat("-").concat(uuid.substring(8, 12)).concat("-").concat(uuid.substring(12, 16))
				.concat("-").concat(uuid.substring(16, 20)).concat("-").concat(uuid.substring(20));
		return java.util.UUID.fromString(uuid);
	}

}
