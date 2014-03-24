package dataconvert;

import dataconvert.rule.DateFeatureRule;
import dataconvert.rule.basicfeature.IBasicFeatureRule;
import dataimport.ThreadData;
import dataimport.ThreadDataSet;

public class BasicFeatureExtractor extends IFeatureExtractor{
	
	public BasicFeatureExtractor(IntermediateDataSetInitializer init) {
		super(init);
	}
	
	public IntermediateDataSet extract(ThreadDataSet srcDataSet, 
			String destDataSetName,
			IBasicFeatureRule[] rules) throws Exception{
		
		if(srcDataSet==null || destDataSetName==null || rules==null){
			return null;
		}
		
		int threadNum = srcDataSet.size();	
		IntermediateDataSet destDataSet = this.destDatasetInit.initDestDataSet(destDataSetName, threadNum, rules);
			
		
		for(int threadId = 0; threadId < threadNum; threadId ++){
			if(srcDataSet.getDataInstance(threadId)==null){
				throw new Exception("null threadId");
			}
			
			ThreadData srcThread = srcDataSet.getDataInstance(threadId);
			
			IntermediateData inst = this.destDatasetInit.initADataInstance(destDataSet, rules);
			int attrId = 0;
						
			for(int i=0; i<rules.length; i++){				
				Object val = rules[i].extract(srcThread);
				if(val==null){
					attrId = inst.setMissing(destDataSet, attrId, val);
					continue;
				}
					
				rules[i].checkValid(val);
				
				if(rules[i] instanceof DateFeatureRule){
					attrId = inst.setDateAttrValue(destDataSet, attrId++, val);
				}else{	
					attrId = inst.setAttrValue(destDataSet, attrId++, val);
				}
			}
			destDataSet.addDataInstance(inst);			
		}
		
		return destDataSet;		
		
	}

}
