package dataconvert;

public interface IntermediateData {
	
	public int setAttrValue(IntermediateDataSet dataset, int attrIndex, Object val);
	public int setDateAttrValue(IntermediateDataSet dataset, int attrIndex, Object val);
	public int setMissing(IntermediateDataSet dataset, int attrIndex, Object val);

}
