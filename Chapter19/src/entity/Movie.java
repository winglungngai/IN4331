package entity;
import java.util.ArrayList;


public class Movie {
	
	private String movieTitle;
	private String year;
	private Director director;
	private ArrayList<Actor> actors;

	public Movie() {
	}
	
	public Movie(String movieTitle, Director director, ArrayList<Actor> actors, String year) {
		setMovieTitle(movieTitle);
		setDirector(director);
		setActors(actors);
		setYear(year);
	}
	
	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}
	
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String toString()
	{
		return movieTitle + "\t" + year + "\t" + director; 
	}
}
