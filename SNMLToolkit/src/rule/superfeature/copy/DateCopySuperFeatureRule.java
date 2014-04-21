package rule.superfeature.copy;

import dataconvert.IntermediateData;
import rule.DateFeatureRule;
import rule.superfeature.ISuperFeatureRule;

public class DateCopySuperFeatureRule extends DateFeatureRule implements
		ISuperFeatureRule {

	protected String srcAttrName;
	
	public DateCopySuperFeatureRule(String destFeatureName, String srcAttrName, String dateFormat) {
		super(destFeatureName, dateFormat);
		
		this.srcAttrName = srcAttrName;
	}

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		return anInstData.getDateAttrValue(srcAttrName);
	}
	



}
