package org.wdm.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wdm.project1.UserRequest;
 
@Controller
public class HelloController {
    protected final Logger logger = Logger.getLogger(getClass());
 
    @RequestMapping(value="/asyncCall", method=RequestMethod.GET)
    public @ResponseBody List<String> handleGetRequest() {
    	
    	UserRequest uRequest = new UserRequest();
		uRequest.setActorName("Scarlett");
		uRequest.setTimeInterval(2002, 2006);
		uRequest.setTitle("Translation");
        
        return uRequest.retrieveMovie();
    }
    
    @RequestMapping(value="/asyncCall", method=RequestMethod.POST )
    public @ResponseBody List<String> handlePostRequest(
    		@RequestParam(value="title") String movieTitle, @RequestParam(value="genre") String genre) {
    	UserRequest uRequest = new UserRequest();
		uRequest.setTitle(movieTitle);
		uRequest.setGenre(genre);
        
        return uRequest.retrieveMovie();
    }
}