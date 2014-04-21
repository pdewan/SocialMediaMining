package rule.basicfeature;

import dataimport.MessageData;
import dataimport.ThreadData;
import rule.NumericFeatureRule;

public class ResponseAvgCharLength extends NumericFeatureRule implements IBasicFeatureRule{

	String field;
	
	public ResponseAvgCharLength(String destFeatureName, String responseField) {
		super(destFeatureName);
		this.field = responseField;
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		int length = 0;
		int responseNum = aThread.size()-1;
		for(int i=1; i<=responseNum; i++){
			MessageData msg = aThread.getKthEarlest(i);
			String ans = (String)msg.getAttribute(field);
			length += ans.length();
		}
		if(responseNum>=1){
			length /= responseNum;
		}
		return length;
	}

}
