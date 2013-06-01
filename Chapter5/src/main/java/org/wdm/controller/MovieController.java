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

@Controller
public class MovieController {
	protected final Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "ajax/SearchMovie", method = RequestMethod.POST)
	public HttpEntity<byte[]> handleMovieLRequest(
			@RequestParam(value = "title", required=false) String title,
			@RequestParam(value = "genre", required=false) String genre,
			@RequestParam(value = "directorName", required=false) String directorName,
			@RequestParam(value = "actorName", required=false) String actorName,
			@RequestParam(value = "earliestYear", required=false) String earliestYear,
			@RequestParam(value = "latestYear", required=false) String latestYear,
			@RequestParam(value = "keyword", required=false) String keyword) {
		UserRequest uRequest = new UserRequest();
		if(title != null && title.length() > 0) { uRequest.setTitle(title);	}
		if(genre != null && genre.length() > 0) { uRequest.setGenre(genre); }
		if(directorName != null && directorName.length() > 0) { uRequest.setDirectorName(directorName); }
		if(actorName != null && actorName.length() > 0) { uRequest.setActorName(actorName); }
		if(earliestYear != null && isNumeric(earliestYear) && latestYear != null && isNumeric(latestYear) ) 
		{ uRequest.setTimeInterval(Integer.parseInt(earliestYear), Integer.parseInt(latestYear)); }
		if(keyword != null && keyword.length() > 0) { uRequest.setKeyword(keyword); }
		
		String xml = "";
		if(uRequest.isRequested()) { xml = uRequest.retrieveMovies(); }

		byte[] documentBody = xml.getBytes();

		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "xml"));
		header.setContentLength(documentBody.length);
		return new HttpEntity<byte[]>(documentBody, header);

	}	
	
	private boolean isNumeric(String str)
	{
		try{
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
				
		/*
	  if(str.length() == 0 ) return false;
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();*/
	}
}