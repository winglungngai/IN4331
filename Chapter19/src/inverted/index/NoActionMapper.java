package inverted.index;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NoActionMapper extends Mapper<Object, Text, Text, Text> {
	@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		Scanner line = new Scanner(value.toString());
		line.useDelimiter("\t");
		Text keyText = new Text(line.next());
		Text valueText = new Text(line.next()); 
		context.write(keyText, valueText);
	}
}
