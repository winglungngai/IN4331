package inverted.index.util;

import inverted.index.NoActionMapper;
import inverted.index.TermsFreqReducer;
import inverted.index.TermsMapper;
import inverted.index.TermsReducer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import publication.VersionController;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		/*
		String fileIn = "movies.txt";
		String fileOut = "movies_out.txt";
		try {
			Main.processFile(fileIn, fileOut);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(1==1) return;*/
		String[] params = {"hdfs://localhost:9000/dblp/movies_out.txt", "results-movies"};
		//String[] params = {"hdfs://localhost:9000/dblp/author-small.txt", "results-movies"};
		 args = params;
		/*
		 * Load the Haddop configuration. IMPORTANT: the 
		 * $HADOOP_HOME/conf directory must be in the CLASSPATH
		 */
		Configuration conf = new Configuration();
		

		
		Job firstJob = new Job(conf, "Tokenize and compute document frequency ");
		
		/* Define the Mapper and the Reducer */
		firstJob.setMapperClass(TermsMapper.class);
		firstJob.setReducerClass(TermsReducer.class);
		//job.setCombinerClass(Authors.CountReducer.class);

		/* Define the output type */
		firstJob.setOutputKeyClass(Text.class);
		firstJob.setOutputValueClass(Text.class);

		/* Set the input and the output */
		FileInputFormat.addInputPath(firstJob, new Path(args[0]));
		Path firstJobOutputPath = new Path(args[1] + "/" + args[1]+VersionController.GetIncrementedVersionNumber());
		FileOutputFormat.setOutputPath(firstJob, firstJobOutputPath);

		/* Do it! */
		boolean firstJobStatus = firstJob.waitForCompletion(true);
		if(firstJobStatus){
			conf = new Configuration();
			conf.set("totalNumberOfDocuments", 6+"");
			Job secondJob = new Job(conf, "");
			/* Define the Mapper and the Reducer */
			secondJob.setMapperClass(NoActionMapper.class);
			secondJob.setReducerClass(TermsFreqReducer.class);
			
			secondJob.setOutputKeyClass(Text.class);
			secondJob.setOutputValueClass(Text.class);
			
			FileInputFormat.addInputPath(secondJob, firstJobOutputPath);
			Path secondJobOutputPath = new Path(args[1] + "/" + args[1]+VersionController.GetIncrementedVersionNumber());
			FileOutputFormat.setOutputPath(secondJob, secondJobOutputPath);
			
			/* Do it! */
			System.exit(secondJob.waitForCompletion(true)==true?0:1);
			
		}else{
			System.exit(1);
		}
	}

	public static void processFile(String fileIn, String fileOut) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fileIn));
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
		String line;
		TextProcessor tp = new TextProcessor();
		while ((line = reader.readLine()) != null) {
			String[] newValue = tp.stemAndRemovePunctuation(line);
			for(int i=0;i<newValue.length;i++) writer.append(newValue[i]).append(" ");
			writer.append("\n");
		}
		reader.close();
		writer.close();
	}
}
