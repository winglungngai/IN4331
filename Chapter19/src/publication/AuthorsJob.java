package publication;

/**
 * Example of a simple MapReduce job: it reads 
 * file containing authors and publications, and 
 * produce each author with her publication count.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * The follozing class implements the Job submission, based on 
 * the Mapper (AuthorsMapper) and the Reducer (CountReducer)
 */
public class AuthorsJob {

  public static void main(String[] args) throws Exception {

	/*
	 * Load the Haddop configuration. IMPORTANT: the 
	 * $HADOOP_HOME/conf directory must be in the CLASSPATH
	 */
	Configuration conf = new Configuration();

	/* We expect two arguments */

	if (args.length != 2) {
	  System.err.println("Usage: AuthorsJob <in> <out>");
	  System.exit(2);
	}

	/* Allright, define and submit the job */
	Job job = new Job(conf, "Authors count");

	/* Define the Mapper and the Reducer */
	job.setMapperClass(Authors.AuthorsMapper.class);
	job.setReducerClass(Authors.CountReducer.class);
	job.setCombinerClass(Authors.CountReducer.class);

	/* Define the output type */
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(IntWritable.class);

	/* Set the input and the output */
	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1] + "/" + args[1]+VersionController.GetIncrementedVersionNumber()));

	/* Do it! */
	System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
