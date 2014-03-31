package rule.superfeature.copy;

import dataconvert.IntermediateData;
import rule.NumericFeatureRule;
import rule.superfeature.ISuperFeatureRule;

public class NumericCopySuperFeatureRule extends NumericFeatureRule implements ISuperFeatureRule {

	protected String srcAttrName;
	
	public NumericCopySuperFeatureRule(String destFeatureName,
			String srcAttrName) {
		super(destFeatureName);
		this.srcAttrName = srcAttrName;
	}


	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		return anInstData.getNumericAttrValue(srcAttrName);
	}


}
