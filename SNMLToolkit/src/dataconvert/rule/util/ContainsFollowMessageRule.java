package dataconvert.rule.util;

import java.util.ArrayList;

import weka.core.Instance;
import dataconvert.rule.NominalSuperFeatureRule;
import dataimport.ThreadData;

public class ContainsFollowMessageRule extends BinaryFeatureRule {

	//please make domain yes & no
	public ContainsFollowMessageRule(String featureName,
			ArrayList<String> aDomain) {
		super(featureName, aDomain);
		if(aDomain==null){
			this.domain = new ArrayList<String>(2);
			domain.add("y");
			domain.add("n");
		}
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

	@Override
	public Object extract(Instance anInstance) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
