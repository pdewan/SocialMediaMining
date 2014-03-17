package dataimport;

import java.util.HashMap;

public class MessageData {
	protected HashMap<String, Object> attributes;
	
	public MessageData(){
		attributes = new HashMap<String, Object>();
	}
	
	public MessageData(int attributeNum){
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
