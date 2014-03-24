package dataconvert.rule.superfeature;

import dataconvert.IntermediateData;
import dataconvert.rule.IFeatureRule;
import dataimport.ThreadData;

public interface ISuperFeatureRule extends IFeatureRule{
	
	public Object extract(IntermediateData anInstData) throws Exception;

}
