package dataimport.json.yahooanswers;

import dataimport.json.JsonDataConfig;

public class YahooAnswersDataConfig extends JsonDataConfig{

	protected static String date 	= "date \"yyyy-MM-dd HH:mm:ss\"";
	
	public static String CONTENT 			= "Content";
	public static String DATE 				= "Date";
	public static String TIMESTAMP 			= "Timestamp";
	public static String USERID				= "UserId";
	public static String USERNICK 			= "UserNick";
	
	public void initFieldType(){
		super.initFieldType();
		
		FIELDTYPE.put(CONTENT, s);
		FIELDTYPE.put(DATE, date);
		FIELDTYPE.put(TIMESTAMP, l);
		FIELDTYPE.put(USERID, s);
		FIELDTYPE.put(USERNICK, s);
	}
}
