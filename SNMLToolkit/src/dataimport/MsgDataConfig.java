package dataimport;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public abstract class MsgDataConfig {
	
	public static final String STRING 				= "string";
	public static final String INT 					= "int";
	public static final String LONG 				= "long";
	public static final String DOUBLE 				= "double";
	public static final String TYPEDATE				= "date";
	public static final String DATEFORMAT_DEFAULT 	= "yyyy-MM-dd HH:mm:ss";
	
	public HashMap<String, String> attributeTypes;
	
	public static final String THREADID 			= "ThreadId";
	public static final String DATE_DEFAULT 		= "Date";
	
	public MsgDataConfig(){
		attributeTypes = new HashMap<String, String>();
		attributeTypes.put(THREADID, INT);
		attributeTypes.put(DATE_DEFAULT, TYPEDATE);
	}
	
	public Set<Entry<String, String>> attrTypeEntries(){
		return attributeTypes.entrySet();
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
