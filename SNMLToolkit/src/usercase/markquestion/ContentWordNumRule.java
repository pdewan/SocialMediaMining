package usercase.markquestion;

import java.util.StringTokenizer;

import weka.core.Instance;

public class ContentWordNumRule implements NumericalSuperFeatureRule {

	@Override
	public double condition(Instance instance) {
		double k = 0;
		for(int i=0; i < instance.numAttributes(); i++){
			if(instance.attribute(i).name().equals("Content")){
				StringTokenizer st = new StringTokenizer(instance.stringValue(i), ",.!?(){} ");
				k = st.countTokens();
				return k;
			}
		}
		return 0;
	}

}
