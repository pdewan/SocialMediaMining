package dataimport.xml.stackoverflow;

public class StackOverflowPostDataCofig extends StackOverflowDataConfig{
	
	public static String POSTTYPEID			= "PostTypeId";
	public static String OWNERUSERID		= "OwnerUserId";
	public static String LASTEDITORUSERID		= "LastEditorUserId";
	public static String LASTEDITORDISPLAYNAME	= "LastEditorDisplayName";
	
	
	public static String LASTEDITDATE		= "LastEditDate";
	public static String LASTACTIVITYDATE	= "LastActivityDate";
	
	public static String COMMENTCOUNT		= "CommentCount";
	public static String FAVORITECOUNT		= "FavoriteCount";
	
	public static String BODY				= "Body";
	
	public StackOverflowPostDataCofig(){
		super();

		attributeTypes.put(POSTTYPEID, "{1, 2, 3}");
		attributeTypes.put(OWNERUSERID, s);
		attributeTypes.put(LASTEDITORUSERID, s);
		attributeTypes.put(LASTEDITORDISPLAYNAME, s);
		
		attributeTypes.put(BODY, s);
		
		attributeTypes.put(COMMENTCOUNT, i);
		attributeTypes.put(FAVORITECOUNT, i);
		
		attributeTypes.put(LASTEDITDATE, dateFormat);
		attributeTypes.put(LASTACTIVITYDATE, dateFormat);
	}
	
}
