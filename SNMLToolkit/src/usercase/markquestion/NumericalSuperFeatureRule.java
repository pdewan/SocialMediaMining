package usercase.markquestion;

import weka.core.Instance;

public interface NumericalSuperFeatureRule {

	//instance would be marked with the return value
	public double condition(Instance instance);
}
