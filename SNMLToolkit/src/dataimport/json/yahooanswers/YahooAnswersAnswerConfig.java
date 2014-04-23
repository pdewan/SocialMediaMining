package dataimport.json.yahooanswers;

/**
 * Define fields name/type of a YahooAnswers answer.
 *
 * @author Jinjing Ma (jinjingm@cs.unc.edu)
 * @version $1$
 */
public class YahooAnswersAnswerConfig extends YahooAnswersDataConfig{

	//TODO: Haven't seen a reference != null
	public static String REREFENCE 				= "Reference";
	public static String REREFENCE_DATATYPE 	= null;
	
	public static String BEST 					= "Best";
	public static String BEST_DATATYPE 			= INT;
	
	public YahooAnswersAnswerConfig(){
		super();
		
		//attributeTypes.put(BEST, ?);
	}
}
