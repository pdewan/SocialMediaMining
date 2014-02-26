package dataimport.json.yahooanswers;

public class QuestionConfig extends DataConfig{
	
	public static String ID 				= "id";
	public static String ID_DATATYPE 		= s;
	
	public static String TYPE 				= "type";
	public static String TYPE_DATATYPE 		= "{Open, Answered, Deleted}";
	
	public static String SUBJECT 			= "Subject";
	public static String SUBJECT_DATATYPE 	= s;
	
	//TODO: handle Category, it's JSONObject
	public static String CATEGORY 			= "Category";
	public static String CATEGORY_DATATYPE 	= s; 
	
	public static String NUMANSWERS				= "NumAnswers";
	public static String NUMANSWERS_DATATYPE 	= i; 
	
	public static String NUMCOMMENTS			= "NumComments";
	public static String NUMCOMMENTS_DATATYPE 	= i; 
	
	public static String CHOSENANSWER				= "ChosenAnswer";
	public static String CHOSENANSWER_DATATYPE 		= s; 
	
	public static String CHOSENANSWERID				= "ChosenAnswerId";
	public static String CHOSENANSWERID_DATATYPE	= s; 
	
	public static String CHOSENANSWERNICK			= "ChosenAnswerNick";
	public static String CHOSENANSWERNICK_DATATYPE 	= s; 
	
	public static String CHOSENANSWERTIMESTAMP				= "ChosenAnswerTimeStamp";
	public static String CHOSENANSWERTIMESTAMP_DATATYPE 	= l; 
	
	public static String CHOSENANSWERAWARDTIMESTAMP			= "ChosenAnswerAwardTimeStamp";
	public static String CHOSENANSWERAWARDTIMESTAMP_DATATYPE= l;
	
	//TODO: handle Answers later, it's JSONObject contains JSONArray
	public static String ANSWERS			= "Answers";
	public static String ANSWERS_DATATYPE	= null;
	
	//TODO: handle Comments later, haven't seen any question containing it
	public static String COMMENTS			= "Comments";
	public static String COMMENTS_DATATYPE	= null;
}
