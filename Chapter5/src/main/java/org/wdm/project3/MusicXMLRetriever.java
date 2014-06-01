package org.wdm.project3;
import java.util.ArrayList;

import org.wdm.util.XMLDatabaseConnector;
import org.wdm.util.XQueryFileReader;


/**
 * MovieRetriever contains a list of methods to retrieve movies according to different criteria.
 * @author Wing
 *
 */
public class MusicXMLRetriever {

	protected XMLDatabaseConnector xConnector;
	
	/**
	 * Test
	 */
	public static void main(String[] args) throws Exception {
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();

		MusicXMLRetriever mRetriever = new MusicXMLRetriever(xConnector);
		String movies;
		movies = mRetriever.retrieveByFileName("Heat");
		System.out.println(movies.toString());
		
	}

	public String retrieveByFileName(String fileName)
	{
		String collectionPath = "/db/music";
		fileName=fileName.substring(fileName.lastIndexOf("/")+1);
		String xQuery = XQueryFileReader.Read("queries/project3/retrieveByFileName.txt");
		xQuery = xQuery.replace("#fileName", fileName);
		 
		ArrayList<String> moviesWithSameId = xConnector.read(collectionPath, xQuery);
		if(moviesWithSameId.size() > 0)
		{
			return moviesWithSameId.get(0);
		}
		else
		{
			return null;
		}
		
	}
	
	/*
	 * Retrieve songs information by credit words
	 */
	public String retrieveByCreditWords(String creditWords)
	{
		String collectionPath = "/db/music";
		
		String xQuery = XQueryFileReader.Read("queries/project3/retrieveByCreditWords.txt");
		xQuery = xQuery.replace("#creditWords", toXQuerySequence(creditWords));
		 
		ArrayList<String> moviesWithSameId = xConnector.read(collectionPath, xQuery);
		if(moviesWithSameId.size() > 0)
		{
			return moviesWithSameId.get(0);
		}
		else
		{
			return null;
		}
	}
	
	/*
	 * Retrieve songs information by lyrics
	 */
	public String retrieveByLyrics(String lyrics)
	{
		String collectionPath = "/db/music";
		
		String xQuery = XQueryFileReader.Read("queries/project3/retrieveByLyrics.txt");
		
		String userInput = toXQuerySequence(lyrics);
		xQuery = xQuery.replace("#userInput", userInput);
		 
		ArrayList<String> moviesWithSameId = xConnector.read(collectionPath, xQuery);
		if(moviesWithSameId.size() > 0)
		{
			return moviesWithSameId.get(0);
		}
		else
		{
			return null;
		}
	}
	
	/*
	 * Retrieve songs information by lyrics
	 */
	public String retrieveByNotes(String notes)
	{
		String collectionPath = "/db/music";
		
		String xQuery = XQueryFileReader.Read("queries/project3/retrieveByNotes.txt");
		notes.replaceAll(" ", "");
		xQuery = xQuery.replace("#userInput", notes);
		 
		ArrayList<String> moviesWithSameId = xConnector.read(collectionPath, xQuery);
		if(moviesWithSameId.size() > 0)
		{
			return moviesWithSameId.get(0);
		}
		else
		{
			return null;
		}
	}
	
	private String toXQuerySequence(String input)
	{
		String[] inputArray = input.split("\\s+");
		
		String userInput = "(";
		
		if(inputArray.length > 0)
		{
			userInput += "'" + inputArray[0] + "'";
		}
		
		if(inputArray.length > 0)
		{
			for(int i = 1; i<inputArray.length; i++)
			{
				userInput += ", '" + inputArray[i] + "'";
			}
		}
		
		userInput += ")";	
		
		return userInput;
	}
	
	
	/**
	 * MovieRetriever contains a list of methods to retrieve movies according to different criteria.
	 */
	public MusicXMLRetriever(XMLDatabaseConnector xConnector)
	{
		setxConnector(xConnector);
	}
	
	public XMLDatabaseConnector getxConnector() {
		return xConnector;
	}

	public void setxConnector(XMLDatabaseConnector xConnector) {
		this.xConnector = xConnector;
	}
	


	
}
