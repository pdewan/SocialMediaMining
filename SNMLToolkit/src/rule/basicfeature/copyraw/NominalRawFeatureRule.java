package rule.basicfeature.copyraw;

import java.util.ArrayList;

import rule.NominalFeatureRule;
import rule.basicfeature.IBasicFeatureRule;
import dataimport.ThreadData;


public class NominalRawFeatureRule extends NominalFeatureRule implements IBasicFeatureRule {

	RawFeatureRule rawRule;
	
	public NominalRawFeatureRule(String destFeatureName, String srcAttrName, ArrayList<String> aDomain,
			int inOrder, int kth) {
		super(destFeatureName, aDomain);
		
		rawRule = new RawFeatureRule(destFeatureName, srcAttrName, inOrder, kth);
	}
	
	public NominalRawFeatureRule(String destFeatureName, String srcAttrName, ArrayList<String> aDomain) {
		super(destFeatureName, aDomain);
		
		rawRule = new RawFeatureRule(destFeatureName, srcAttrName, RawFeatureRule.ACCENDING, 0);
	}
	
	@Override
	public Object extract(ThreadData aThread) throws Exception {
		Object val = rawRule.extract(aThread);
		checkValid(val);
		
		return val;
	}
	
}
