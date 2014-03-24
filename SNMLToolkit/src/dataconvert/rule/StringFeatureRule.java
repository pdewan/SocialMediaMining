package dataconvert.rule;

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
