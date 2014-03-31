package rule.superfeature;

import java.util.ArrayList;

import rule.NominalFeatureRule;

public abstract class NominalSuperFeatureRule extends NominalFeatureRule implements ISuperFeatureRule {
	
	public NominalSuperFeatureRule(String featureName, ArrayList<String> aDomain){
		super(featureName, aDomain);
	}
	
	/*
	public NominalSuperFeatureRule(String featureName){
		super(featureName);
	}
	*/
	
	

}
