package dataconvert;

import java.util.ArrayList;

import rule.DateFeatureRule;
import rule.IFeatureRule;
import rule.NominalFeatureRule;
import rule.NumericFeatureRule;
import rule.NumericVectorFeatureRule;
import rule.StringFeatureRule;
import weka.core.Attribute;
import weka.core.Instances;
import dataimport.MsgDataConfig;

public class WekaDataSetInitializer implements IntermediateDataSetInitializer {
	
	protected int attrNum;

	@Override
	public IntermediateDataSet initDestDataSet(String destDataSetName,
			int threadNum, IFeatureRule[] rules) throws Exception {
		
		ArrayList<Attribute> attributes = new ArrayList<Attribute>(rules.length);
		for(int i=0; i<rules.length; i++){

			if(rules[i] instanceof NumericVectorFeatureRule){
				int l = ((NumericVectorFeatureRule)rules[i]).length;
				for(int j=0; j<l; j++){
					Attribute attr = new Attribute(rules[i].getDestFeatureName()+j);
					attributes.add(attr);	
				}
			}
			else{
				Attribute attr;

				if(rules[i] instanceof DateFeatureRule){
					attr = new Attribute(rules[i].getDestFeatureName(), MsgDataConfig.DATEFORMAT_DEFAULT);
				}
				else if(rules[i] instanceof NumericFeatureRule){
					attr = new Attribute(rules[i].getDestFeatureName());
				}
				else if(rules[i] instanceof NominalFeatureRule){
					ArrayList<String> domain = ((NominalFeatureRule)rules[i]).getDomain();
					attr = new Attribute(rules[i].getDestFeatureName(), domain);
				}
				else if(rules[i] instanceof StringFeatureRule){
					ArrayList<String> domain = null;
					attr = new Attribute(rules[i].getDestFeatureName(), domain);
				}
				else if(rules[i] instanceof DateFeatureRule){
					attr = new Attribute(rules[i].getDestFeatureName(), MsgDataConfig.DATEFORMAT_DEFAULT);
				}
				else{
					throw new Exception("What kind of feature rule it is!?");
				}
				attributes.add(attr);
			}
		}

		attributes.trimToSize();
		attrNum = attributes.size();
		
		Instances dataset = new Instances(destDataSetName, attributes, threadNum);		

		return new WekaDataSet(dataset);
	}

	@Override
	public IntermediateData initADataInstance(
			IntermediateDataSet relatedDataset, IFeatureRule[] rules)
			throws Exception {
		
		WekaData inst = new WekaData(attrNum);
		inst.setRelatedDataset((WekaDataSet)relatedDataset);
		return inst;
	}

}
