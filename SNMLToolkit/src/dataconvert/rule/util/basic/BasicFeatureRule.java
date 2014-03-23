package dataconvert.rule.util.basic;

import weka.core.Instance;
import dataconvert.rule.FeatureRule;
import dataimport.MessageData;
import dataimport.ThreadData;

public class BasicFeatureRule extends FeatureRule{	
	protected int k;
	protected int inOrder;
	protected String attrName;
	
	public static final int ACCENDING = 0;
	public static final int DECENDING = 1;
	
	public BasicFeatureRule(String destFeatureName, 
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
	public Object extract(Instance anInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkValid(Object val) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
