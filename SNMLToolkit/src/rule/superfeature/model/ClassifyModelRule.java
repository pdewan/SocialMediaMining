package rule.superfeature.model;

import java.util.ArrayList;

import rule.superfeature.NominalSuperFeatureRule;

public abstract class ClassifyModelRule extends NominalSuperFeatureRule
		implements IModelRule {

	public ClassifyModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}
	
	public ClassifyModelRule(String featureName, int classNum) {
		super(featureName, null);
		domain = new ArrayList<String>(classNum);
		for(int i=0; i<classNum; i++){
			domain.add(String.valueOf(i));
		}
	}


}
