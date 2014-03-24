package dataconvert.rule.util.rawfeature;

import dataconvert.rule.NumericFeatureRule;
import dataconvert.rule.basicfeature.IBasicFeatureRule;
import dataimport.ThreadData;


public class NumericRawFeatureRule 
	extends NumericFeatureRule 
	implements IBasicFeatureRule{

	RawFeatureRule rawRule;
	
	public NumericRawFeatureRule(String destFeatureName, String srcAttrName,
			int inOrder, int kth) {
		super(destFeatureName);
		
		rawRule = new RawFeatureRule(destFeatureName, srcAttrName, inOrder, kth);
	}
	
	public NumericRawFeatureRule(String destFeatureName, String srcAttrName) {
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
