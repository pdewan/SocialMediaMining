package rule.superfeature.model.mallet;

import java.io.File;

import cc.mallet.topics.SimpleLDA;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;

public class MalletSimpleLDAModelRule extends MalletTopicModelRule implements IMalletModelRule{

	SimpleLDA model;
	
	public MalletSimpleLDAModelRule(String featureName, String[] attrNames,
			int l) {
		super(featureName, attrNames, l);
	}

	@Override
	public void train(IntermediateDataSet trainingSet, String[] options)
			throws Exception {
		//super.train(trainingSet, options);
		
		model = new SimpleLDA(this.length);
		model.setRandomSeed((int)System.currentTimeMillis());
		//model.
	}
	
	@Override
	public void save(String modelFilePath) throws Exception {
		File file = new File(modelFilePath);		
		model.write(file);		
	}

	@Override
	public void load(String modelFilePath) throws Exception {
		File file = new File(modelFilePath);	
		//model = 
	}

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
