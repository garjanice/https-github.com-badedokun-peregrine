/**
 * 
 */
package com.depth1.grc.util;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This class produces unique id that can be used to set id for classes that requires ID generator
 * The class is a singleton and cannot be instantiated
 * @author Bisi Adedokun
 *
 */
public class IdProducer {

	private static final IdProducer INSTANCE = new IdProducer(103);
	private AtomicLong atomicLong;

	/**
	 * Private constructor that ensures the class cannot be instantiated.
	 * @param initialValue initial value to set
	 */
	private IdProducer(long initialValue) {
		this.atomicLong = new AtomicLong(initialValue);
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
	 * 
	 */
	private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

	/**
	 * Generates a unique Id based on Atomic Reference, which is guaranteed to be unique across multiple JVMs.
	 * 
	 * @return next unique identifier
	 */
	public static Long nextId() {
		return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) -> next > prev ? next : prev + 1);
	}
	
	public static String nextStringId(String prefix) {
		return prefix.toUpperCase().concat(nextId().toString().substring(4, 13));
	}	

}
