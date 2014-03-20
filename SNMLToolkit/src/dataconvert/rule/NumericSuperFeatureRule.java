package dataconvert.rule;

public abstract class NumericSuperFeatureRule extends FeatureRule{
	
	public NumericSuperFeatureRule(String destFeatureName){
		super(destFeatureName);
	}
	
	@Override
	public void checkValid(Object val) throws Exception{
		/*
		if(val==null){
			System.out.println("Error in NumericSuperFeatureRule.isValid: " + 
					"parameter is null");
			return false;
		}
		*/
		
		if(!(val instanceof Double)){
			throw new Exception("This attr value must be double");
		}
	}

}
