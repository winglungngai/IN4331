package inverted.file;


public class TextProcessor {
	String stemAndRemovePunctuation(String value) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		/*Class stemClass = Class.forName("org.tartarus.snowball.ext.englishStemmer");
		SnowballStemmer stemmer = (SnowballStemmer) stemClass.newInstance();
		stemmer.setCurrent(value);
		stemmer.stem();
		value = value.replaceAll("\\W", " ");
		value = value.replaceAll("  ", " ");*/
		
		
		return value;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		TextProcessor tp = new TextProcessor();
		String test = "Tom Stall, a humble family man and owner of a popular neighborhood restaurant, lives a quiet but fulfilling existence in the Midwest. One night Tom foils a crime at his place of business and, to his chagrin, is plastered all over the news for his heroics. Following this, mysterious people follow the Stalls' every move, concerning Tom more than anyone else. As this situation is confronted, more lurks out over where all these occurrences have stemmed from compromising his marriage, family relationship and the main characters' former relations in the process.";
		System.out.println(tp.stemAndRemovePunctuation(test));
	}
}
