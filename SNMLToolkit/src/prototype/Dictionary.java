package prototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary {
	static private ArrayList<String> indexToWord = new ArrayList<String>();
	static private HashMap<String, Integer> wordToIndexMap = new HashMap<String, Integer>();
	
	static public int add(String word){
		if(!wordToIndexMap.containsKey(word)){
			wordToIndexMap.put(word, indexToWord.size());
			indexToWord.add(word);
		}
		return wordToIndexMap.get(word);
	}
	
	static public ArrayList<Integer> parseText(String text){
		Pattern pattern = Pattern.compile("[^a-zA-Z]+");	//only support english now
		Matcher matcher = pattern.matcher(text);
		text = matcher.replaceAll(" ");
		
		StringTokenizer st = new StringTokenizer(text, " ");
		ArrayList<Integer> textCode = new ArrayList<Integer>(st.countTokens()+1);		
		while(st.hasMoreTokens()){
			int index = add(st.nextToken());
			textCode.add(index);
		}
		
		return textCode;
	}
	
	static public int lookupIndex(String word){
		return wordToIndexMap.get(word);
	}
	
	static public String lookupWord(int idx){
		return indexToWord.get(idx);
	}
}
