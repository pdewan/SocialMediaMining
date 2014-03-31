package rule.superfeature.model;

import rule.superfeature.ISuperFeatureRule;
import dataconvert.IntermediateDataSet;

public interface IModelRule extends ISuperFeatureRule{
	
	public void train(IntermediateDataSet trainingSet, String[] options) throws Exception;

	public void save(String modelFilePath) throws Exception;
	public void load(String modelFilePath) throws Exception;
	
	//public void evaluate(IntermediateDataSet testSet) throws Exception;
	
}
