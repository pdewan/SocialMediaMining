package dataconvert.rule;

import java.util.ArrayList;

public abstract class NominalSuperFeatureRule extends FeatureRule {
	protected ArrayList<String> domain;
	
	public NominalSuperFeatureRule(String featureName, ArrayList<String> aDomain){
		super(featureName);
		domain = aDomain;
	}
	
	public ArrayList<String> getDomain(){
		return domain;
	}
	
	//Check if a string is in its domain.
	@Override
	public void checkValid(Object val) throws Exception{
		/*
		if(val==null){
			System.out.println("Error in NominalSuperFeatureRule.isValid: " + 
					"parameter is null");
			return false;
		}
		*/
		if(val!=null){
			if(val instanceof String){
				for(int i=0; i < domain.size(); i++){
					if(domain.get(i).equals(val)){
						return;
					}
				}
			}
			throw new Exception("invalid attribute value");
		}
		
	}

}
