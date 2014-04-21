package rule.superfeature.model.weka;

import java.util.ArrayList;

import rule.superfeature.model.ClassifyModelRule;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import dataconvert.IntermediateData;
import dataconvert.WekaData;
import dataconvert.WekaDataSet;

public abstract class WekaClassifyModelRule extends ClassifyModelRule implements IWekaModelRule{

	Classifier classifier;
	
	public WekaClassifyModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}	
	
	public WekaClassifyModelRule(String featureName, int domainSize) {
		super(featureName, domainSize);
	}
	
	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		double result = classifier.classifyInstance(((WekaData)anInstData).getInstValue());
		return domain.get((int)result);
	}
	
	@Override
	public void save(String modelFilePath) throws Exception{
		weka.core.SerializationHelper.write(modelFilePath, classifier);
	}

	@Override
	public void load(String modelFilePath) throws Exception{
		classifier = (Classifier) weka.core.SerializationHelper.read(modelFilePath);	
	}
	
	
	
	public void evaluate(WekaDataSet train, WekaDataSet test) throws Exception{
		Evaluation eval = new Evaluation(train.getDataSet());
		eval.evaluateModel(classifier, test.getDataSet());
		System.out.println(eval.toSummaryString("\nResults\n======\n", false));
	}

}