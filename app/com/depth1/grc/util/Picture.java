/**
 * 
 */
package com.depth1.grc.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import play.Logger;

/**
 * This class provides methods to read and write picture to the file system 
 * @author Bisi Adedokun
 *
 */
public class Picture {
	
	FileInputStream fis = null;
	
	
	/**
	 * Loads picture from the file system.
	 * 
	 * @param pictureUrl Absolute path or URL of the location of the picture
	 * @return buffer containing the picture to be saved
	 * @throws IOException if errors occur while loading the picture from the file system
	 */
	
	public byte[] loadPicture(String pictureUrl) throws IOException {		
		fis = new FileInputStream(pictureUrl);
		byte[] picture = new byte[8192];
		try {
			picture = new byte[fis.available() + 1];
			fis.read(picture);
		} catch (IOException e) {
			Logger.error("Problem retrieving picture from the file system location", e);
		}
		if (fis == null) {
			fis.close();
		}

		return picture;
	}
	

	
	/**
	 * Writes images to a given location.
	 * 
	 * @param image The picture to write to the file system
	 * @param pictureUrl The URL to write the image to
	 */
	public void writePicture(byte[] image, String pictureUrl) {
		InputStream is = new ByteArrayInputStream(image);
		BufferedInputStream input = new BufferedInputStream(is);
		byte[] buffer = new byte[8192];
		int length;
		File file = new File(pictureUrl);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			// Writes bytes from the specified byte array to the file output stream
			for (length = 0; (length = input.read(buffer)) > 0;) {
				fos.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			Logger.error("File not found" + e);
		} catch (IOException ioe) {
			Logger.error("Error occurred while writing file to  " + pictureUrl + ioe);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ioe) {
				Logger.error("Error while closing stream: " + ioe);
			}

		}
	}
	
	
	/**
	 * Retrieves picture URL.
	 * 
	 * @param email User email to use to retrieve the picture
	 * @param extension file extension for the URL
	 * @return URL path to picture to retrieve
	 */
	public String getPictureFileURL(String email, String extension) {
		String fileName = email.replaceAll("[@.]", "_");
		String fileNameWithExtension = fileName.concat(".").concat(extension);
		return new File("public/images", fileNameWithExtension).getPath();
	}	

}
