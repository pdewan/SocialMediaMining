package dataconvert.rule.basicfeature;

import dataconvert.rule.IFeatureRule;
import dataimport.ThreadData;

public interface IBasicFeatureRule extends IFeatureRule{
	
	public Object extract(ThreadData aThread) throws Exception;
	
}
