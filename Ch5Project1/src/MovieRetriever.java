import java.util.ArrayList;

import org.wdm.util.XMLDatabaseConnector;
import org.wdm.util.XQueryFileReader;


/**
 * MovieRetriever contains a list of methods to retrieve movies according to different criteria.
 * @author Wing
 *
 */
public class MovieRetriever {

	protected XMLDatabaseConnector xConnector;
	
	/**
	 * Test
	 */
	public static void main(String[] args) throws Exception {
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();

		MovieRetriever mRetriever = new MovieRetriever(xConnector);
		ArrayList<String> movies;
		movies = mRetriever.retrieveByTitle("Heat");
		System.out.println(movies.toString());
		
		movies = mRetriever.retrieveByGenre("Crime");
		System.out.println(movies.toString());
		
		movies = mRetriever.retrieveByActorName("Scarlett J");
		System.out.println(movies.toString());
		
		movies = mRetriever.retrieveByDirectorName("Clint");
		System.out.println(movies.toString());
		
		movies = mRetriever.retrieveByKeyword("love");
		System.out.println(movies.toString());
		
		
	}
	
	/*
	 * Retrieve by title.
	 */
	public ArrayList<String> retrieveByTitle(String title)
	{
		String collectionPath = "/db/movies";
		
		String xQuery = XQueryFileReader.Read("queries/retrieveByTitle.txt");
		xQuery = xQuery.replace("#title", title);
		
		return xConnector.read(collectionPath, xQuery);
	}
	
	/*
	 * Retrieve by genre.
	 */
	public ArrayList<String> retrieveByGenre(String genre)
	{
		String collectionPath = "/db/movies";
		
		String xQuery = XQueryFileReader.Read("queries/retrieveByGenre.txt");
		xQuery = xQuery.replace("#genre", genre);
		
		return xConnector.read(collectionPath, xQuery);
	}

	
	/*
	 * Retrieve by director name.
	 */
	public ArrayList<String> retrieveByDirectorName(String directorName)
	{
		String collectionPath = "/db/movies";
		
		String xQuery = XQueryFileReader.Read("queries/retrieveByDirectorName.txt");
		xQuery = xQuery.replace("#directorName", directorName);
		
		return xConnector.read(collectionPath, xQuery);
	}

	/*
	 * Retrieve by actor name.
	 */
	public ArrayList<String> retrieveByActorName(String actorName)
	{
		String collectionPath = "/db/movies";
		
		String xQuery = XQueryFileReader.Read("queries/retrieveByActorName.txt");
		xQuery = xQuery.replace("#actorName", actorName);
		
		return xConnector.read(collectionPath, xQuery);
	}
	
	/*
	 * Retrieve by time interval in years.
	 */
	public ArrayList<String> retrieveByYear(int earliest, int latest)
	{
		String collectionPath = "/db/movies";
		
		String xQuery = XQueryFileReader.Read("queries/retrieveByYear.txt");
		xQuery = xQuery.replace("#earliest", Integer.valueOf(earliest).toString());
		xQuery = xQuery.replace("#latest", Integer.valueOf(latest).toString());
		
		return xConnector.read(collectionPath, xQuery);
	}
	
	/*
	 * Retrieve by time interval in years.
	 */
	public ArrayList<String> retrieveByKeyword(String keyword)
	{
		String collectionPath = "/db/movies";
		
		String xQuery = XQueryFileReader.Read("queries/retrieveByKeyword.txt");
		xQuery = xQuery.replace("#keyword", keyword);
		
		return xConnector.read(collectionPath, xQuery);
	}
	
	
//	public ArrayList<Integer> retrieveByYear(int earliest, int latest)
//	{
//		
//	}
	
	/**
	 * MovieRetriever contains a list of methods to retrieve movies according to different criteria.
	 */
	public MovieRetriever(XMLDatabaseConnector xConnector)
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
