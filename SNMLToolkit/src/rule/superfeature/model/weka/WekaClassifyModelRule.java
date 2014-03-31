package rule.superfeature.model.weka;

import java.util.ArrayList;

import rule.superfeature.model.ClassifyModelRule;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import dataconvert.WekaDataSet;

public abstract class WekaClassifyModelRule extends ClassifyModelRule implements IWekaModelRule{

	Classifier classifier;
	
	public WekaClassifyModelRule(String featureName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
	}
	
	public WekaClassifyModelRule(String featureName, int classNum) {
		super(featureName, classNum);
	}
	
	@Override
	public void save(String modelFilePath) throws Exception{
		weka.core.SerializationHelper.write(modelFilePath, classifier);
	}

	@Override
	public void load(String modelFilePath) throws Exception{
		classifier = (Classifier) weka.core.SerializationHelper.read(modelFilePath);	
	}
	
	public void setTargetIndex(WekaDataSet dataset, int index) throws Exception {
		Instances insts = dataset.getDataSet();
		if(insts.classIndex()<0){
			insts.setClassIndex(insts.numAttributes()-1);
		}
	}
	
	public void evaluate(WekaDataSet train, WekaDataSet test) throws Exception{
		Evaluation eval = new Evaluation(train.getDataSet());
		eval.evaluateModel(classifier, test.getDataSet());
		System.out.println(eval.toSummaryString("\nResults\n======\n", false));
	}

}
