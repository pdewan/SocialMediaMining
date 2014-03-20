package dataimport.json.yahooanswers;


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
		
		attributeTypes.put(ID, s);
		attributeTypes.put(TYPE, "{Open, Answered, Deleted}");
		attributeTypes.put(SUBJECT, s);
		
		//TODO: handle Category, it's JSONObject / nominal
		attributeTypes.put(CATEGORY, j);
		
		attributeTypes.put(NUMANSWERS, i);
		attributeTypes.put(NUMCOMMENTS, i);
		
		attributeTypes.put(CHOSENANSWER, s);
		attributeTypes.put(CHOSENANSWERID, s);
		attributeTypes.put(CHOSENANSWERNICK, s);
		attributeTypes.put(CHOSENANSWERTIMESTAMP, l);
		attributeTypes.put(CHOSENANSWERAWARDTIMESTAMP, l);
		
		//TODO: handle Answers later, it's JSONObject contains JSONArray
		attributeTypes.put(ANSWERS, j);
		
		//TODO: handle Comments later, haven't seen any question containing it
		attributeTypes.put(COMMENTS, j);
	}
	

}
