package usercase.markquestion;

import weka.core.Instance;

public interface NumericSuperFeatureRule {

	//instance would be marked with the return value
	public double condition(Instance instance);
}
