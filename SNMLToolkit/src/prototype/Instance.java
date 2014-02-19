package prototype;

import java.util.HashMap;

public class Instance {
	private String id ="";
	private HashMap<Short, Object> fieldMap; //fieldNumber, fieldContent
	private short label = -1;
	
	Instance(String id, int fieldNum, short label){
		this.id = id;
		fieldMap = new HashMap<Short, Object>((int)(fieldNum/0.75)+2);
		this.label = label;
	}
	
	Instance(int fieldNum, short label){
		fieldMap = new HashMap<Short, Object>((int)(fieldNum/0.75)+2);
		this.label = label;
	}
	
	Instance(String id, int fieldNum){
		this.id = id;
		fieldMap = new HashMap<Short, Object>((int)(fieldNum/0.75)+2);
	}
	
	Instance(int fieldNum){
		fieldMap = new HashMap<Short, Object>((int)(fieldNum/0.75)+2);
	}
	
	Instance(){
		fieldMap = new HashMap<Short, Object>();
	}
	
	public String getID(){
		return id;
	}
	
	public int getFieldNum(){
		return this.fieldMap.size();
	}
	
	public void addField(short field, Object content){
		fieldMap.put(field, content);
	}
	
	public void addField(String fieldName, Object content){
		fieldMap.put(DataConfig.getFieldIdx(fieldName), content);
	}
	
	public Object getFieldContent(short fieldIdx){
		return fieldMap.get(fieldIdx);
	}

	public Object getFieldContent(String fieldName){
		short fieldIdx = DataConfig.getFieldIdx(fieldName);
		return fieldMap.get(fieldIdx);
	}
	
	//define operations?
	public Instance add(Instance i){
		Instance sum = new Instance(1);
		sum.addField("Content", content);
		return i;
	}
}
