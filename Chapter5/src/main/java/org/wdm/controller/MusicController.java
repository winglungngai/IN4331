package org.wdm.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wdm.project3.MusicXMLRetriever;
import org.wdm.util.XMLDatabaseConnector;

@Controller
public class MusicController {
	protected final Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "ajax/DownloadMusicXMLFile.xml", method = RequestMethod.GET)
	public HttpEntity<byte[]> handleMusicXMLRequest(
			@RequestParam(value = "fileName", required=false) String fileName) {
		
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
		MusicXMLRetriever mRetriever = new MusicXMLRetriever(xConnector);
		
		String xml = "";
		xml = mRetriever.retrieveByFileName(fileName);

		byte[] documentBody = xml.getBytes();

		HttpHeaders header = new HttpHeaders();
		header.add("Pragma", "cache");
		header.setContentType(new MediaType("application", "xml"));
		header.setContentDispositionFormData("attachment", "DownloadMusicXMLFile.xml");
		header.setContentLength(documentBody.length);
		return new HttpEntity<byte[]>(documentBody, header);
	}
	
	@RequestMapping(value = "ajax/SearchMusic", method = RequestMethod.POST)
	public HttpEntity<byte[]> handleMusicSearchRequest(
			@RequestParam(value = "creditWords") String creditWords) {
		
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
		MusicXMLRetriever mRetriever = new MusicXMLRetriever(xConnector);
		
		String xml = "";
		xml = mRetriever.retrieveByCreditWords(creditWords);

		byte[] documentBody = xml.getBytes();

		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "xml"));
		header.setContentLength(documentBody.length);
		return new HttpEntity<byte[]>(documentBody, header);
	}
	
	@RequestMapping(value = "ajax/SearchMusicByLyrics", method = RequestMethod.POST)
	public HttpEntity<byte[]> handleLyricsSearchRequest(
			@RequestParam(value = "lyrics") String lyrics) {
		
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
		MusicXMLRetriever mRetriever = new MusicXMLRetriever(xConnector);
		
		String xml = "";
		xml = mRetriever.retrieveByLyrics(lyrics);

		byte[] documentBody = xml.getBytes();

		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "xml"));
		header.setContentLength(documentBody.length);
		return new HttpEntity<byte[]>(documentBody, header);
	}
	
	@RequestMapping(value = "ajax/SearchMusicByNotes", method = RequestMethod.POST)
	public HttpEntity<byte[]> handleNotesSearchRequest(
			@RequestParam(value = "notes") String notes) {
		
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
		MusicXMLRetriever mRetriever = new MusicXMLRetriever(xConnector);
		notes = notes.replaceAll(" ", "");
		String xml = "";
		xml = mRetriever.retrieveByNotes(notes);

		byte[] documentBody = xml.getBytes();

		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "xml"));
		header.setContentLength(documentBody.length);
		return new HttpEntity<byte[]>(documentBody, header);
	}
}