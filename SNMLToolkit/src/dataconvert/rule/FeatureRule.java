package dataconvert.rule;

import weka.core.Instance;
import dataimport.ThreadData;

public abstract class FeatureRule {
	
	protected String destFeatureName;	
	
	public FeatureRule(String aName){
		this.destFeatureName = aName;
	}
	
	public String getDestFeatureName(){
		return this.destFeatureName;
	}
	
	/* The return value can only be numeric(int, double, etc), String, or numeric array
	 * Only one of the two extract methods needed to be implemented, based on need
	 */
	public abstract Object extract(ThreadData aThread) throws Exception;
	public abstract Object extract(Instance anInstance) throws Exception;

	//Check if an object is a valid feature value for corresponding feature rule
	public abstract void checkValid(Object val) throws Exception;
}
