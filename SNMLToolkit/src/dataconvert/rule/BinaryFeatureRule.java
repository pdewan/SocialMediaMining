package dataconvert.rule;

import java.util.ArrayList;

public class BinaryFeatureRule extends NominalFeatureRule {

	public BinaryFeatureRule(String destFeatureName) {
		super(destFeatureName);
		domain.add("y");
		domain.add("n");
	}
	
	public BinaryFeatureRule(String destFeatureName, ArrayList<String> aDomain) {
		super(destFeatureName, aDomain);
	}

}
