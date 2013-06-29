package inverted.file;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DFCalculator {

	public static class DfMapper extends
	Mapper<Object, Text, Text, IntWritable> {

	private Text author = new Text();
	
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		IntWritable documentId = new IntWritable();
		Scanner line = new Scanner(value.toString());
		line.useDelimiter("\t");
		author.set(line.next());
		//context.write(author, one);
	}
}
	
}
