package dataconvert.rule.superfeature;

import java.util.ArrayList;

import weka.core.Instance;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;
import dataimport.ThreadData;

public class ClusterModelRule extends NominalSuperFeatureRule{
	
	public ClusterModelRule(String featureName, int clusterOrClassNum) {
		super(featureName, new ArrayList<String>(clusterOrClassNum));
		
		for(int k=0; k<clusterOrClassNum; k++){
			domain.add(String.valueOf(k));
		}
		
	}

	public ClusterModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
		
	}
	
	public void train(IntermediateDataSet trainingSet)  throws Exception{
		
	}

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkValid(Object val) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
