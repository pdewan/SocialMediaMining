package rule;

/**
 * Abstract, superclass of all rules extracting numeric features
 *
 * @author Jinjing Ma (jinjingm@cs.unc.edu)
 * @version $1$
 */
public abstract class NumericFeatureRule extends FeatureRule {

	public NumericFeatureRule(String destFeatureName) {
		super(destFeatureName);
	}
	
	@Override
	public void checkValid(Object val) throws Exception{
		if(val==null) return;
		
		if(!(val instanceof Double || val instanceof Integer)){
			throw new Exception("This attr value must be double/int");
		}
		if(val instanceof Integer){
			val = new Double(((Integer) val).doubleValue());
		}
	}

}
