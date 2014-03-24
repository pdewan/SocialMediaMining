package dataconvert.rule.superfeature;


import dataconvert.rule.DateFeatureRule;

public abstract class DateSuperFeatureRule extends DateFeatureRule implements ISuperFeatureRule {

	public DateSuperFeatureRule(String destFeatureName, String dateFormat) {
		super(destFeatureName, dateFormat);
	}

	

}
