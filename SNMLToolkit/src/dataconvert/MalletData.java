package dataconvert;

import cc.mallet.types.Instance;

public class MalletData implements IntermediateData {
	
	Instance inst;

	@Override
	public int setAttrValue(IntermediateDataSet dataset, int attrIndex,
			Object val) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDateAttrValue(IntermediateDataSet dataset, int attrIndex,
			Object val) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMissing(IntermediateDataSet dataset, int attrIndex, Object val) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getStringAttrValue(int attrIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringAttrValue(String attrName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getNumericAttrValue(int attrIndex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getNumericAttrValue(String attrName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDateAttrValue(int attrIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDateAttrValue(String attrName) {
		// TODO Auto-generated method stub
		return null;
	}

}
