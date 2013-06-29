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
 * Mapper type that convert xml data to director-and-title.txt format
 */
public class MovieMapper2 extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	public void map(LongWritable key, Text value1, Context context) throws IOException, InterruptedException {
		MovieSAXParser2 spe = new MovieSAXParser2();
		InputStream is = IOUtils.toInputStream(value1.toString());
		ArrayList<Movie> movies = spe.parse(is);
		for(Movie movie : movies)
		{
				String text = movie.getDirector() + "\t " + movie.getMovieTitle() + "\t" + movie.getYear();
				context.write(new Text(text), new Text(""));
		}
	}
}