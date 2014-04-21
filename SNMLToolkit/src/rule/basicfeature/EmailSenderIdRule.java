package rule.basicfeature;

import java.util.ArrayList;

import dataimport.MessageData;
import dataimport.ThreadData;
import dataimport.email.EmailDataConfig;
import rule.NominalFeatureRule;
import rule.NumericVectorFeatureRule;

public class EmailSenderIdRule extends NumericVectorFeatureRule implements IBasicFeatureRule{

	

	public EmailSenderIdRule(String featureName, int largestSenderId) {
		super(featureName, largestSenderId);
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		double[] ids = new double[length];
		MessageData msg = aThread.getKthEarlest(0);
		int senderId = (int) msg.getAttribute(EmailDataConfig.FROM);
		ids[senderId-1]=1;
		
		return ids;
	}

}
