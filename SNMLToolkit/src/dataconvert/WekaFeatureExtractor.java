package dataconvert;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Instances;
import dataconvert.rule.DateSuperFeatureRule;
import dataconvert.rule.FeatureRule;
import dataconvert.rule.NominalSuperFeatureRule;
import dataconvert.rule.NumericSuperFeatureRule;
import dataconvert.rule.NumericVectorSuperFeatureRule;
import dataconvert.rule.util.basic.DateBasicFeatureRule;
import dataconvert.rule.util.basic.NominalBasicFeatureRule;
import dataconvert.rule.util.basic.NumericBasicFeatureRule;
import dataimport.MsgDataConfig;

public class WekaFeatureExtractor extends FeatureExtractor {

	@Override
	public IntermediateDataSet initDestDataSet(String destDataSetName,
			int threadNum, FeatureRule[] rules) throws Exception {
		
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
			
				if(rules[i] instanceof DateBasicFeatureRule || 
						rules[i] instanceof DateSuperFeatureRule){
					attr = new Attribute(rules[i].getDestFeatureName(), MsgDataConfig.DATEFORMAT);
				}
				else if(rules[i] instanceof NumericBasicFeatureRule || 
						rules[i] instanceof NumericSuperFeatureRule){
					attr = new Attribute(rules[i].getDestFeatureName());
				}
				else if(rules[i] instanceof NominalBasicFeatureRule || 
						rules[i] instanceof NominalSuperFeatureRule){
					ArrayList<String> domain;
					if(rules[i] instanceof NominalBasicFeatureRule){
						domain = ((NominalBasicFeatureRule)rules[i]).getDomain();
					}else{
						domain = ((NominalSuperFeatureRule)rules[i]).getDomain();
					}
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
	public IntermediateData initADataInstance(FeatureRule[] rules) {
		WekaData inst = new WekaData(rules.length);
		return inst;
	}

}
