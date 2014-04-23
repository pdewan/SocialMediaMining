package dataimport.json.yahooanswers;

import dataimport.json.JsonDataConfig;

/**
 * Define common fields name/type of YahooAnswers data.
 *
 * @author Jinjing Ma (jinjingm@cs.unc.edu)
 * @version $1$
 */
public abstract class YahooAnswersDataConfig extends JsonDataConfig{
	
	public static String CONTENT 			= "Content";
	public static String TIMESTAMP 			= "Timestamp";
	public static String USERID				= "UserId";
	public static String USERNICK 			= "UserNick";
	
	public static final String DATEFORMAT_YAHOO 	= "yyyy-MM-dd HH:mm:ss";
	
	public YahooAnswersDataConfig(){
		super();
		
		attributeTypes.put(CONTENT, STRING);
		
		attributeTypes.put(TIMESTAMP, LONG);
		attributeTypes.put(USERID, STRING);
		attributeTypes.put(USERNICK, STRING);
	}
	
	@Override
	public String getDateReadFormat() {
		return DATEFORMAT_YAHOO;
	}
	
}
