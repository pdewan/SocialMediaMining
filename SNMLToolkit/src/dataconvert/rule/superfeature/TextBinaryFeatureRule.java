package dataconvert.rule.superfeature;

import java.util.ArrayList;

import dataconvert.IntermediateData;
import dataconvert.rule.BinaryFeatureRule;

public class TextBinaryFeatureRule extends BinaryFeatureRule implements ISuperFeatureRule{

	protected String attrName;
	protected String[] seeIfContainTheseTokens;
		
	//Leave aDomain null or set \yes at pos 0, \no at pos 1
	public TextBinaryFeatureRule(String destFeatureName, 
			ArrayList<String> aDomain, 
			String attrName,
			String[] seeIfContainTheseTokens) {
		super(destFeatureName, aDomain);
		this.attrName = attrName;
		this.seeIfContainTheseTokens = seeIfContainTheseTokens;
		
	}


	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		if(anInstData==null) return null;
		
		String attrValue = anInstData.getStringAttrValue(attrName);
		if(attrValue==null) return null;
		
		for(int i=0; i < this.seeIfContainTheseTokens.length; i++){
			if(attrValue.contains(this.seeIfContainTheseTokens[i])){
				return domain.get(0);
			}
		}
		return domain.get(1);
	}

}
