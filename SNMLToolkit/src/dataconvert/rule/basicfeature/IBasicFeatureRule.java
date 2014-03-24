package dataconvert.rule.basicfeature;

import dataimport.ThreadData;

public interface IBasicFeatureRule{
	
	public String getDestFeatureName();
	
	public Object extract(ThreadData aThread) throws Exception;
	
	//Check if an object is a valid feature value for corresponding feature rule
	public void checkValid(Object val) throws Exception;
	
}
