package dataconvert.rule.superfeature;

import java.util.ArrayList;

import weka.clusterers.SimpleKMeans;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;
import dataconvert.WekaData;
import dataconvert.WekaDataSet;
import dataimport.ThreadData;

public class WekaKmeansModelRule extends KmeansModelRule {
	
	SimpleKMeans kmeans;
	
	public WekaKmeansModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}
	
	public WekaKmeansModelRule(String featureName, int clusterOrClassNum) {
		super(featureName, clusterOrClassNum);		
	}

	@Override
	public void train(IntermediateDataSet trainingSet) throws Exception {
		kmeans = new SimpleKMeans();

		kmeans.setSeed((int)System.currentTimeMillis());

		// This is the important parameter to set
		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(domain.size());
		kmeans.buildClusterer(((WekaDataSet)trainingSet).getDataSet());

	}


	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		/*
		double[] prob = kmeans.distributionForInstance(((WekaData)anInstData).getInstValue());
		int cluster = 0;
		double maxProb = 0;
		for(int i=0; i<prob.length; i++){
			if(maxProb < prob[i]){
				maxProb = prob[i];
				cluster = i;
			}
		}
		*/
		int cluster = kmeans.clusterInstance(((WekaData)anInstData).getInstValue());
		return domain.get(cluster);
	}

}
