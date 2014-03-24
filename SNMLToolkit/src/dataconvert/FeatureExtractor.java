package dataconvert;

import dataconvert.rule.basicfeature.IBasicFeatureRule;
import dataconvert.rule.superfeature.DateSuperFeatureRule;
import dataconvert.rule.util.rawfeature.DateRawFeatureRule;
import dataimport.ThreadData;
import dataimport.ThreadDataSet;

public abstract class FeatureExtractor {
	
	protected abstract IntermediateDataSet initDestDataSet(
			String destDataSetName, int threadNum, IBasicFeatureRule[] rules) throws Exception;
	
	protected abstract IntermediateData initADataInstance(
			IntermediateDataSet relatedDataset, IBasicFeatureRule[] rules);
	
	
	public IntermediateDataSet extract(ThreadDataSet srcDataSet, 
			String destDataSetName,
			IBasicFeatureRule[] rules) throws Exception{
		
		if(srcDataSet==null || destDataSetName==null || rules==null){
			return null;
		}
		
		int threadNum = srcDataSet.size();	
		IntermediateDataSet destDataSet = this.initDestDataSet(destDataSetName, threadNum, rules);
			
		
		for(int threadId = 0; threadId < threadNum; threadId ++){
			if(srcDataSet.getThreadData(threadId)==null){
				throw new Exception("null threadId");
			}
			
			ThreadData srcThread = srcDataSet.getThreadData(threadId);
			
			IntermediateData inst = this.initADataInstance(destDataSet, rules);
			int attrId = 0;
						
			for(int i=0; i<rules.length; i++){				
				Object val = rules[i].extract(srcThread);
				if(val==null){
					attrId = inst.setMissing(destDataSet, attrId, val);
					continue;
				}
					
				rules[i].checkValid(val);
				
				if(rules[i] instanceof DateRawFeatureRule || 
						rules[i] instanceof DateSuperFeatureRule){
					attrId = inst.setDateAttrValue(destDataSet, attrId++, val);
				}else{	
					attrId = inst.setAttrValue(destDataSet, attrId++, val);
				}
			}
			destDataSet.addInstance(inst);			
		}
		
		return destDataSet;		
		
	}

}
