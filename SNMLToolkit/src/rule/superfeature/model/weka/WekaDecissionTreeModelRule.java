package rule.superfeature.model.weka;

import java.util.ArrayList;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;
import dataconvert.WekaData;
import dataconvert.WekaDataSet;

public class WekaDecissionTreeModelRule extends WekaClassifyModelRule implements IWekaModelRule{
	
	
	public WekaDecissionTreeModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}
	
	public WekaDecissionTreeModelRule(String featureName, int classNum) {
		super(featureName, classNum);
	}
	
		
	
	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		double result = classifier.classifyInstance(((WekaData)anInstData).getInstValue());
		return domain.get((int)result);
	}



	@Override
	public void train(IntermediateDataSet trainingSet, String[] options) throws Exception {
		
		classifier = new J48();
			
		((J48)classifier).setOptions(options);//TODO wrap options
		classifier.buildClassifier(((WekaDataSet)trainingSet).getDataSet());		
		
		
	}


}
