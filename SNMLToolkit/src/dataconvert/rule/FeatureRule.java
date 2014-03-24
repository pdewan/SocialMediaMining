package dataconvert.rule;

public abstract class FeatureRule {
	
	protected String destFeatureName;	
	
	public FeatureRule(String destFeatureName){
		this.destFeatureName = destFeatureName;
	}
	
	public String getDestFeatureName(){
		return this.destFeatureName;
	}
	
	//Check if an object is a valid feature value for corresponding feature rule
	public abstract void checkValid(Object val) throws Exception;
}
