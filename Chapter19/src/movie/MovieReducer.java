package movie;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Reducer for the movie entities. 
 */
public class MovieReducer extends Reducer<Text, IntWritable, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context)
		throws IOException, InterruptedException {
	  context.write(key, new Text(""));
	}
}
