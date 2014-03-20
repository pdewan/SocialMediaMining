package dataimport.xml.stackoverflow;

public class StackOverflowCommentConfig extends StackOverflowDataConfig{
	
	public static String POSTID				= "PostId";
	public static String USERID 			= "UserId";
	public static String TEXT				= "Text";

	public StackOverflowCommentConfig(){
		super();
		
		attributeTypes.put(POSTID, s);
		attributeTypes.put(USERID, s);
		attributeTypes.put(TEXT, s);
	}
	
}
