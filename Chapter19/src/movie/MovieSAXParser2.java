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

import entity.Director;
import entity.Movie;

/**
 * SaxParser for parsing xml data to director-and-title.txt format
 */
public class MovieSAXParser2 extends DefaultHandler{

	ArrayList<Movie> movies;
	private Movie movie;
	private Director director;
	
	private String tempTextString;
	private String tempFirstName;
	private String tempLastname;
	private String tempRole;

	/**
	 * SaxParser for parsing xml data to director-and-title.txt format
	 */
	public MovieSAXParser2(){
		movies = new ArrayList<Movie>();
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
	 * Start Event Handlers
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		tempTextString = "";
		
		if(qName.equalsIgnoreCase("movie")) {	
			movie = new Movie();
		}
		else if (qName.equalsIgnoreCase("director")) {
			director = new Director();
		}
	}
	
	/**
	 * Character Event Handlers
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempTextString = new String(ch,start,length);
	}
	
	/**
	 * End Event.
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("movie")) {
			//add it to the list
			movie.setDirector(director);
			movies.add(movie);
		}else if (qName.equalsIgnoreCase("title")) {
			movie.setMovieTitle(tempTextString);
		}else if (qName.equalsIgnoreCase("year")) {
			movie.setYear(tempTextString);
		}else if (qName.equalsIgnoreCase("director")) {
			director.setFirstName(tempFirstName);
			director.setLastName(tempLastname);
		}
		else if (qName.equalsIgnoreCase("first_name")) {
			tempFirstName = tempTextString;
		}
		else if (qName.equalsIgnoreCase("last_name")) {
			tempLastname = tempTextString;
		}	
	}
	
	public static void main(String[] args){
		MovieSAXParser2 spe = new MovieSAXParser2();

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




