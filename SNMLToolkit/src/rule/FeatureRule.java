package rule;

/**
 * Abstract, superclass of all feature extracting rules
 *
 * @author Jinjing Ma (jinjingm@cs.unc.edu)
 * @version $1$
 */
public abstract class FeatureRule implements IFeatureRule {
	
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
