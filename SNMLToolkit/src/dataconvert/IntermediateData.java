package dataconvert;

public interface IntermediateData {
	
	public int setAttrValue(IntermediateDataSet dataset, int attrIndex, Object val);
	public int setDateAttrValue(IntermediateDataSet dataset, int attrIndex, Object val);
	public int setMissing(IntermediateDataSet dataset, int attrIndex, Object val);
	
	
	public String getStringAttrValue(int attrIndex);
	public String getStringAttrValue(String attrName);
	
	public double getNumericAttrValue(int attrIndex);
	public double getNumericAttrValue(String attrName);
	
	public String getDateAttrValue(int attrIndex);
	public String getDateAttrValue(String attrName);
	

}
