package rule;

/**
 * Abstract, superclass of all rules extracting string features
 *
 * @author Jinjing Ma (jinjingm@cs.unc.edu)
 * @version $1$
 */
public class StringFeatureRule extends FeatureRule {

	public StringFeatureRule(String destFeatureName) {
		super(destFeatureName);
	}

	@Override
	public void checkValid(Object val) throws Exception {
		if(val==null) return;
		
		if(!(val instanceof String)){
			throw new Exception("String type required");
		}

	}

}
