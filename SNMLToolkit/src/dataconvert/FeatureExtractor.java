package dataconvert;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import dataconvert.rule.DateBasicFeatureRule;
import dataconvert.rule.DateSuperFeatureRule;
import dataconvert.rule.FeatureRule;
import dataconvert.rule.NominalBasicFeatureRule;
import dataconvert.rule.NominalSuperFeatureRule;
import dataconvert.rule.NumericBasicFeatureRule;
import dataconvert.rule.NumericSuperFeatureRule;
import dataconvert.rule.NumericVectorSuperFeatureRule;
import dataimport.ThreadData;
import dataimport.ThreadDataSet;

/*
 * Extract (super)features from weka.core.Instance / ThreadData format data to 
 * weka.core.Instance format data
 */
public class FeatureExtractor {
	
	private ArrayList<Attribute> setAttributes(FeatureRule[] rules) throws Exception{
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
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
					attr = new Attribute(rules[i].getDestFeatureName(), "yyyy-MM-dd HH:mm:ss");
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
		
		return attributes;
	}
	
	public Instances newDestDataSet(int threadNum, 
			String destDataSetName) throws Exception{
		
		Instances destDataSet = new Instances(destDataSetName, new ArrayList<Attribute>(), threadNum);		
		return destDataSet;
	}
	
	public void extract(ThreadDataSet srcDataSet, 
			Instances destDataSet, String destDataSetName,
			FeatureRule[] rules) throws Exception{
		
		if(srcDataSet==null || destDataSet==null || destDataSetName==null || rules==null){
			return;
		}
		
		int threadNum = srcDataSet.size();		
		if(	!(destDataSet.size()==0 && destDataSet.numAttributes()==0) && 
				destDataSet.size()!=threadNum){
			throw new Exception("Thread numbers do not match");
		}		
		
		//add attributes of destDataSet
		ArrayList<Attribute> attributes = this.setAttributes(rules);
		int newAttributeNum = attributes.size();		
		
		for(int i=0; i<newAttributeNum; i++){
			destDataSet.insertAttributeAt(attributes.get(i), destDataSet.numAttributes());
		}
	
		
		
		boolean wasDestDataSetEmpty = false;
		if(destDataSet.size()==0){
			wasDestDataSetEmpty = true;
		}
		
		for(int threadId = 0; threadId < threadNum; threadId ++){
			if(srcDataSet.getThreadData(threadId)==null){
				throw new Exception("null threadId");
				//continue;
			}
			
			ThreadData srcThread = srcDataSet.getThreadData(threadId);
			
			Instance inst;
			int attrId = destDataSet.numAttributes() - newAttributeNum;
			if(wasDestDataSetEmpty){        
				inst = new DenseInstance(destDataSet.numAttributes());
			}else{
				inst = destDataSet.get(threadId);
			}
						
			for(int i=0; i<rules.length; i++){
				if(rules[i] instanceof NumericVectorSuperFeatureRule){
					double[] v =  (double[]) ((NumericVectorSuperFeatureRule)rules[i]).extract(srcThread);			
					for(int j=0; j<v.length; j++){
						//instanceValue[attrId++] = v[j];
						inst.setValue(attrId++, v[j]);
					}
				}
				else{
					Object val = rules[i].extract(srcThread);
					if(val==null){
						//inst.setMissing(attributes.get(attrId++));
						attrId++;
						continue;
					}
					
					rules[i].checkValid(val);
					if(val instanceof Double){
						inst.setValue(attrId++, (Double)val);
					}
					else if(val instanceof String){
						if(rules[i] instanceof DateBasicFeatureRule || 
								rules[i] instanceof DateSuperFeatureRule){
							double time = destDataSet.attribute(attrId).parseDate((String)val);
							inst.setValue(attrId, time);
							attrId++;
						}else{
							inst.setValue(attributes.get(attrId++), (String)val);
						}
					}else{
						throw new Exception("invalid feature value type");
					}
				}
			}
			destDataSet.add(inst);
			//inst.setDataset(destDataSet);
			
		}
		
		
		
	}
	
	
	

}
