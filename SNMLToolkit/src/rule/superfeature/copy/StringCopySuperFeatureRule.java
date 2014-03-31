package rule.superfeature.copy;

import rule.StringFeatureRule;
import rule.superfeature.ISuperFeatureRule;
import dataconvert.IntermediateData;

public class StringCopySuperFeatureRule extends StringFeatureRule implements ISuperFeatureRule {
	
	protected String srcAttrName;
	
	public StringCopySuperFeatureRule(String destFeatureName,
			String srcAttrName) {
		super(destFeatureName);
		this.srcAttrName = srcAttrName;
	}
	
	

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		return anInstData.getStringAttrValue(srcAttrName);
	}

}
