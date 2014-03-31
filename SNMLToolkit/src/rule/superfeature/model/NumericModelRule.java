package rule.superfeature.model;

import rule.superfeature.NumericSuperFeatureRule;

public abstract class NumericModelRule extends NumericSuperFeatureRule implements
IModelRule {

	public NumericModelRule(String featureName) {
		super(featureName);
	}

}
