package usercase.markquestion;

import weka.core.Instance;

public interface NominalSuperFeatureRule {
	//when condition returns k, 
	//instance would be marked with kth marker in nominal marker list
	public int condition(Instance instance);

}
