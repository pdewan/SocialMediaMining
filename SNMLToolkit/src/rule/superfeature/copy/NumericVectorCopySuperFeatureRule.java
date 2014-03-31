package rule.superfeature.copy;

import dataconvert.IntermediateData;
import rule.NumericVectorFeatureRule;
import rule.superfeature.ISuperFeatureRule;

public class NumericVectorCopySuperFeatureRule 
	extends NumericVectorFeatureRule  
	implements ISuperFeatureRule {

	protected String srcAttrName;
	
	public NumericVectorCopySuperFeatureRule(String destFeatureName,
			String srcAttrName, int length) {
		super(destFeatureName, length);
		this.srcAttrName = srcAttrName;
	}
	
	

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		double[] val = new double[length];
		for(int i=0; i<length; i++){
			val[i] = anInstData.getNumericAttrValue(srcAttrName+i);
		}
		return val;
	}

}
