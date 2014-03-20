package usercase.markquestion;

import org.json.JSONArray;
import org.json.JSONObject;

import util.TextSentenceCounter;
import util.TextWordCounter;

public class LongAnswerNumFeatureExtractor extends JsonArrayFeatureExtractor {

	int wordNumThreshold;
	int sentenceNumThreshold;
	
	TextWordCounter wordCounter;
	TextSentenceCounter sentenceCounter;
	
	/*set wordNumThreshold = -1 if it's not used
	  set sentenceNumThreshold = -1 if it's not used
	*/
	public LongAnswerNumFeatureExtractor(
			String featureName, 
			int wordNumThreshold, 
			TextWordCounter wordCounter,
			int sentenceNumThreshold,
			TextSentenceCounter sentenceCounter) {
		
		super(featureName);
		this.wordNumThreshold = wordNumThreshold;
		this.sentenceNumThreshold = sentenceNumThreshold;
		this.wordCounter = wordCounter;
		this.sentenceCounter = sentenceCounter;
	}

	@Override
	public double extract(JSONArray answers) {
		int count = 0;
		for(int i=0; i<answers.length(); i++){
			JSONObject ans = answers.getJSONObject(i);
			String text = ans.getString("Content");
			int wordNum = this.wordCounter.count(text);
			int sentenceNum = this.sentenceCounter.count(text);
			
			if(wordNum >= this.wordNumThreshold && 
					sentenceNum >= this.sentenceNumThreshold){
				count ++;
			}
		}
		return count;
	}

}
