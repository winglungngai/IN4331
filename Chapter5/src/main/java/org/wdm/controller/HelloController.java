package org.wdm.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wdm.project1.UserRequest;
 
@Controller
public class HelloController {
    protected final Logger logger = Logger.getLogger(getClass());
 
    @RequestMapping(value="/ajax/asyncCall", method=RequestMethod.GET)
    public @ResponseBody List<String> handleGetRequest() {
    	
    	UserRequest uRequest = new UserRequest();
		uRequest.setActorName("Scarlett");
		uRequest.setTimeInterval(2002, 2006);
		uRequest.setTitle("Translation");
        
        return uRequest.retrieveMovie();
    }
    
    @RequestMapping(value="/ajax/asyncCall", method=RequestMethod.POST )
    public HttpEntity<byte[]> handlePostRequest(
    		@RequestParam(value="title") String movieTitle, @RequestParam(value="genre") String genre) {
    	UserRequest uRequest = new UserRequest();
		uRequest.setTitle(movieTitle);
		uRequest.setGenre(genre);
		
		String xml = uRequest.retrieveMovie().get(0);

	    byte[] documentBody = xml.getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
        
    }
}