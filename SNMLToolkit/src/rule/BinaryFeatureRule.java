package rule;

import java.util.ArrayList;

/**
 * subclass of NominalFeatureRule extracting binary features
 *
 * @author Jinjing Ma (jinjingm@cs.unc.edu)
 * @version $1$
 */
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
