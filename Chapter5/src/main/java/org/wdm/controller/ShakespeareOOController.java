package org.wdm.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wdm.project1.UserRequest;
import org.wdm.project2.PlayRetriever;
 
@Controller
public class ShakespeareOOController {
    protected final Logger logger = Logger.getLogger(getClass());
 
    @RequestMapping(value="ajax/listPlays", method=RequestMethod.GET)
    public HttpEntity<byte[]> handleGetRequest() {
    	
    	PlayRetriever pRetriever = new PlayRetriever();
    	byte[] documentBody = pRetriever.listPlays().getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
    }
    
    @RequestMapping(value="ajax/listActsByPlay", method=RequestMethod.GET)
    public HttpEntity<byte[]> listActs(@RequestParam(value="play", required=true) String play) {
    	
    	PlayRetriever pRetriever = new PlayRetriever();
    	byte[] documentBody = pRetriever.listActsByPlay(play).getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
    }
    
    @RequestMapping(value="ajax/listScenesByPlay", method=RequestMethod.GET)
    public HttpEntity<byte[]> listScenes(@RequestParam(value="play", required=true) String play) {
    	
    	PlayRetriever pRetriever = new PlayRetriever();
    	byte[] documentBody = pRetriever.listScenesByPlay(play).getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
    }
    
    @RequestMapping(value="ajax/listScenesByPlayAct", method=RequestMethod.GET)
    public HttpEntity<byte[]> listScenesByAct(@RequestParam(value="play", required=true) String play, @RequestParam(value="act", required=true) String act) {
    	
    	PlayRetriever pRetriever = new PlayRetriever();
    	byte[] documentBody = pRetriever.listScenesByPlayAct(play, act).getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
    }
    
    @RequestMapping(value="ajax/listChatactersByPlay", method=RequestMethod.GET)
    public HttpEntity<byte[]> listCharacters(@RequestParam(value="play", required=true) String play) {
    	
    	PlayRetriever pRetriever = new PlayRetriever();
    	byte[] documentBody = pRetriever.listCharactersByPlay(play).getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
    }
    
    @RequestMapping(value="ajax/getPlaySumary", method=RequestMethod.GET)
    public HttpEntity<byte[]> getPlaySumary(@RequestParam(value="play", required=true) String play) {
    	
    	PlayRetriever pRetriever = new PlayRetriever();
    	byte[] documentBody = pRetriever.getPlaySumary(play).getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
    }
    
    @RequestMapping(value="ajax/getPlayDetails", method=RequestMethod.GET)
    public HttpEntity<byte[]> getPlayDetails(@RequestParam(value="play", required=true) String play) {
    	
    	PlayRetriever pRetriever = new PlayRetriever();
    	byte[] documentBody = pRetriever.getPlayDetails(play).getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
    }
    
    @RequestMapping(value="ajax/getPartByCharacter", method=RequestMethod.GET)
    public HttpEntity<byte[]> listCharacters(@RequestParam(value="play", required=true) String play, @RequestParam(value="act") String act, @RequestParam(value="scene") String scene, @RequestParam(value="character", required=true) String speaker) {
    	
    	PlayRetriever pRetriever = new PlayRetriever();
    	byte[] documentBody = pRetriever.getPartByCharacterActScene(play, act, scene, speaker).getBytes();
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
    }
    
    @RequestMapping(value="ajax/getSceneByPlayActTitle", method=RequestMethod.GET)
    public HttpEntity<byte[]> getSceneByActTitle(@RequestParam(value="play", required=true) String play, @RequestParam(value="act") String act, @RequestParam(value="scene") String scene) {
    	
    	PlayRetriever pRetriever = new PlayRetriever();
    	byte[] documentBody = pRetriever.getSceneByPlayActTitle(play, act, scene).getBytes();
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
    }
    
    @RequestMapping(value="/asyncCalll", method=RequestMethod.POST )
    public HttpEntity<byte[]> handlePostRequest(
    		@RequestParam(value="title") String movieTitle, @RequestParam(value="genre") String genre) {
    	UserRequest uRequest = new UserRequest();
		uRequest.setTitle(movieTitle);
		uRequest.setGenre(genre);
		
		//String xml = uRequest.retrieveMovie().get(0);
		String xml="<movie></movie>";
	    byte[] documentBody = xml.getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
        
    }
}