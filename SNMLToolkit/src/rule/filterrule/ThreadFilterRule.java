package rule.filterrule;

import rule.BinaryFeatureRule;
import rule.basicfeature.IBasicFeatureRule;

public abstract class ThreadFilterRule extends BinaryFeatureRule implements IBasicFeatureRule {

	public ThreadFilterRule(String destFeatureName) {
		super(destFeatureName);
	}

}
