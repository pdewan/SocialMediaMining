package dataconvert;

import dataconvert.rule.DateFeatureRule;
import dataconvert.rule.superfeature.ISuperFeatureRule;

public class SuperFeatureExtractor extends IFeatureExtractor{
	
	
	public SuperFeatureExtractor(IntermediateDataSetInitializer init) {
		super(init);
	}

	public IntermediateDataSet extract(IntermediateDataSet srcDataSet, 
			String destDataSetName,
			ISuperFeatureRule[] rules) throws Exception{
		
		if(srcDataSet==null || destDataSetName==null || rules==null){
			return null;
		}
		
		int threadNum = srcDataSet.size();	
		IntermediateDataSet destDataSet = this.destDatasetInit.initDestDataSet(destDataSetName, threadNum, rules);
			
		
		for(int threadId = 1; threadId < threadNum; threadId ++){
			if(srcDataSet.getDataInstance(threadId)==null){
				throw new Exception("null threadId");
			}
			
			IntermediateData srcData = srcDataSet.getDataInstance(threadId);
			
			IntermediateData inst = this.destDatasetInit.initADataInstance(destDataSet, rules);
			int attrId = 0;
						
			for(int i=0; i<rules.length; i++){				
				Object val = rules[i].extract(srcData);
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
