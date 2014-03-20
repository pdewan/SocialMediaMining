package dataconvert.rule;

import java.lang.reflect.Array;

public abstract class NumericVectorSuperFeatureRule extends FeatureRule{
	public int length = 0;
	
	//for the same dataset, the rule must return vector with the same length
	public NumericVectorSuperFeatureRule(String featureName, int l){
		super(featureName);
		length = l;
	}
	
	@Override
	public void checkValid(Object val) throws Exception{
		/*
		if(val==null){
			System.out.println("Error in NumericVectorSuperFeatureRule.isValid: " + 
					"parameter is null");
			return false;
		}
		*/
		if(val!=null){
			if(val instanceof double[] || val instanceof Double[]){
			
				if(Array.getLength(val)!=length){
					throw new Exception("vector length does not match");
				}
			}else{
				throw new Exception("only double vector allowed to be returned");		
			}
		}
	}

}
