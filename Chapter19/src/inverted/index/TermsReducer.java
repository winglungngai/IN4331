package inverted.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TermsReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		Map<String, DocFrequency> docFreq = new HashMap<String, DocFrequency>();
		String token = key.toString();
		if(key.toString().equals("his")){
			int h = 0;
			
		}
		for (Text dId : values) {
			String documentId = dId.toString();
			String tokenDocumentKey = token + "_" + documentId;
			DocFrequency df = null;
			if (docFreq.containsKey(tokenDocumentKey)) {
				df = docFreq.get(tokenDocumentKey);
			} else {
				df = new DocFrequency();
				df.docId = documentId;
			}
			df.frequency +=1;
			docFreq.put(tokenDocumentKey, df);
		}
		Set<String> keys = docFreq.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String tokenValue = it.next();
			DocFrequency df = docFreq.get(tokenValue);
			context.write(new Text(token), new Text(df.toString()));
		}
	}

	class DocFrequency {
		Integer frequency = 0;
		String docId = "";
		public String toString(){
			return "d"+docId.trim() + "/" + frequency;
		}
	}
}
