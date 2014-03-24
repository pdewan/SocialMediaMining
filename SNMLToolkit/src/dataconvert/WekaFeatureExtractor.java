package dataconvert;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Instances;
import dataconvert.rule.DateFeatureRule;
import dataconvert.rule.NominalFeatureRule;
import dataconvert.rule.NumericFeatureRule;
import dataconvert.rule.basicfeature.IBasicFeatureRule;
import dataconvert.rule.superfeature.DateSuperFeatureRule;
import dataconvert.rule.superfeature.NominalSuperFeatureRule;
import dataconvert.rule.superfeature.NumericSuperFeatureRule;
import dataconvert.rule.superfeature.NumericVectorSuperFeatureRule;
import dataconvert.rule.util.rawfeature.DateRawFeatureRule;
import dataconvert.rule.util.rawfeature.NominalRawFeatureRule;
import dataconvert.rule.util.rawfeature.NumericRawFeatureRule;
import dataimport.MsgDataConfig;

public class WekaFeatureExtractor extends FeatureExtractor {

	@Override
	public IntermediateDataSet initDestDataSet(String destDataSetName,
			int threadNum, IBasicFeatureRule[] rules) throws Exception {
		
		ArrayList<Attribute> attributes = new ArrayList<Attribute>(rules.length);
		for(int i=0; i<rules.length; i++){
			
			if(rules[i] instanceof NumericVectorSuperFeatureRule){
				int l = ((NumericVectorSuperFeatureRule)rules[i]).length;
				for(int j=0; j<l; j++){
					Attribute attr = new Attribute(rules[i].getDestFeatureName()+j);
					attributes.add(attr);	
				}
			}
			else{
				Attribute attr;
			
				if(rules[i] instanceof DateFeatureRule){
					attr = new Attribute(rules[i].getDestFeatureName(), MsgDataConfig.DATEFORMAT);
				}
				else if(rules[i] instanceof NumericFeatureRule){
					attr = new Attribute(rules[i].getDestFeatureName());
				}
				else if(rules[i] instanceof NominalFeatureRule){
					ArrayList<String> domain = ((NominalFeatureRule)rules[i]).getDomain();
					attr = new Attribute(rules[i].getDestFeatureName(), domain);
				}
				else{
					throw new Exception("What kind of feature rule it is!?");
				}
				attributes.add(attr);
			}
		}
				
		Instances dataset = new Instances(destDataSetName, attributes, threadNum);		
		
		return new WekaDataSet(dataset);
		
		/*
		//add attributes of destDataSet
				ArrayList<Attribute> attributes = this.setAttributes(rules);
				int newAttributeNum = attributes.size();		
				
				for(int i=0; i<newAttributeNum; i++){
					destDataSet.insertAttributeAt(attributes.get(i), destDataSet.numAttributes());
				}
				*/
	}

	@Override
	public IntermediateData initADataInstance(
			IntermediateDataSet relatedDataset, IBasicFeatureRule[] rules) {
		
		WekaData inst = new WekaData(rules.length);
		inst.setRelatedDataset((WekaDataSet)relatedDataset);
		return inst;
	}

}
