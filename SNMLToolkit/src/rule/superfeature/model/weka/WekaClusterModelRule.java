package rule.superfeature.model.weka;

import java.util.ArrayList;

import rule.superfeature.model.ClusterModelRule;
import weka.clusterers.Clusterer;

public abstract class WekaClusterModelRule extends ClusterModelRule implements IWekaModelRule{

	Clusterer clusterer;
	
	public WekaClusterModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}

	public WekaClusterModelRule(String featureName, int clusterOrClassNum) {
		super(featureName, clusterOrClassNum);
	}


	@Override
	public void save(String modelFilePath) throws Exception{
		weka.core.SerializationHelper.write(modelFilePath, clusterer);
	}

	@Override
	public void load(String modelFilePath) throws Exception{
		clusterer = (Clusterer) weka.core.SerializationHelper.read(modelFilePath);	
	}


}
