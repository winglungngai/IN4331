package inverted.index.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;

public class TextProcessor {
	public String[] stemAndRemovePunctuation(String value)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

		String retVal = null;
		try {
			QueryParser queryParser = new QueryParser(Version.LUCENE_43, "",
					analyzer);
			value = QueryParser.escape(value);
			value = value.replaceAll("\\W", " ");
			Query q = queryParser.parse(value);
			retVal = q.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return retVal.split(" ");
	}

	public String[] getTockens(String value) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

		List<String> tokens = new ArrayList<String>();
		try {
			QueryParser queryParser = new QueryParser(Version.LUCENE_43, "",
					analyzer);
			value = QueryParser.escape(value);
			// value = value.replaceAll("\\W", " ");
			Query q = queryParser.parse(value);
			Set<Term> tokensSet = new TreeSet<Term>();
			q.extractTerms(tokensSet);
			Iterator<Term> it = tokensSet.iterator();
			while (it.hasNext()) {
				Term term = it.next();
				tokens.add(term.text());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] retVal = new String[tokens.size()];
		for (int i = 0; i < tokens.size(); i++)
			retVal[i] = tokens.get(i);
		return retVal;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		TextProcessor tp = new TextProcessor();
		String test = "2 Hunters and their prey--Neil and his professional criminal crew hunt to score big money targets (banks, vaults, armored cars) and are, in turn, hunted by Lt. Vincent Hanna and his team of cops in the Robbery/Homicide police division. A botched job puts Hanna onto their trail while they regroup and try to put together one last big 'retirement' score. Neil and Vincent are similar in many ways, including their troubled personal lives. At a crucial moment in his life, Neil disobeys the dictum taught to him long ago by his criminal mentor--'Never have anything in your life that you can't walk out on in thirty seconds flat, if you spot the heat coming around the corner'--as he falls in love.";
		System.out.println(tp.stemAndRemovePunctuation(test));

	}
}
