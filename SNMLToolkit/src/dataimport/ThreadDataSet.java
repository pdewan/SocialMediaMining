package dataimport;

import java.util.ArrayList;

public class ThreadDataSet {
	protected ArrayList<ThreadData> threads;
	
	//thread id must be integer
	public ThreadDataSet(){
		threads = new ArrayList<ThreadData>();
	}
	
	public ThreadDataSet(int threadNum){
		threads = new ArrayList<ThreadData>(threadNum+1);
	}
	
	//Setup an empty thread at threadId only if it's not initialized yet
	public void setThreadData(int threadId){
		if(this.threads.get(threadId)==null){
			this.setThreadData(threadId, new ThreadData());
		}
	}
	
	public void setThreadData(int threadId, ThreadData aThread){
		threads.set(threadId, aThread);
	}
	/*
	public void setThreadData(String threadId, ThreadData aThread){
		this.setThreadData(Integer.parseInt(threadId), aThread);
	}
	*/
	
	//Setup an empty thread at threadId only if it's not initialized yet
	public ThreadData getThreadData(int threadId){
		if(threadId >= threads.size() ){
			int i = threads.size();
			for(; i<=threadId; i++){
				threads.add(null);
			}
		}
		if(this.threads.get(threadId)==null){
			this.setThreadData(threadId, new ThreadData());
		}
		return threads.get(threadId);
	}
	/*
	public ThreadData getThreadData(String threadId){
		return threads.get(Integer.parseInt(threadId));
	}
	*/
	
	public void addMsgData(int threadId, MessageData aMsgData){
		this.getThreadData(threadId).addMsgData(aMsgData);
	}
	
	public int size(){
		return threads.size();
	}
	
	public void trimToSize(){
		threads.trimToSize();
	}
}
