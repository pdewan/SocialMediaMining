package rule.basicfeature;

import dataimport.MessageData;
import dataimport.ThreadData;
import dataimport.email.EmailDataConfig;
import rule.NumericVectorFeatureRule;

public class EmailRecipientIdsRule extends NumericVectorFeatureRule implements IBasicFeatureRule{

	public EmailRecipientIdsRule(String featureName, int totalRecipientNum) {
		super(featureName, totalRecipientNum);
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		double[] val = new double[length];
		for(int i=0; i<val.length; i++){
			val[i]=0;
		}
		
		MessageData msg = aThread.getKthEarlest(0);
		int[] recipients = (int[]) msg.getAttribute(EmailDataConfig.RECIPIENTS);
		
		for(int i=0; i<recipients.length; i++){
			val[recipients[i]-1] = 1;
		}
		
		return val;
	}

}
