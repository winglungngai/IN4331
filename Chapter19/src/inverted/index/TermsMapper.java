package inverted.index;

import inverted.index.util.TextProcessor;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TermsMapper extends Mapper<Object, Text, Text, Text> {
	@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		try {
			TextProcessor tp = new TextProcessor();
			String[] tokens;
			tokens = tp.stemAndRemovePunctuation(value.toString());
			String documentId = tokens[0];
			Text termDocumentId = new Text(documentId+ " ");
			for (int i = 1; i < tokens.length; i++) {
				Text termValue = new Text(tokens[i]);
				context.write(termValue, termDocumentId);
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
