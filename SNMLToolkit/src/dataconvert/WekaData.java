package dataconvert;

import java.lang.reflect.Array;
import java.text.ParseException;

import weka.core.DenseInstance;
import weka.core.Instance;

public class WekaData implements IntermediateData {
	
	protected Instance inst;

	public WekaData(int attrNum){
		inst = new DenseInstance(attrNum);
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

}