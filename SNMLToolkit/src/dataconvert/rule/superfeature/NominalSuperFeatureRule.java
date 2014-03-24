package dataconvert.rule.superfeature;

import java.util.ArrayList;

import dataconvert.rule.FeatureRule;
import dataconvert.rule.NominalFeatureRule;

public abstract class NominalSuperFeatureRule extends NominalFeatureRule implements ISuperFeatureRule {
	protected ArrayList<String> domain;
	
	public NominalSuperFeatureRule(String featureName, ArrayList<String> aDomain){
		super(featureName);
		domain = aDomain;
	}
	
	public ArrayList<String> getDomain(){
		return domain;
	}
	
	

}
