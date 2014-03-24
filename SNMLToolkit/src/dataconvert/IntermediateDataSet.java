package dataconvert;


public interface IntermediateDataSet{
	
	public void addDataInstance(IntermediateData inst) throws Exception;
	public IntermediateData getDataInstance(int instId) throws Exception;
	
	public int size();
	
	public void save(String path) throws Exception;
	
	public IntermediateDataSet mergeByAttributes(IntermediateDataSet anotherDataSet) throws Exception;
	
	public IntermediateDataSet mergeByDataInstances(IntermediateDataSet[] otherDataSets) throws Exception;
	
	public IntermediateDataSet[] splitToTrainAndTest(double trainPercent) throws Exception;
	
	public IntermediateDataSet[] splitToFolds(int foldNum) throws Exception;
	

}
