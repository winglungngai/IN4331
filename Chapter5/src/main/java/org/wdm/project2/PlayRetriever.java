package org.wdm.project2;
import java.util.List;

import org.wdm.util.XMLDatabaseConnector;
import org.wdm.util.XQueryFileReader;


/**
 * MovieRetriever contains a list of methods to retrieve movies according to different criteria.
 * @author Wing
 *
 */
public class PlayRetriever {

	protected XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
	private static final String COLLECTION_PATH = "/db/shakespeare";
	
	/**
	 * Test
	 */
	public static void main(String[] args) throws Exception {
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();

		PlayRetriever pRetriever = new PlayRetriever();
		//ArrayList<String> movies;
		//movies = pRetriever.retrieveByTitle("Heat");
		//System.out.println(movies.toString());
		/*
		movies = mRetriever.retrieveByGenre("Crime");
		System.out.println(movies.toString());
		
		movies = mRetriever.retrieveByActorName("Scarlett J");
		System.out.println(movies.toString());
		
		movies = mRetriever.retrieveByDirectorName("Clint");
		System.out.println(movies.toString());
		
		movies = mRetriever.retrieveByKeyword("love");
		System.out.println(movies.toString());*/
		String play = "The Tragedy of Hamlet, Prince of Denmark";
		String act = "ACT III";
		String scene = "SCENE I.  A room in the castle.";//"SCENE I.  Elsinore. A platform before the castle.";
		String speaker = "KING CLAUDIUS";
		//System.out.println(pRetriever.getPartByCharacterActScene(play, act, scene, speaker));
		//System.out.println(pRetriever.listPlays());
		//System.out.println(pRetriever.listCharactersByPlay("The Tragedy of Hamlet, Prince of Denmark"));
		//System.out.println(pRetriever.listActsByPlay("The Tragedy of Hamlet, Prince of Denmark"));
		System.out.println(pRetriever.listScenesByPlayAct("The Tragedy of Hamlet, Prince of Denmark", ""));
		//System.out.println(pRetriever.listActs("hamlet.xml"));
		
	}
	
	/*
	 * Retrieve by title.
	 */
	public String listPlays(){
		String xQuery = XQueryFileReader.Read("queries/shakespeare/listPlays.txt");
		//xQuery = xQuery.replace("#title", title);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String listCharactersByPlay(String play){
		String xQuery = XQueryFileReader.Read("queries/shakespeare/listCharactersByPlay.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append("<PERSONA>").append(result).append("</PERSONA>");
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String listActsByPlay(String play){
		String xQuery = XQueryFileReader.Read("queries/shakespeare/listActsByPlay.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append("<ACT>").append(result).append("</ACT>");
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String listScenesByPlayAct(String play, String act){
		String xQuery = XQueryFileReader.Read("queries/shakespeare/listScenesByPlayAct.txt");
		xQuery = xQuery.replace("#play", play);
		xQuery = xQuery.replace("#act", act);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String listScenesByPlay(String play){
		String xQuery = XQueryFileReader.Read("queries/shakespeare/listScenesByPlay.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String getPlaySumary(String play){
		String xQuery = XQueryFileReader.Read("queries/shakespeare/getPlaySummary.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		sb.append("<TITLE>").append(play).append("</TITLE>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String getPlayDetails(String play){
		String xQuery = XQueryFileReader.Read("queries/shakespeare/getPlayDetails.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer();
		for(String result:results) sb.append(result);
		return  sb.toString();
	}
	
	public String getPartByCharacterActScene(String play, String act, String scene, String speaker){
		if(play==null && speaker==null || play.trim().length()==0 || speaker.trim().length()==0) throw new IllegalArgumentException("play and speaker parameters cannot be null or empty.");
		//if((act==null && scene==null) || (act.trim().length()==0 && scene.trim().length()==0)) throw new IllegalArgumentException("act and scene parameters cannot both be null or empty.");
		if(act==null) act="";
		if(scene==null) scene="";
		
		String xQuery = XQueryFileReader.Read("queries/shakespeare/getPartByCharacterActScene.txt");
		xQuery = xQuery.replace("#play", play);
		xQuery = xQuery.replace("#act", act);
		xQuery = xQuery.replace("#scene", scene);
		xQuery = xQuery.replace("#speaker", speaker);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		sb.append("<PLAY>").append(play).append("</PLAY>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String getSceneByPlayActTitle(String play, String act, String scene){
		String xQuery = XQueryFileReader.Read("queries/shakespeare/getSceneByPlayActTitle.txt");
		xQuery = xQuery.replace("#play", play);
		xQuery = xQuery.replace("#act", act);
		xQuery = xQuery.replace("#scene", scene);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		sb.append("<PLAY>").append(play).append("</PLAY>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public XMLDatabaseConnector getxConnector() {
		return xConnector;
	}

	public void setxConnector(XMLDatabaseConnector xConnector) {
		this.xConnector = xConnector;
	}
	


	
}
