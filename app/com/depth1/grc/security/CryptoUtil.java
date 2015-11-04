/**
 * 
 */
package com.depth1.grc.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class provides cryptographic utility methods for encoding and decoding values
 * 
 * @author Bisi Adedokun
 *
 */
public class CryptoUtil {
	
	
    /**
     * @param value Element to get its SHA-512
     * @return Corresponding SHA-512 message digest of the value
     */
    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
