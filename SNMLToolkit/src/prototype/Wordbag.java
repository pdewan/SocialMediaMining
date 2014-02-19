package prototype;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Wordbag {
	int wordNum;
	HashMap<Integer, Double> wordbag;

	public Wordbag(DictIndexedText text){
		int wordNum = text.size();
		wordbag = new HashMap<Integer, Double>((int)(wordNum/0.75)+2);
		this.convertDictIndexedText(text);
	}
	
	public Wordbag(String text){
		DictIndexedText indexedText = new DictIndexedText(text);
		
		int wordNum = indexedText.size();
		wordbag = new HashMap<Integer, Double>(wordNum+1);
		this.convertDictIndexedText(indexedText);
	}
	
	private void convertDictIndexedText(DictIndexedText text){
		for(int i=0; i<wordNum; i++){
			int idx = text.get(i);
			if(wordbag.containsKey(idx)){
				double freq = wordbag.get(idx) + 1;
				wordbag.put(idx, freq);
			}			
		}
		for(Entry<Integer, Double> e: wordbag.entrySet()){
			double freq = e.getValue() / wordNum;
			wordbag.put(e.getKey(), freq);
		}
	}
	
	public Set<Entry<Integer, Double>> entrySet(){
		return this.wordbag.entrySet();
	}
	
	public boolean containsWordIndex(int idx){
		return this.wordbag.containsKey(idx);
	}
	
	public boolean containsWord(String word){
		int idx = Dictionary.lookupIndex(word);
		return this.wordbag.containsKey(idx);
	}
	
	public double getFreq(int idx){
		return this.wordbag.get(idx);
	}
	
	
}
