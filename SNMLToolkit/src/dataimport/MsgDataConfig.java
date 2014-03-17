package dataimport;

import java.util.HashMap;

public abstract class MsgDataConfig {
	
	protected static String s 	= "string";
	protected static String i 	= "int";
	protected static String l 	= "long";
	protected static String d 	= "double";
	protected static String dateFormat 	= "date \"yyyy-MM-dd HH:mm:ss\"";
	
	protected HashMap<String, String> attributeTypes;
	
	public static String THREADID = "ThreadId";
	public static String DATE = "Date";
	
	public MsgDataConfig(){
		attributeTypes = new HashMap<String, String>();
		attributeTypes.put(THREADID, i);
		attributeTypes.put(DATE, dateFormat);
	}
	
	public String getAttributeType(String attributeName){
		return attributeTypes.get(attributeName);
	}
	
	public boolean verifyAttribute(String attributeName){
		return attributeTypes.containsKey(attributeName);
	}
	
	public int attributeNum(){
		return attributeTypes.size();
	}

}
