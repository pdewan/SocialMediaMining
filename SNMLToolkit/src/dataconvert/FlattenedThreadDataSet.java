package dataconvert;

import java.util.ArrayList;
import java.util.HashMap;

public class FlattenedThreadDataSet {
	protected ArrayList<FlattenedThreadData> threads;
	protected HashMap<String, String> attrTypes;
	
	//thread id must be integer
	public FlattenedThreadDataSet(){
		threads = new ArrayList<FlattenedThreadData>();
		attrTypes = new HashMap<String, String>(20);
	}
	
	public FlattenedThreadDataSet(int threadNum){
		threads = new ArrayList<FlattenedThreadData>(threadNum+1);
		attrTypes = new HashMap<String, String>(20);
	}
	
	public void setAttrType(String attrName, String attrType){
		attrTypes.put(attrName, attrType);
	}
	
	public String getAttrType(String attrName){
		return attrTypes.get(attrName);
	}
	
	public void addTreadData(FlattenedThreadData aThread){
		threads.add(aThread);
	}
	
	public void setThreadData(int threadId, FlattenedThreadData aThread){
		if(threadId >= threads.size() ){
			int i = threads.size();
			for(; i<=threadId; i++){
				threads.add(null);
			}
		}
		threads.set(threadId, aThread);
	}
	
	//Setup an empty thread at threadId only if it's not initialized yet
	public FlattenedThreadData getThreadData(int threadId){		
		if(threadId >= threads.size() || threads.get(threadId)==null){
			this.setThreadData(threadId, new FlattenedThreadData());
		}
		return threads.get(threadId);
	}
	
	public int size(){
		return threads.size();
	}
	
	public void trimToSize(){
		threads.trimToSize();
	}

}
