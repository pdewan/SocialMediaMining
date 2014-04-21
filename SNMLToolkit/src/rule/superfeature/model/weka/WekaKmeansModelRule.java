package rule.superfeature.model.weka;

import java.util.ArrayList;

import weka.clusterers.SimpleKMeans;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;
import dataconvert.WekaData;
import dataconvert.WekaDataSet;

public class WekaKmeansModelRule extends WekaClusterModelRule implements IWekaModelRule{
	
	public final static int SIMPLEKMEANS = 0;
	public final static int FARTHESTFIRST = 1;
	
	public WekaKmeansModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}
	
	public WekaKmeansModelRule(String featureName, int clusterOrClassNum) {
		super(featureName, clusterOrClassNum);		
	}

	@Override
	public void train(IntermediateDataSet trainingSet, String[] options) throws Exception {
		/*
		int kmeansType = Integer.parseInt(options[0]);
		switch(kmeansType){
		case SIMPLEKMEANS:
			clusterer = new SimpleKMeans();
			((SimpleKMeans)clusterer).setNumClusters(domain.size());
			break;
		case FARTHESTFIRST:
			clusterer = new FarthestFirst();
			((FarthestFirst)clusterer).setNumClusters(domain.size());
			break;
		}
		clusterer.setSeed((int)System.currentTimeMillis());
		clusterer.buildClusterer(((WekaDataSet)trainingSet).getDataSet());
		*/
		
		clusterer = new SimpleKMeans();
		((SimpleKMeans)clusterer).setNumClusters(domain.size());
		((SimpleKMeans)clusterer).setSeed((int)System.currentTimeMillis());
		clusterer.buildClusterer(((WekaDataSet)trainingSet).getDataSet());
		
		
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
		int cluster = clusterer.clusterInstance(((WekaData)anInstData).getInstValue());
		return domain.get(cluster);
	}

	@Override
	public void evaluate(WekaDataSet train, WekaDataSet test) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	

}
