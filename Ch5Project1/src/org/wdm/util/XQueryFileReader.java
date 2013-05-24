package org.wdm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * XQueryFileReader reads a file containing XQuery statements.
 * @author Wing
 */
public class XQueryFileReader {

	/*
	 * Read file and put it to String
	 */
	public static String Read(String fileLocation)
	{
		String content = "";
		
		try {
			Scanner c = new Scanner(new File(fileLocation));
			content = c.useDelimiter("\\Z").next();
			c.close();
		} catch (IOException | NoSuchElementException e) {
			System.out.println("Error during XQueryFileReading");
			e.printStackTrace();
		}
		return content;
	}
}
