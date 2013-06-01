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
	
	public String listPlays(){
		String xQuery = XQueryFileReader.Read("queries/project2/listPlays.txt");
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String listCharactersByPlay(String play){
		String xQuery = XQueryFileReader.Read("queries/project2/listCharactersByPlay.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append("<PERSONA>").append(result).append("</PERSONA>");
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String listActsByPlay(String play){
		String xQuery = XQueryFileReader.Read("queries/project2/listActsByPlay.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append("<ACT>").append(result).append("</ACT>");
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String listScenesByPlayAct(String play, String act){
		String xQuery = XQueryFileReader.Read("queries/project2/listScenesByPlayAct.txt");
		xQuery = xQuery.replace("#play", play);
		xQuery = xQuery.replace("#act", act);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String listScenesByPlay(String play){
		String xQuery = XQueryFileReader.Read("queries/project2/listScenesByPlay.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String getPlaySumary(String play){
		String xQuery = XQueryFileReader.Read("queries/project2/getPlaySummary.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer("<items>");
		sb.append("<TITLE>").append(play).append("</TITLE>");
		for(String result:results) sb.append(result);
		sb.append("</items>");
		return  sb.toString();
	}
	
	public String getPlayDetails(String play){
		String xQuery = XQueryFileReader.Read("queries/project2/getPlayDetails.txt");
		xQuery = xQuery.replace("#play", play);
		List<String> results = xConnector.read(COLLECTION_PATH, xQuery);
		StringBuffer sb = new StringBuffer();
		for(String result:results) sb.append(result);
		return  sb.toString();
	}
	
	public String getPartByCharacterActScene(String play, String act, String scene, String speaker){
		if(play==null && speaker==null || play.trim().length()==0 || speaker.trim().length()==0) throw new IllegalArgumentException("play and speaker parameters cannot be null or empty.");
		if(act==null) act="";
		if(scene==null) scene="";
		
		String xQuery = XQueryFileReader.Read("queries/project2/getPartByCharacterActScene.txt");
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
		String xQuery = XQueryFileReader.Read("queries/project2/getSceneByPlayActTitle.txt");
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
