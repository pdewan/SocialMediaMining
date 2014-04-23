package rule.superfeature.model.weka;

import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SimpleLinearRegression;
import weka.core.Instances;
import dataconvert.IntermediateDataSet;
import dataconvert.WekaDataSet;

public class WekaLinearRegressionModelRule extends WekaRegressionModelRule implements IWekaModelRule{

	public WekaLinearRegressionModelRule(String featureName) {
		super(featureName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void train(IntermediateDataSet trainingSet, String[] options)
			throws Exception {
		
		Instances insts = ((WekaDataSet)trainingSet).getDataSet();
		
		
		classifier = new SimpleLinearRegression();
		
		//((LinearRegression)classifier).setOptions(options);//TODO wrap options
		classifier.buildClassifier(insts);	
		
	}

	
}
