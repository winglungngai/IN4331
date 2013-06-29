package movie;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import publication.Authors;

/**
 * 
 * @author root
 */
public class MovieJobs {
	
	private enum JobType {TITLE_AND_ACTOR, DIECTOR_AND_TITLE};

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			runJob("movies.xml", "title-and-actor", JobType.TITLE_AND_ACTOR);
			runJob("movies.xml", "director-and-title", JobType.DIECTOR_AND_TITLE);
		} catch (IOException e) {
			Logger.getLogger(MovieJobs.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	public static void runJob(String input, String output, JobType jobType) throws IOException {

		Configuration conf = new Configuration();

		conf.set("xmlinput.start", "<movie>");
		conf.set("xmlinput.end", "</movie>");
		conf.set("io.serializations", "org.apache.hadoop.io.serializer.JavaSerialization,org.apache.hadoop.io.serializer.WritableSerialization");

		Job job = new Job(conf, "jobName");

		FileInputFormat.setInputPaths(job, input);
		job.setJarByClass(MovieJobs.class);
		
		if (jobType == JobType.TITLE_AND_ACTOR)
		{
			job.setMapperClass(MovieMapper1.class);
		}
		else if (jobType == JobType.DIECTOR_AND_TITLE)
		{
			job.setMapperClass(MovieMapper2.class);
		}

		job.setReducerClass(MovieReducer.class);
		job.setNumReduceTasks(0);
		job.setInputFormatClass(XmlInputFormat.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		Path outPath = new Path(output);
		FileOutputFormat.setOutputPath(job, outPath);
		FileSystem dfs = FileSystem.get(outPath.toUri(), conf);
		if (dfs.exists(outPath)) {
			dfs.delete(outPath, true);
		}

		try {
			job.waitForCompletion(true);
		} catch (InterruptedException ex) {
			Logger.getLogger(MovieJobs.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MovieJobs.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}