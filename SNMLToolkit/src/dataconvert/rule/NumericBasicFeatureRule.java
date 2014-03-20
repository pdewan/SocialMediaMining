package dataconvert.rule;

public class NumericBasicFeatureRule extends BasicFeatureRule {

	public NumericBasicFeatureRule(String destFeatureName, String srcAttrName,
			int inOrder, int kth) {
		super(destFeatureName, srcAttrName, inOrder, kth);
	}

	@Override
	public void checkValid(Object val) throws Exception {
		if(val!=null && !(val instanceof Double)){
			if(val instanceof Integer){
				int i = ((Integer)val).intValue();
				val = new Double(i);
				return;
			}
			throw new Exception("This attr value "+val+" must be double");
		}
		
	}
	
	

}
