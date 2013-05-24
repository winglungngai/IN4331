import java.util.ArrayList;

import org.wdm.util.XMLDatabaseConnector;

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
		System.out.println(uRequest.retrieveMovie());
	
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
	public ArrayList<String> retrieveMovie()
	{
		ArrayList<String> movies = new ArrayList<String>();
		
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
		MovieRetriever mRetriever = new MovieRetriever(xConnector);
		ArrayList<ArrayList<String>> movieBigSet = new ArrayList<ArrayList<String>>();
		
		if(searchByTitle)
			movieBigSet.add(mRetriever.retrieveByTitle(title));
		if(searchByGenre)
			movieBigSet.add(mRetriever.retrieveByGenre(genre));
		if(searchByDirectorName)
			movieBigSet.add(mRetriever.retrieveByDirectorName(directorName));
		if(searchByActorName)
			movieBigSet.add(mRetriever.retrieveByActorName(actorName));
		if(searchByTimeInterval)
			movieBigSet.add(mRetriever.retrieveByYear(earliestYear, latestYear));
		if(searchByKeyword)
			movieBigSet.add(mRetriever.retrieveByKeyword(keyword));
		
		if(movieBigSet.size() > 0)
		{
			movies = (ArrayList<String>) movieBigSet.get(0).clone();
			for(ArrayList<String> movieSet : movieBigSet)
			{
				movies.retainAll(movieSet);
			}
		}
		return movies;
	}

}
