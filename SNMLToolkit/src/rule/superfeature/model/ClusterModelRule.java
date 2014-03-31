package rule.superfeature.model;

import java.util.ArrayList;

import rule.superfeature.NominalSuperFeatureRule;
import dataconvert.IntermediateData;

public abstract class ClusterModelRule extends NominalSuperFeatureRule implements IModelRule{

	public ClusterModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}
	
	public ClusterModelRule(String featureName, int clusterNum) {
		super(featureName, new ArrayList<String>(clusterNum));
		for(int i=0; i< clusterNum; i++){
			domain.add(String.valueOf(i));
		}
	}

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
