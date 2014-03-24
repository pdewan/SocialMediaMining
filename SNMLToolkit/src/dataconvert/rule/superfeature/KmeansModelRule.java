package dataconvert.rule.superfeature;

import java.util.ArrayList;

import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;

public abstract class KmeansModelRule extends ClusterModelRule {
	
	protected IntermediateData[] centrals;

	public KmeansModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
		centrals = new IntermediateData[aDomain.size()];
	}

	public KmeansModelRule(String featureName, int clusterOrClassNum) {
		super(featureName, clusterOrClassNum);		
		centrals = new IntermediateData[clusterOrClassNum];
	}
	


}
