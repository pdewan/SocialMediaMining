package rule.superfeature;

import rule.IFeatureRule;
import dataconvert.IntermediateData;

public interface ISuperFeatureRule extends IFeatureRule{
	
	public Object extract(IntermediateData anInstData) throws Exception;

}
