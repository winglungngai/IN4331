package movie;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import org.apache.commons.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import entity.Actor;
import entity.Movie;


/**
 * Mapper type that convert xml data to title-and-actor.txt format
 */
public class MovieMapper1 extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	public void map(LongWritable key, Text value1, Context context) throws IOException, InterruptedException {
		MovieSAXParser1 spe = new MovieSAXParser1();
		InputStream is = IOUtils.toInputStream(value1.toString());
		ArrayList<Movie> movies = spe.parse(is);
		for(Movie movie : movies)
		{
			for(Actor actor : movie.getActors())
			{
				String text = movie.getMovieTitle() + "\t "+ actor.getFirstName() + " "+ actor.getLastName() + "\t"+ movie.getYear() + "\t"+ actor.getRole();
				context.write(new Text(text), new Text(""));
			}
		}
	}

}