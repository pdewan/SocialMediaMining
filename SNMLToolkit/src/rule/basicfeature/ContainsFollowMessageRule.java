package rule.basicfeature;


import rule.BinaryFeatureRule;
import dataimport.ThreadData;

public class ContainsFollowMessageRule extends BinaryFeatureRule implements IBasicFeatureRule{

	

	public ContainsFollowMessageRule(String destFeatureName) {
		super(destFeatureName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		if(aThread==null) return this.domain.get(1);
		double msgNum = aThread.size();
		if(msgNum >= 2){
			return this.domain.get(0);
		}else{
			return this.domain.get(1);
		}
	}

}
