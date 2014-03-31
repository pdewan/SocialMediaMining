package dataconvert;

import rule.IFeatureRule;



public interface IntermediateDataSetInitializer {
	
	public IntermediateDataSet initDestDataSet(
			String destDataSetName, int threadNum, IFeatureRule[] rules) throws Exception;
	
	public IntermediateData initADataInstance(
			IntermediateDataSet relatedDataset, IFeatureRule[] rules) throws Exception;

}
