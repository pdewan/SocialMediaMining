package rule.superfeature;

import dataconvert.IntermediateData;

public class WeightedSumRule extends NumericSuperFeatureRule implements ISuperFeatureRule{

	double[] weight;
	
	public WeightedSumRule(String featureName, double[] weight) {
		super(featureName);
		this.weight = weight;
	}

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		int n = weight.length;
		double sum = 0;
		for(int i=0; i<n; i++){
			sum += weight[i] * anInstData.getNumericAttrValue(i);
		}
		return sum;
	}

}
