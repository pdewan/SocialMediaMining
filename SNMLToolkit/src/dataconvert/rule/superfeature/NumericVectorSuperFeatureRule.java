package dataconvert.rule.superfeature;

import dataconvert.rule.NumericVectorFeatureRule;

public abstract class NumericVectorSuperFeatureRule extends NumericVectorFeatureRule implements ISuperFeatureRule {

	public NumericVectorSuperFeatureRule(String featureName, int l) {
		super(featureName, l);
	}
	

}
