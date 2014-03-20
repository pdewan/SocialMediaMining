package dataimport.json.yahooanswers;

import dataimport.json.JsonDataConfig;

public abstract class YahooAnswersDataConfig extends JsonDataConfig{
	
	public static String CONTENT 			= "Content";
	public static String TIMESTAMP 			= "Timestamp";
	public static String USERID				= "UserId";
	public static String USERNICK 			= "UserNick";
	
	public YahooAnswersDataConfig(){
		super();
		
		attributeTypes.put(CONTENT, s);
		
		attributeTypes.put(TIMESTAMP, l);
		attributeTypes.put(USERID, s);
		attributeTypes.put(USERNICK, s);
	}
	
}
