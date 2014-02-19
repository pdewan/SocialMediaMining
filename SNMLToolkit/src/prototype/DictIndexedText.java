package prototype;

import java.util.ArrayList;

public class DictIndexedText {
	ArrayList<Integer> indexedText;
	
	public DictIndexedText(String text){
		indexedText = Dictionary.parseText(text);
	}
	
	public Integer get(int idx){
		return indexedText.get(idx);
	}
	
	public int size(){
		return indexedText.size();
	}
}
