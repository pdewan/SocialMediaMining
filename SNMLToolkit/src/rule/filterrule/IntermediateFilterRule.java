package rule.filterrule;

import rule.BinaryFeatureRule;
import rule.superfeature.ISuperFeatureRule;

public abstract class IntermediateFilterRule extends BinaryFeatureRule implements ISuperFeatureRule{

	public IntermediateFilterRule(String destFeatureName) {
		super(destFeatureName);
	}

}
