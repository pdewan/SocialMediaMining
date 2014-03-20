package dataimport.xml.stackoverflow;

public class StackOverflowAnswerConfig extends StackOverflowPostDataCofig{
	public static String PARENTID	= "ParentId";

	public StackOverflowAnswerConfig(){
		super();
		
		attributeTypes.put(PARENTID, s);
	}
	
}
