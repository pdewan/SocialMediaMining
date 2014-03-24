package dataconvert.rule.superfeature;

import java.util.ArrayList;

import weka.clusterers.SimpleKMeans;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;
import dataconvert.WekaDataSet;
import dataimport.ThreadData;

public class WekaKmeansModelRule extends KmeansModelRule {
	
	public WekaKmeansModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}
	
	public WekaKmeansModelRule(String featureName, int clusterOrClassNum) {
		super(featureName, clusterOrClassNum);		
	}

	@Override
	public void train(IntermediateDataSet trainingSet) throws Exception {
		SimpleKMeans kmeans = new SimpleKMeans();

		kmeans.setSeed((int)System.currentTimeMillis());

		// This is the important parameter to set
		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(domain.size());
		kmeans.buildClusterer(((WekaDataSet)trainingSet).getDataSet());

	}


	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
