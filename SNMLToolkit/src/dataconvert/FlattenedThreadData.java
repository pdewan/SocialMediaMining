package dataconvert;

import java.util.HashMap;

public class FlattenedThreadData {
	protected HashMap<String, Object> attributes;
	
	public FlattenedThreadData(){
		attributes = new HashMap<String, Object>();
	}
	
	public FlattenedThreadData(int attributeNum){
		attributes = new HashMap<String, Object>(attributeNum+1, 1);
	}
	
	public void addAttribute(String attributeName, Object attribute){
		/*
		if(!dataConfig.verifyAttribute(attributeName)){
			System.out.println("error in PostData.addAttribute: "+ 
									attributeName + " is not valid attribute name");
			return;
		}
		*/
		attributes.put(attributeName, attribute);
	}
	
	public Object getAttribute(String attributeName){
		return attributes.get(attributeName);
	}

}
