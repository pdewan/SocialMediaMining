package rule.superfeature.model.weka;

import java.util.ArrayList;

import weka.classifiers.trees.J48;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;
import dataconvert.WekaData;
import dataconvert.WekaDataSet;

public class WekaDecisionTreeModelRule extends WekaClassifyModelRule implements IWekaModelRule{
	
	
	public WekaDecisionTreeModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}
	
	public WekaDecisionTreeModelRule(String featureName, int classNum) {
		super(featureName, classNum);
	}
	
		
	
	



	@Override
	public void train(IntermediateDataSet trainingSet, String[] options) throws Exception {
		
		classifier = new J48();
			
		((J48)classifier).setOptions(options);//TODO wrap options
		classifier.buildClassifier(((WekaDataSet)trainingSet).getDataSet());		
		
		
	}


}
