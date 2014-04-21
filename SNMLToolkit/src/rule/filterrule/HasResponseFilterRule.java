package rule.filterrule;

import dataimport.ThreadData;

public class HasResponseFilterRule extends ThreadFilterRule {

	public HasResponseFilterRule(String destFeatureName) {
		super(destFeatureName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		int n = aThread.size();		
		return n>=2;
	}

}
