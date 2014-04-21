
package rule.basicfeature;

import dataimport.ThreadData;
import rule.NumericFeatureRule;

public class ResponseNumRule extends NumericFeatureRule implements IBasicFeatureRule{

	
	public ResponseNumRule(String destFeatureName) {
		super(destFeatureName);
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		int responseNum = aThread.size()-1;	
		return responseNum;
	}

}
