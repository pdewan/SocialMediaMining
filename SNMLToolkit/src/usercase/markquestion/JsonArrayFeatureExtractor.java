package usercase.markquestion;

import org.json.JSONArray;

public abstract class JsonArrayFeatureExtractor {
	public String featureName;
	
	public JsonArrayFeatureExtractor(String featureName){
		this.featureName = featureName;
	}

	public abstract double extract(JSONArray answers);
}
