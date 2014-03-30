package dataimport.email;

import dataimport.MsgDataConfig;

public class EmailDataConfig extends MsgDataConfig {
	public static String ID			= "Id";
	public static String SUBJECT 	= "Subject";
	public static String ATTACHMENT_NUM 	= "Num_Attachments";
	public static String ATTACHMENTS		= "Attachments";
	public static String FROM				= "From";
	public static String RECIPIENTS			= "Recipients";
	public static String DAYOFWEEK			= "DayOfWeek";
	public static String TIMEZONE			= "TimeZone";
	
	public EmailDataConfig(){
		super();
		
		attributeTypes.put(ID, INT);
		attributeTypes.put(SUBJECT, STRING);
		attributeTypes.put(ATTACHMENT_NUM, DOUBLE);
		attributeTypes.put(FROM, INT);
		attributeTypes.put(DAYOFWEEK, "{Mon, Tue, Wed, Thu, Fri, Sat, Sun}");
		attributeTypes.put(TIMEZONE, STRING);
		
		//TODO: ARRAY
		attributeTypes.put(ATTACHMENTS, null);
		attributeTypes.put(RECIPIENTS, null);		
	}
}
