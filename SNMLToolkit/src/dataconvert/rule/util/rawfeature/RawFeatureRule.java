package dataconvert.rule.util.rawfeature;

import dataconvert.rule.FeatureRule;
import dataconvert.rule.basicfeature.IBasicFeatureRule;
import dataimport.MessageData;
import dataimport.ThreadData;

public class RawFeatureRule extends FeatureRule implements IBasicFeatureRule{	
	protected int k;
	protected int inOrder;
	protected String attrName;
	
	public static final int ACCENDING = 0;
	public static final int DECENDING = 1;
	
	public RawFeatureRule(String destFeatureName, 
			String srcAttrName, 
			int inOrder, int kth){
		super(destFeatureName);
		
		this.k = kth;
		this.inOrder = inOrder;
		this.attrName = srcAttrName;
	}	
	
	public boolean isValidForWeka(Object val){	
		if(!(val!=null && (val instanceof String ||
				val instanceof Integer ||
				val instanceof Double))){
			return false;
		}
		return true;
	}

	@Override
	public Object extract(ThreadData aThread) throws Exception {
		MessageData msg; 
		switch(inOrder){
		case ACCENDING:
			msg = aThread.getKthEarlest(k);
			break;
		case DECENDING:
			msg = aThread.getKthLatest(k);
			break;
		default:
			throw new Exception("parameter of constructor is wrong");
		}
		
		if(msg==null){
			return null;
		}
		Object val = msg.getAttribute(attrName);
		this.checkValid(val);
		return val;
	}


	@Override
	public void checkValid(Object val) throws Exception {
	
	}

}
