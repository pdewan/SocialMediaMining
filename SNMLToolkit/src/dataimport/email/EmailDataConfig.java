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
		
		attributeTypes.put(ID, i);
		attributeTypes.put(SUBJECT, s);
		attributeTypes.put(ATTACHMENT_NUM, i);
		attributeTypes.put(FROM, i);
		attributeTypes.put(DAYOFWEEK, "{Mon, Tue, Wed, Thu, Fri, Sat, Sun}");
		attributeTypes.put(TIMEZONE, s);
		
		//array must be extracted to feature
		attributeTypes.put(ATTACHMENTS, null);
		attributeTypes.put(RECIPIENTS, null);		
	}
}
