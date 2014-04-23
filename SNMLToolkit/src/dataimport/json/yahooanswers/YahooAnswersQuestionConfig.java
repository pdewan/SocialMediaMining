package dataimport.json.yahooanswers;

/**
 * Define fields name/type of YahooAnswers question.
 *
 * @author Jinjing Ma (jinjingm@cs.unc.edu)
 * @version $1$
 */
public class YahooAnswersQuestionConfig extends YahooAnswersDataConfig{	
	
	public static String ID 				= "id";
	public static String TYPE 				= "type";
	public static String SUBJECT 			= "Subject";	
	public static String CATEGORY 			= "Category";
	
	public static String NUMANSWERS			= "NumAnswers";
	public static String NUMCOMMENTS		= "NumComments";
	
	public static String CHOSENANSWER				= "ChosenAnswer";
	public static String CHOSENANSWERID				= "ChosenAnswerId";
	public static String CHOSENANSWERNICK			= "ChosenAnswerNick";
	public static String CHOSENANSWERTIMESTAMP		= "ChosenAnswerTimeStamp";
	public static String CHOSENANSWERAWARDTIMESTAMP	= "ChosenAnswerAwardTimeStamp";
		
	public static String ANSWERS			= "Answers";	
	public static String COMMENTS			= "Comments";
	
	public YahooAnswersQuestionConfig(){
		super();
		
		attributeTypes.put(ID, STRING);
		attributeTypes.put(TYPE, "{Open, Answered, Deleted}");
		attributeTypes.put(SUBJECT, STRING);
		
		attributeTypes.put(CATEGORY, JSON);
		
		attributeTypes.put(NUMANSWERS, INT);
		attributeTypes.put(NUMCOMMENTS, INT);
		
		attributeTypes.put(CHOSENANSWER, STRING);
		attributeTypes.put(CHOSENANSWERID, STRING);
		attributeTypes.put(CHOSENANSWERNICK, STRING);
		attributeTypes.put(CHOSENANSWERTIMESTAMP, LONG);
		attributeTypes.put(CHOSENANSWERAWARDTIMESTAMP, LONG);
		
		attributeTypes.put(ANSWERS, MESSAGE);
		attributeTypes.put(COMMENTS, JSON);
	}
	

}
