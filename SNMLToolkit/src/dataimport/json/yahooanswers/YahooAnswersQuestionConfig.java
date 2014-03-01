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
	
	
	
	public void initFieldType(){
		super.initFieldType();
	
		FIELDTYPE.put(ID, s);
		FIELDTYPE.put(TYPE, "{Open, Answered, Deleted}");
		FIELDTYPE.put(SUBJECT, s);
		
		//TODO: handle Category, it's JSONObject / nominal
		FIELDTYPE.put(CATEGORY, null);
		
		FIELDTYPE.put(NUMANSWERS, i);
		FIELDTYPE.put(NUMCOMMENTS, i);
		
		FIELDTYPE.put(CHOSENANSWER, s);
		FIELDTYPE.put(CHOSENANSWERID, s);
		FIELDTYPE.put(CHOSENANSWERNICK, s);
		FIELDTYPE.put(CHOSENANSWERTIMESTAMP, l);
		FIELDTYPE.put(CHOSENANSWERAWARDTIMESTAMP, l);
		
		//TODO: handle Answers later, it's JSONObject contains JSONArray
		FIELDTYPE.put(ANSWERS, null);
		
		//TODO: handle Comments later, haven't seen any question containing it
		FIELDTYPE.put(COMMENTS, null);
		
	}

}
