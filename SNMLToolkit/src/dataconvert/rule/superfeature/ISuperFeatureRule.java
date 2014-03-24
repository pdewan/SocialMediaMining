package dataconvert.rule.superfeature;

import dataconvert.IntermediateData;
import dataimport.ThreadData;

public interface ISuperFeatureRule{
	
	public String getDestFeatureName();
	
	public Object extract(IntermediateData anInstData) throws Exception;
	
	//Check if an object is a valid feature value for corresponding feature rule
	public void checkValid(Object val) throws Exception;

}
