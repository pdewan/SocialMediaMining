package dataconvert;

public abstract class IFeatureExtractor {
	
	protected IntermediateDataSetInitializer destDatasetInit;
	
	public IFeatureExtractor(IntermediateDataSetInitializer init){
		destDatasetInit = init;
	}


}
