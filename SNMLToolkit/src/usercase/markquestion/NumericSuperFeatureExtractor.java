package usercase.markquestion;

import java.io.IOException;
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class NumericSuperFeatureExtractor extends SuperFeatureExtractor {

	protected NumericSuperFeatureRule rule;
	
	public NumericSuperFeatureExtractor(NumericSuperFeatureRule rule){
		this.rule = rule;
	}

	@Override
	public void extract(Instances instances, String superfeatureNameAndType) throws IOException {
		
		super.extract(instances, superfeatureNameAndType);		
			
		instances.insertAttributeAt(new Attribute(this.superfeatureName), instances.numAttributes());
		
		int n = instances.size();
		for(int i=0; i<n; i++){
			Instance instance = instances.get(i);
			double v = this.rule.condition(instance);			
			instance.setValue(instance.numAttributes()-1, v);
		}		

	}

}
