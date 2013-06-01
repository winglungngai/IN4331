package org.wdm.project1;
import java.util.ArrayList;

import org.wdm.util.XMLDatabaseConnector;
import org.wdm.util.XQueryFileReader;

/**
 * UserRequest specifies the user's search criteria and uses those to search in the XMLDatabase.
 * @author Wing
 *
 */
public class UserRequest {
	
	protected boolean searchByTitle;
	protected boolean searchByGenre;
	protected boolean searchByDirectorName;
	protected boolean searchByActorName;
	protected boolean searchByTimeInterval;
	protected boolean searchByKeyword;
	
	protected String title;
	protected String genre;
	protected String directorName;
	protected String actorName;
	protected int earliestYear;
	protected int latestYear;
	protected String keyword;
	
	/**
	 * Test
	 */
	public static void main(String[] args) throws Exception {
		UserRequest uRequest = new UserRequest();
		uRequest.setActorName("Scarlett");
		uRequest.setTimeInterval(2002, 2006);
		uRequest.setTitle("Translation");
		System.out.println(uRequest.retrieveMovies());
	
	}
	
	public void setTitle(String title)
	{
		this.title = title;
		searchByTitle = true;
	}
	
	public void setGenre(String genre)
	{
		this.genre = genre;
		searchByGenre = true;
	}
	
	public void setDirectorName(String directorName)
	{
		this.directorName = directorName;
		searchByDirectorName = true;
	}

	public void setActorName(String actorName)
	{
		this.actorName = actorName;
		searchByActorName = true;
	}
	
	public void setTimeInterval(int earliestYear, int latestYear)
	{
		this.earliestYear = earliestYear;
		this.latestYear = latestYear;
		searchByTimeInterval = true;
	}
	
	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
		searchByKeyword = true;
	}
	
	/**
	 * Retrieve movies according to the specified criteria.
	 */
//	public String retrieveMovie()
//	{
//		String xmlString = "<movies>";
//		ArrayList<String> movieIds = retrieveMovieIds();
//		
//		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
//		MovieRetriever mRetriever = new MovieRetriever(xConnector);
//		
//		for(String movieId : movieIds)
//		{
//			String movie = mRetriever.retrieveById(movieId);
//			if(movie != null)
//			{
//				xmlString += movie;
//			}
//		}
//		
//		xmlString += "</movies>";
//		return xmlString;
//	}
	
	/**
	 * Retrieve movies according to the specified criteria.
	 */
	@SuppressWarnings("unchecked")
	public String retrieveMovies()
	{
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
		String collectionPath = "/db/movies";
		String xQuery = XQueryFileReader.Read("queries/retrieveMovies.txt");
		
		xQuery = (searchByGenre) ?  xQuery.replace("#genre", genre) : xQuery.replace("#genre", "");
		xQuery = (searchByActorName) ?  xQuery.replace("#actorName", actorName) : xQuery.replace("#actorName", "");
		xQuery = (searchByDirectorName) ?  xQuery.replace("#directorName", directorName) : xQuery.replace("#directorName", "");
		xQuery = (searchByKeyword) ?  xQuery.replace("#keyword", keyword) : xQuery.replace("#keyword", "");
		xQuery = (searchByTitle) ?  xQuery.replace("#title", title) : xQuery.replace("#title", "");
		xQuery = (searchByTimeInterval) ?  xQuery.replace("#earliest", Integer.valueOf(earliestYear).toString()) : xQuery.replace("#earliest", "");
		xQuery = (searchByTimeInterval) ?  xQuery.replace("#latest", Integer.valueOf(latestYear).toString()) : xQuery.replace("#latest", "");
		
		ArrayList<String> results = xConnector.read(collectionPath, xQuery);
		StringBuffer sb = new StringBuffer("<movies>");
		for(String result:results) sb.append(result);
		sb.append("</movies>");
		return sb.toString();
	}
	
	public boolean isRequested()
	{
		return (searchByTitle | searchByGenre | searchByDirectorName | searchByActorName | searchByTimeInterval | searchByKeyword) ;
	}
	
	public String toSingleXMLString(ArrayList<String> xmlEntries)
	{
		String xmlString = "";
		for(String xmlEntry : xmlEntries)
		{
			xmlString += xmlEntry;
		}
		return xmlString;
	}

}
