package dataconvert.rule.util;

import weka.core.Instance;
import dataconvert.rule.NumericSuperFeatureRule;
import dataimport.ThreadData;

public class FollowMessageNumRule extends NumericSuperFeatureRule {

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

	@Override
	public Object extract(Instance anInstance) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
