package rule.basicfeature;

import rule.NumericFeatureRule;
import dataimport.MessageData;
import dataimport.ThreadData;
import dataimport.email.EmailDataConfig;

public class EmailRecipientNumRule  extends NumericFeatureRule implements IBasicFeatureRule{

	public EmailRecipientNumRule(String destFeatureName) {
		super(destFeatureName);
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		
		MessageData msg = aThread.getKthEarlest(0);
		int[] recipients = (int[]) msg.getAttribute(EmailDataConfig.RECIPIENTS);
		return recipients.length;
	}

}
