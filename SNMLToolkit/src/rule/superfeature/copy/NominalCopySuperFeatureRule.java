package rule.superfeature.copy;

import java.util.ArrayList;

import rule.NominalFeatureRule;
import rule.superfeature.ISuperFeatureRule;
import dataconvert.IntermediateData;

public class NominalCopySuperFeatureRule extends NominalFeatureRule implements ISuperFeatureRule {
	
	protected String srcAttrName;
	
	public NominalCopySuperFeatureRule(String destFeatureName,
			String srcAttrName, ArrayList<String> aDomain) {
		super(destFeatureName, aDomain);
		this.srcAttrName = srcAttrName;
	}
	

	@Override
	public Object extract(IntermediateData anInstData) throws Exception {
		return anInstData.getStringAttrValue(srcAttrName);
	}

}
