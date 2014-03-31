package rule.basicfeature.copyraw;

import rule.DateFeatureRule;
import rule.basicfeature.IBasicFeatureRule;
import dataimport.ThreadData;

public class DateRawFeatureRule extends DateFeatureRule implements IBasicFeatureRule {

	RawFeatureRule rawRule;
	
	public DateRawFeatureRule(String destFeatureName, String srcAttrName, String dateFormat,
			int inOrder, int kth) {
		super(destFeatureName, dateFormat);
		
		rawRule = new RawFeatureRule(destFeatureName, srcAttrName, inOrder, kth);
	}
	
	public DateRawFeatureRule(String destFeatureName, String srcAttrName, String dateFormat) {
		super(destFeatureName, dateFormat);
		
		rawRule = new RawFeatureRule(destFeatureName, srcAttrName, RawFeatureRule.ACCENDING, 0);
	}
	
	@Override
	public Object extract(ThreadData aThread) throws Exception {
		Object val = rawRule.extract(aThread);
		checkValid(val);
		
		return val;
	}

	
}
