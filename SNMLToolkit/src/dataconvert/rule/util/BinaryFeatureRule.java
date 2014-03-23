package dataconvert.rule.util;

import java.util.ArrayList;

import weka.core.Instance;
import dataconvert.rule.NominalSuperFeatureRule;
import dataimport.ThreadData;

public class BinaryFeatureRule extends NominalSuperFeatureRule {

	public BinaryFeatureRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
		if(aDomain==null){
			this.domain = new ArrayList<String>(2);
			domain.add("y");
			domain.add("n");
		}
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object extract(Instance anInstance) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
