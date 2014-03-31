package rule.superfeature.model.mallet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import rule.superfeature.model.ClassifyModelRule;
import cc.mallet.classify.Classifier;
import dataconvert.IntermediateData;
import dataconvert.IntermediateDataSet;

public abstract class MalletClassifyModelRule extends ClassifyModelRule {

	Classifier classifier;
	String srcAttrName;
	
	public MalletClassifyModelRule(String featureName, String srcAttrName, ArrayList<String> aDomain) {
		super(featureName, aDomain);
		this.srcAttrName = srcAttrName;
	}

	@Override
	public void train(IntermediateDataSet trainingSet,  String[] options)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String modelFilePath) throws Exception {
		File file = new File(modelFilePath);
		
		ObjectOutputStream oos =
	            new ObjectOutputStream(new FileOutputStream (file));
	    oos.writeObject (classifier);
	    oos.close();

	}

	@Override
	public void load(String modelFilePath) throws Exception {
		File file = new File(modelFilePath);
		
		ObjectInputStream ois =
	            new ObjectInputStream (new FileInputStream (file));
	    classifier = (Classifier) ois.readObject();
	    ois.close();
	}

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
