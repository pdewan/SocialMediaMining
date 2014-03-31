package rule.basicfeature.copyraw;

import rule.StringFeatureRule;
import rule.basicfeature.IBasicFeatureRule;
import dataimport.ThreadData;

public class StringRawFeatureRule extends StringFeatureRule implements
		IBasicFeatureRule {

	RawFeatureRule rawRule;
	
	public StringRawFeatureRule(String destFeatureName, String srcAttrName,
			int inOrder, int kth) {
		super(destFeatureName);
		
		rawRule = new RawFeatureRule(destFeatureName, srcAttrName, inOrder, kth);
	}
	
	public StringRawFeatureRule(String destFeatureName, String srcAttrName) {
		super(destFeatureName);
		
		rawRule = new RawFeatureRule(destFeatureName, srcAttrName, RawFeatureRule.ACCENDING, 0);
	}
	
	@Override
	public Object extract(ThreadData aThread) throws Exception {
		Object val = rawRule.extract(aThread);
		checkValid(val);
		
		return val;
	}

}
