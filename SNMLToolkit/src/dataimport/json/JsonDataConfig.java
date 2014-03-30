package dataimport.json;


import dataimport.MsgDataConfig;

public abstract class JsonDataConfig extends MsgDataConfig{
	
	public static final String JSON 		= "JSON";
	public static final String MESSAGE 		= "message";

	public JsonDataConfig(){
		super();
	}
	
	public abstract String getDateReadFormat();
	
}
