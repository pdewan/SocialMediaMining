package rule.superfeature.model.weka;

import java.util.ArrayList;

import weka.classifiers.functions.SimpleLogistic;
import weka.core.Instances;
import dataconvert.IntermediateDataSet;
import dataconvert.WekaDataSet;

public class WekaLogisticRegressionModelRule extends WekaClassifyModelRule implements IWekaModelRule{

	public WekaLogisticRegressionModelRule(String featureName,
			ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}
	
	public WekaLogisticRegressionModelRule(String featureName,
			int domainSize) {
		super(featureName, domainSize);
	}

	@Override
	public void train(IntermediateDataSet trainingSet, String[] options)
			throws Exception {
		
		
		Instances insts = ((WekaDataSet)trainingSet).getDataSet();
		
		classifier = new SimpleLogistic();		
		//((LinearRegression)classifier).setOptions(options);//TODO wrap options
		classifier.buildClassifier(insts);
		
	}

}
