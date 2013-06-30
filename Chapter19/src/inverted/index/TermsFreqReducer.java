package inverted.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TermsFreqReducer extends Reducer<Text, Text, Text, Text> {

	int totalNumberOfDocuments = 6;
	//the values are of form documentId/term_frequency 
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		String token = key.toString();
		StringBuffer sb = new StringBuffer();
		List<String> valuesArray = new ArrayList<String>();
		Iterator<Text> it = values.iterator();
		while(it.hasNext()){
			Text text = it.next();
			valuesArray.add(text.toString());
		}
		
		
		for (String dIdTf : valuesArray) {
			String[] dIdTfArray = dIdTf.trim().split("/");
			if(dIdTfArray.length==2){
				String documentId = dIdTfArray[0];
				String documentFrequency = dIdTfArray[1];
				sb.append(dIdTf.toString());
				Double df = new Double(documentFrequency);
				df = totalNumberOfDocuments/df;
				sb.append("/").append(Math.log10(df));
				sb.append(";");
			}
		}
		
		context.write(key, new Text(sb.toString()));
	}
}
