package rule.basicfeature;

import rule.NumericFeatureRule;
import dataimport.ThreadData;

public class FollowMessageNumRule extends NumericFeatureRule implements IBasicFeatureRule{

	public FollowMessageNumRule(String destFeatureName) {
		super(destFeatureName);
	}

	@Override
	//returns -1 if aThread is null or contains no msgdata
	public Object extract(ThreadData aThread) throws Exception {
		if(aThread==null) return -1.0;
		
		double msgNum = aThread.size();	
		
		return msgNum-1;
	}

}
