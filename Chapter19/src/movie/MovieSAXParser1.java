package movie;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entity.Actor;
import entity.Movie;

/**
 * SaxParser for parsing xml data to title-and-actor.txt format
 */
public class MovieSAXParser1 extends DefaultHandler{

	ArrayList<Movie> movies;
	private Movie movie;
	
	private ArrayList<Actor> actors;
	private Actor actor;
	
	private String tempTextString;
	private String tempFirstName;
	private String tempLastname;
	private String tempRole;
	private String tempBirthYear;
	/**
	 * SaxParser for parsing xml data to title-and-actor.txt format
	 */
	public MovieSAXParser1(){
		movies = new ArrayList<Movie>();
		actors = new ArrayList<Actor>();
	}
		
	/**
	 * Parse the input to an array list of Movie object.
	 */
	public ArrayList<Movie> parse(InputStream is) {
		parseDocument(is);
		return movies;
	}

	/**
	 * Parse the input to local(private) java object.
	 */
	private void parseDocument(InputStream is) {
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			
			SAXParser sp = spf.newSAXParser();
			sp.parse(is, this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * Iterate through the list and print
	 * the contents
	 */
	private void printData(){
		
		System.out.println("No of Movies '" + movies.size() + "'.");
	
		Iterator<Movie> it = movies.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	

	/**
	 * Start Event Handlers
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		tempTextString = "";
		
		if(qName.equalsIgnoreCase("movie")) {	
			movie = new Movie();
		}
		else if (qName.equalsIgnoreCase("actor")) {
			actor = new Actor();
		}
	}
	
	/**
	 * Character Event Handlers
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempTextString = (new String(ch,start,length)).trim();
	}
	
	/**
	 * End Event.
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("movie")) {
			movie.setActors(actors);
			movies.add(movie);
		}else if (qName.equalsIgnoreCase("title")) {
			movie.setMovieTitle(tempTextString);
		}else if (qName.equalsIgnoreCase("year")) {
			movie.setYear(tempTextString);
		}
		else if (qName.equalsIgnoreCase("actor")) {
			actor.setFirstName(tempFirstName);
			actor.setLastName(tempLastname);
			actor.setRole(tempRole);
			actor.setBirth_year(tempBirthYear);
			actors.add(actor);
		}
		else if (qName.equalsIgnoreCase("first_name")) {
			tempFirstName = tempTextString;
		}
		else if (qName.equalsIgnoreCase("last_name")) {
			tempLastname = tempTextString;
		}
		else if (qName.equalsIgnoreCase("role")) {
			tempRole = tempTextString;
		}
		else if (qName.equalsIgnoreCase("birth_date")) {
			tempBirthYear = tempTextString;
		}		
	}
	
	public static void main(String[] args){
		MovieSAXParser1 spe = new MovieSAXParser1();
		
		try {
			File file = new File("movies.xml");
			String content;
			content = FileUtils.readFileToString(file);
			InputStream is = IOUtils.toInputStream(content);
			System.out.println(spe.parse(is));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}




