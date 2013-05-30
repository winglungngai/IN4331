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