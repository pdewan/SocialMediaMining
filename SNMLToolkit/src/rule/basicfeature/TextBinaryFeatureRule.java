package rule.basicfeature;

import rule.BinaryFeatureRule;
import rule.basicfeature.copyraw.RawFeatureRule;
import dataimport.ThreadData;

public class TextBinaryFeatureRule extends BinaryFeatureRule implements IBasicFeatureRule {

	protected int k;
	protected int inOrder;
	protected String attrName;
	protected String[] seeIfContainTheseTokens;
	
	public static final int ACCENDING = 0;
	public static final int DECENDING = 1;
	
	public TextBinaryFeatureRule(String destFeatureName, 
			String srcAttrName, 
			String[] seeIfContainTheseTokens){
		super(destFeatureName);
		
		this.k = 0;
		this.inOrder = ACCENDING;
		this.attrName = srcAttrName;
		this.seeIfContainTheseTokens = seeIfContainTheseTokens;
	}
	
	public TextBinaryFeatureRule(String destFeatureName, 
			String srcAttrName, 
			int inOrder, int kth, 
			String[] seeIfContainTheseTokens){
		super(destFeatureName);
		
		this.k = kth;
		this.inOrder = inOrder;
		this.attrName = srcAttrName;
		this.seeIfContainTheseTokens = seeIfContainTheseTokens;
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		RawFeatureRule rawRule = new RawFeatureRule("", attrName, inOrder, k);
		if(aThread==null) return null;
		
		String attrValue = (String)rawRule.extract(aThread);
		if(attrValue==null) return null;
		
		for(int i=0; i < this.seeIfContainTheseTokens.length; i++){
			if(attrValue.contains(this.seeIfContainTheseTokens[i])){
				return domain.get(0);
			}
		}
		return domain.get(1);
	}

}
