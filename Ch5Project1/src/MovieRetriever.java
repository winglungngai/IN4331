import java.util.ArrayList;

import org.wdm.util.XMLDatabaseConnector;
import org.wdm.util.XQueryFileReader;


/**
 * MovieRetriever contains a list of methods to retrieve movies according to different criteria.
 */
public class MovieRetriever {

	protected XMLDatabaseConnector xConnector;
	
	/**
	 * Test
	 */
	public static void main(String[] args) throws Exception {
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();

		MovieRetriever mRetriever = new MovieRetriever(xConnector);
		ArrayList<String> movies = mRetriever.retrieveByYear(1999, 2005);
		System.out.println(movies.toString());
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
