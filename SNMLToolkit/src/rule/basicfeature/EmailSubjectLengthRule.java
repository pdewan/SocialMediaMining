package rule.basicfeature;

import dataimport.MessageData;
import dataimport.ThreadData;
import rule.NumericFeatureRule;

public class EmailSubjectLengthRule extends NumericFeatureRule implements IBasicFeatureRule{

	public EmailSubjectLengthRule(String destFeatureName) {
		super(destFeatureName);
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		
		MessageData msg = aThread.getKthEarlest(0);
		String subj = (String)msg.getAttribute("Subject");
		if(subj==null) return 0;
		
		double length = subj.length();
		
		return length;
	}

}
