package dataimport.xml.stackoverflow;

import java.util.HashMap;

import dataimport.MsgDataConfig;
import dataimport.xml.XmlDataConfig;

public abstract class StackOverflowDataConfig extends XmlDataConfig{
	
	protected static String dateFormat		= "date \"yyyy-MM-ddTHH:mm:ss.s\"";
	
	public static String ID 				= "Id";
	public static String SCORE				= "Score";	
	public static String CREATIONDATE		= "CreationDate";

	public StackOverflowDataConfig(){
		super();
		
		attributeTypes.put(ID, s);
		attributeTypes.put(SCORE, i);
		attributeTypes.put(CREATIONDATE, dateFormat);
	}
	
}
