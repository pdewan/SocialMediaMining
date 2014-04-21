package rule.superfeature.model.weka;

import dataconvert.WekaDataSet;
import rule.superfeature.model.IModelRule;

public interface IWekaModelRule extends IModelRule {
	
	public void evaluate(WekaDataSet train, WekaDataSet test) throws Exception;
	
}
