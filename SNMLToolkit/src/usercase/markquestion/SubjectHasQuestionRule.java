package usercase.markquestion;

import weka.core.Instance;

public class SubjectHasQuestionRule implements NominalSuperFeatureRule {

	@Override
	public int condition(Instance instance) {
		for(int i=0; i < instance.numAttributes(); i++){
			if(instance.attribute(i).name().equals("Content")){
				if(instance.stringValue(i).contains("?")){
					return 0;
				}
			}
		}
		return 1;
	}

}
