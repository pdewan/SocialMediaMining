package dataimport.xml.stackoverflow;

import java.util.HashMap;

import dataimport.MsgDataConfig;
import dataimport.xml.XmlDataConfig;

public abstract class StackOverflowDataConfig extends XmlDataConfig{
	
	public static final String DATEFORMAT_STACKOVER	= "yyyy-MM-ddTHH:mm:ss.s";
	
	public static String ID 				= "Id";
	public static String SCORE				= "Score";	
	public static String CREATIONDATE		= "CreationDate";

	public StackOverflowDataConfig(){
		super();
		
		attributeTypes.put(ID, STRING);
		attributeTypes.put(SCORE, DOUBLE);
		attributeTypes.put(CREATIONDATE, DATEFORMAT_STACKOVER);
	}
	
}
