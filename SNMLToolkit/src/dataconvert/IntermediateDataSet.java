package dataconvert;

public interface IntermediateDataSet {
	
	public void addInstance(IntermediateData inst) throws Exception;
	
	public void insertAttributeAt(int index) throws Exception;
	
	public void save(String path) throws Exception;
	
	public IntermediateDataSet merge(IntermediateDataSet anotherDataSet) throws Exception;

}
