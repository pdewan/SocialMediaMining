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
		
		if(!(val instanceof Double || val instanceof Integer)){
			throw new Exception("This attr value must be double/int");
		}
		if(val instanceof Integer){
			val = new Double(((Integer) val).doubleValue());
		}
	}

}
