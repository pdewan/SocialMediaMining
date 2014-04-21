package rule.superfeature.model;

import java.util.ArrayList;

import rule.superfeature.NominalSuperFeatureRule;

public abstract class NominalModelRule extends NominalSuperFeatureRule implements
		IModelRule {

	public NominalModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}

}
