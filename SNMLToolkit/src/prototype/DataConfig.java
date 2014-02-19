package prototype;

import java.util.HashMap;

public class DataConfig {
	static short fieldNum;
	static short labelNum;
	
	static HashMap<Short, Class> fieldType;
	static HashMap<String, Short> fieldName2IdxMap;
	static String[] fieldNames;
	
	static String[] labelNames;
	static HashMap<String, Short> labelName2IdxMap;
	
	//can be set in userPI
	static HashMap<String, String> typeShortcutMap = new HashMap<String, String>();
	
	//******type shortcut************
	static public void addTypeShortcut(String shortcut, String javaType){
		typeShortcutMap.put(shortcut, javaType);
	}
	
	static public String getJavaType(String shortcut){
		return typeShortcutMap.get(shortcut);
	}
	//---------------------------------------------------------
	
	//****** field name vs field index ************
	static public void setFieldNum(short n){
		fieldName2IdxMap = new HashMap<String, Short>(n);
		fieldType = new HashMap<Short, Class>(n);
		fieldNames = new String[n];
		fieldNum = n;
	}
	
	static public void addField(String fieldName, String fieldClass, short i){
		fieldNames[i] = fieldName;
		fieldName2IdxMap.put(fieldName, i);
		try {
			fieldType.put(i, Class.forName(fieldClass));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static public String getFieldName(short fieldIdx){
		return fieldNames[fieldIdx];
	}
	
	static public short getFieldIdx(String fieldName){
		return fieldName2IdxMap.get(fieldName);
	}
	
	static public boolean containsField(String fieldName){
		return fieldName2IdxMap.containsKey(fieldName);
	}
	
	static public boolean containsField(short fieldIdx){
		return fieldNames.length > fieldIdx;
	}
	//---------------------------------------------------------
	
	
	//----------label name vs label index -------------
	static public void setLabelNum(short n){
		labelNames = new String[n];
		labelName2IdxMap = new HashMap<String, Short>(n);
		labelNum = n;
	}
	
	static public void addLabel(String labelName, short i){
		labelNames[i] = labelName;
		labelName2IdxMap.put(labelName, i);
	}
	
	static public String getLabelName(short labelIdx){
		return labelNames[labelIdx];
	}
	
	static public short getLabelIdx(String labelName){
		return labelName2IdxMap.get(labelName);
	}
	//---------------------------------------------------------
}
