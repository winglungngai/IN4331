package org.wdm.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wdm.project1.UserRequest;
 
@Controller
public class HelloController {
    protected final Logger logger = Logger.getLogger(getClass());
 
    @RequestMapping("/startMapping")
    public @ResponseBody List<String> handleRequest() {
    	
    	UserRequest uRequest = new UserRequest();
		uRequest.setActorName("Scarlett");
		uRequest.setTimeInterval(2002, 2006);
		uRequest.setTitle("Translation");
        
        return uRequest.retrieveMovie();
    }
}