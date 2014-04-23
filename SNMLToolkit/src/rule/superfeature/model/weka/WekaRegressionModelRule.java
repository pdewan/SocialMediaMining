package rule.superfeature.model.weka;

import dataconvert.IntermediateData;
import dataconvert.WekaData;
import dataconvert.WekaDataSet;
import rule.superfeature.model.NumericModelRule;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instance;
import weka.core.Instances;

public abstract class WekaRegressionModelRule extends NumericModelRule implements IWekaModelRule{

	Classifier classifier;
	
	public WekaRegressionModelRule(String featureName) {
		super(featureName);
	}

	@Override
	public void save(String modelFilePath) throws Exception{
		weka.core.SerializationHelper.write(modelFilePath, classifier);
	}

	@Override
	public void load(String modelFilePath) throws Exception{
		classifier = (Classifier) weka.core.SerializationHelper.read(modelFilePath);	
	}
	
	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		Instance inst = ((WekaData)anInstData).getInstValue();
		double result = classifier.classifyInstance(inst);
		return result;
	}
	
	
	
	public void evaluate(WekaDataSet train, WekaDataSet test) throws Exception{
		Evaluation eval = new Evaluation(train.getDataSet());
		eval.evaluateModel(classifier, test.getDataSet());
		System.out.println(eval.toSummaryString("\nResults\n======\n", false));
	}

}
