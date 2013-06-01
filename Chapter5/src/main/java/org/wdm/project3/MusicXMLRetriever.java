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
		xQuery = xQuery.replace("#creditWords", creditWords);
		 
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
