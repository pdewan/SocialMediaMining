package rule.basicfeature;

import java.text.SimpleDateFormat;
import java.util.Date;

import rule.NumericFeatureRule;
import dataimport.MessageData;
import dataimport.MsgDataConfig;
import dataimport.ThreadData;
import dataimport.email.EmailDataConfig;

public class FirstResponseTimeRule extends NumericFeatureRule implements IBasicFeatureRule{

	public FirstResponseTimeRule(String destFeatureName) {
		super(destFeatureName);
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		
		MessageData msg1 = aThread.getKthEarlest(0);
		MessageData msg2 = aThread.getKthEarlest(1);
		if(msg1==null || msg2==null){
			throw new Exception("Conversation has no response");
		}
		
		String date1 = (String)msg1.getAttribute(EmailDataConfig.DATE_DEFAULT);
		String date2 = (String)msg2.getAttribute(EmailDataConfig.DATE_DEFAULT);
		
		SimpleDateFormat format = new SimpleDateFormat(MsgDataConfig.DATEFORMAT_DEFAULT);
		Date d1 = format.parse(date1);
		Date d2 = format.parse(date2);
		
		Long time = (d2.getTime()-d1.getTime())/(60*1000);
		return time.doubleValue();
	}
	
	

}
