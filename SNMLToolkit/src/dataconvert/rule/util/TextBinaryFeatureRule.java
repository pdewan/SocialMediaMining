package dataconvert.rule.util;

import java.util.ArrayList;

import weka.core.Instance;
import dataconvert.rule.NominalSuperFeatureRule;
import dataconvert.rule.util.basic.BasicFeatureRule;
import dataimport.ThreadData;

public class TextBinaryFeatureRule extends BinaryFeatureRule {

	protected String attrName;
	protected String[] seeIfContainTheseTokens;
	protected int inOrder;
	protected int k;
	
	
	//Leave aDomain null or set \yes at pos 0, \no at pos 1
	public TextBinaryFeatureRule(String destFeatureName, 
			ArrayList<String> aDomain, 
			String attrName,
			String[] seeIfContainTheseTokens) {
		super(destFeatureName, aDomain);
		this.attrName = attrName;
		this.seeIfContainTheseTokens = seeIfContainTheseTokens;
		this.inOrder = BasicFeatureRule.ACCENDING;
		this.k = 0;
	}
	
	//Leave aDomain null or set \yes at pos 0, \no at pos 1
	public TextBinaryFeatureRule(String destFeatureName, 
			ArrayList<String> aDomain, 
			String attrName,
			int inOrder, int kthMsg,
			String[] seeIfContainTheseTokens) {
		super(destFeatureName, aDomain);
		this.attrName = attrName;
		this.seeIfContainTheseTokens = seeIfContainTheseTokens;
		
		this.inOrder = inOrder;
		this.k = kthMsg;
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		if(aThread==null) return null;
		
		BasicFeatureRule attrGeter = new BasicFeatureRule("", attrName, inOrder, k);
		String attrValue = (String)attrGeter.extract(aThread);
		if(attrValue==null) return null;
		
		for(int i=0; i < this.seeIfContainTheseTokens.length; i++){
			if(attrValue.contains(this.seeIfContainTheseTokens[i])){
				return domain.get(0);
			}
		}
		return domain.get(1);
	}

	@Override
	public Object extract(Instance anInstance) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
