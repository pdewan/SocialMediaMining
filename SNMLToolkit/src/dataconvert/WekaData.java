package dataconvert;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dataimport.MsgDataConfig;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class WekaData implements IntermediateData {
	
	protected Instance inst;
	protected Instances relatedDataset;

	public WekaData(int attrNum){
		inst = new DenseInstance(attrNum);
	}
	
	public void setRelatedDataset(WekaDataSet dataset){	
		this.relatedDataset = dataset.getDataSet();
	}
	
	
	public Instance getInstValue(){
		return inst;
	}



	@Override
	public int setAttrValue(IntermediateDataSet dataset, 
			int attrIndex,
			Object val) {		
		
		if(val instanceof Double){		
			inst.setValue(((WekaDataSet)dataset).attribute(attrIndex), (Double)val);
			attrIndex++;	
		}else if(val instanceof String){
			inst.setValue(((WekaDataSet)dataset).attribute(attrIndex), (String)val);
			attrIndex++;	
		}else if(val instanceof Double[]){
			for(int j=0; j<Array.getLength(val); j++){				
				inst.setValue(((WekaDataSet)dataset).attribute(attrIndex++), 
						Array.getDouble(val, j));
			}
		}
		return attrIndex;
	}



	@Override
	public int setDateAttrValue(IntermediateDataSet dataset, int attrIndex,
			Object val) {
		if(val instanceof Double){		
			inst.setValue(((WekaDataSet)dataset).attribute(attrIndex), (Double)val);
		}else if(val instanceof String){
			double time;
			try {
				time = ((WekaDataSet)dataset).attribute(attrIndex).parseDate((String)val);
				inst.setValue(((WekaDataSet)dataset).attribute(attrIndex),time);
			} catch (ParseException e) {
				System.out.println("Data format wrong");
				e.printStackTrace();
			}						
		}
		return attrIndex+1;		
	}



	@Override
	public int setMissing(IntermediateDataSet dataset, int attrIndex,
			Object val) {
		return attrIndex+1;		
	}



	@Override
	public String getStringAttrValue(int attrIndex) {
		return inst.stringValue(attrIndex);
	}



	@Override
	public String getStringAttrValue(String attrName) {
		Attribute attr = relatedDataset.attribute(attrName);
		return inst.stringValue(attr);
	}



	@Override
	public double getNumericAttrValue(int attrIndex) {
		return inst.value(attrIndex);
	}



	@Override
	public double getNumericAttrValue(String attrName) {
		Attribute attr = relatedDataset.attribute(attrName);
		return inst.value(attr);
	}



	@Override
	public String getDateAttrValue(int attrIndex) {
		double time = inst.value(attrIndex);
		Attribute attr = relatedDataset.attribute(attrIndex);
		String date = attr.formatDate(time);
		
		return date;
	}



	@Override
	public String getDateAttrValue(String attrName) {		
		Attribute attr = relatedDataset.attribute(attrName);
		double time = inst.value(attr);
		String date = attr.formatDate(time);
		
		return date;
	}
	

}
